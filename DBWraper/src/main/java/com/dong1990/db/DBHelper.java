package com.dong1990.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.dong1990.config.DataConfig;
import com.dong1990.logger.Loggers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据库助手
 *
 * @author zhu_tek
 */

public class DBHelper implements AutoCloseable {
    //DB连接监控 ，1分钟之内3次获取连接失败，发出告警，停止处理REDIS数据
    public static final int THRESHHOLD = 3;
    public static final int PERIOD = 60 * 1000;
    public static String confile = "db.properties";
    private static Properties info;
    private static DruidDataSource dds = null;
    private static InputStream inputStream = null;
    private static AtomicInteger failCount = new AtomicInteger(0);
    private static long failTime;
    private static boolean isHealthy = true;

    // 定义MySQL的数据库驱动程序
    public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
    // 定义MySQL数据库的连接地址
    public static final String DBURL = "jdbc:mysql://localhost:3306/ssh";
    // MySQL数据库的连接用户名
    public static final String DBUSER = "root";
    // MySQL数据库的连接密码
    public static final String DBPASSWORD = "123456";

    static {
        try {


        } catch (Exception e) {
            Loggers.TOTAL.error(e.getMessage(), e);
        }
    }

    public Connection conn = null;
    public PreparedStatement pst = null;

    public DBHelper(String sql) {
        try {
            Class.forName(DBDRIVER);
            //conn = dds.getConnection();// 获取连接
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            pst = conn.prepareStatement(sql);//准备执行语句
            isHealthy = true;
        } catch (SQLException e) {
            Loggers.TOTAL.error("DBHelper get connection fail: " + e.getMessage(), e);
            countConnectFail(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean isDBHealthy() {
        return isHealthy;
    }

    public static void shutdown() {
        try {
            Loggers.TOTAL.info("Shutdown datasource");
            dds.close();
        } catch (Exception e) {
            Loggers.TOTAL.error(e.getMessage(), e);
        }
    }

    private void countConnectFail(SQLException e) {
        long time = System.currentTimeMillis();
        if (failTime == 0 || (time - failTime) > PERIOD) {
            failCount.set(1);
            failTime = time;
        } else {
            int count = failCount.incrementAndGet();
            if (count >= THRESHHOLD) {
                isHealthy = false;
                Loggers.FATAL_ERROR.error("DBHelper get connection fail error!");
                Loggers.FATAL_ERROR.error(e.getMessage(), e);
            }
        }
    }

    //关闭
    public void close() {
        try {
            if (null != this.pst) {
                if (!this.pst.isClosed()) {
                    this.pst.close();
                }
            }
            if (null != this.conn) {
                if (!this.conn.isClosed()) {
                    this.conn.close();
                }
            }
        } catch (SQLException e) {
            Loggers.TOTAL.error(e.getMessage(), e);
        }
    }

}

