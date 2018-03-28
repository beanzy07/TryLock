package com.dong1990.config;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 读取redis.properties属性文件，和初始化连接信息
 *
 * @author hjp
 *
 */
public class DataConfig {

    private static Properties redisInfo;
    private static InputStream inputStream = null;
    public static String redisconfile = "redis.properties";

    static {
        URL pathURL = DataConfig.class.getClassLoader().getResource("");
        if (pathURL != null) {
            redisconfile = pathURL.getPath() + redisconfile;
        } else {
            redisconfile = System.getProperty("user.dir") + System.getProperty("file.separator") + redisconfile;
        }
        redisInfo = new Properties();
        try {
            inputStream = new BufferedInputStream(new FileInputStream(new File(redisconfile)));
            redisInfo.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String REDIS_HOST = redisInfo.getProperty("REDIS_HOST");
    public static String REDIS_AUTH = redisInfo.getProperty("REDIS_AUTH");
}

