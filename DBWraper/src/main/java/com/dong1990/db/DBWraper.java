package com.dong1990.db;

import com.dong1990.db.DBHelper;
import com.dong1990.logger.Loggers;
import org.apache.http.util.Asserts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单jdbc warp
 */
public class DBWraper<T> {

    private int fetchSize = 128;

    public DBWraper(int fetchSize) {
        Asserts.check(fetchSize > 0,
                String.format("fetchSize[%s] must > 0 ", fetchSize));
        this.fetchSize = fetchSize;
    }

    public DBWraper() {
    }

    public List<T> queryList(EntityWrap<T> wrap, String sql, Object... objects) {
        Asserts.notNull(sql, String.format("sql[%s] is null", sql));
        List<T> arrayList = new ArrayList<>();
        try (DBHelper querydb = new DBHelper(sql)) {
            setParamter(querydb.pst, objects);
            querydb.pst.setFetchSize(fetchSize);
            ResultSet resultSet = querydb.pst.executeQuery();
            while (resultSet.next()) {
                arrayList.add(wrap.entityWrap(resultSet));
            }
        } catch (SQLException e) {
            Loggers.TOTAL.error("queryList error:{}", e.getMessage(), e);
        }
        return arrayList;
    }

    public T queryObject(EntityWrap<T> wrap, String sql, Object... objects) {
        Asserts.notNull(sql, String.format("sql[%s] is null", sql));
        try (DBHelper querydb = new DBHelper(sql)) {
            setParamter(querydb.pst, objects);
            querydb.pst.setFetchSize(fetchSize);
            ResultSet resultSet = querydb.pst.executeQuery();
            while (resultSet.next()) {
                return wrap.entityWrap(resultSet);
            }
        } catch (SQLException e) {
            Loggers.TOTAL.error("queryObject error:{}", e.getMessage(), e);
        }
        return null;
    }

    public int update(String sql, Object... objects) {
        Asserts.notNull(sql, String.format("sql[%s] is null", sql));
        try (DBHelper updateDb = new DBHelper(sql)) {
            setParamter(updateDb.pst, objects);
            return updateDb.pst.executeUpdate();
        } catch (SQLException e) {
            Loggers.TOTAL.error("update error:{}", e.getMessage(), e);
        }
        return 0;
    }


    public void delete(String sql, Object... objects) {
        Asserts.notNull(sql, String.format("sql[%s] is null", sql));
        try (DBHelper deleteDb = new DBHelper(sql)) {
            setParamter(deleteDb.pst, objects);
            deleteDb.pst.executeUpdate();
        } catch (SQLException e) {
            Loggers.TOTAL.error("delete error:{}", e.getMessage(), e);
        }
    }

    private void setParamter(PreparedStatement ps, Object[] objects) throws SQLException, IllegalArgumentException {
        if (objects == null)
            return;
        for (int i = 0; i < objects.length; i++) {
            final int index = i + 1;
            if (objects[i] instanceof String) {
                String obj = String.valueOf(objects[i]);
                ps.setString(index, obj);
            } else if (objects[i] instanceof Integer) {
                int obj = (int) objects[i];
                ps.setInt(index, obj);
            } else if (objects[i] instanceof Long) {
                long obj = (long) objects[i];
                ps.setLong(index, obj);
            } else throw new IllegalArgumentException("can't find classType");
        }
    }

    public interface EntityWrap<T> {
        T entityWrap(Wrapper wrapper) throws SQLException;
    }
}
