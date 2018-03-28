package com.dong1990.dao;

import com.dong1990.db.DBWraper;
import com.dong1990.model.SysUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.List;

public class SysUserDao {

    public List<SysUser> getAllListUser() {
        StringBuffer querySql = new StringBuffer()
                .append("select * from sys_user");
        return new DBWraper<SysUser>().queryList(new DBWraper.EntityWrap<SysUser>() {
            @Override
            public SysUser entityWrap(Wrapper wrapper) throws SQLException {
                ResultSet resultSet = (ResultSet) wrapper;
                return sysUserWrap(resultSet);
            }
        }, querySql.toString(), null);
    }

    private SysUser sysUserWrap(ResultSet ret) {
        SysUser sysUser = new SysUser();
        try {
            sysUser.setUser_id(ret.getInt("user_id"));
            sysUser.setUser_code(ret.getString("user_code"));
            sysUser.setUser_name(ret.getString("user_name"));
            sysUser.setUser_password(ret.getString("user_password"));
            sysUser.setUser_state(ret.getInt("user_state"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sysUser;
    }

    public SysUser getUserById(Integer id) {
        StringBuffer querySql = new StringBuffer()
                .append("select * from sys_user where user_id = ?");
        return new DBWraper<SysUser>().queryObject(new DBWraper.EntityWrap<SysUser>() {
            @Override
            public SysUser entityWrap(Wrapper wrapper) throws SQLException {
                ResultSet resultSet = (ResultSet) wrapper;
                return sysUserWrap(resultSet);
            }
        },querySql.toString(),id);
    }
}
