package com.dong1990.service;

import com.dong1990.dao.SysUserDao;
import com.dong1990.model.SysUser;

import java.util.List;

public class SysUserManager {

    private SysUserDao sysUserDao = new SysUserDao();


    public List<SysUser> getAllListUser() {
        return sysUserDao.getAllListUser();
    }

    public SysUser getUserById(Integer id) {
        return sysUserDao.getUserById(id);
    }
}
