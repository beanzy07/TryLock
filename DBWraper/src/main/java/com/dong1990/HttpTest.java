package com.dong1990;

import com.dong1990.model.SysUser;
import com.dong1990.service.SysUserManager;

import java.util.List;

public class HttpTest {

    public static void main(String[] args){
        SysUserManager sysUserManager = new SysUserManager();
        List<SysUser> sysUserList = sysUserManager.getAllListUser();
        System.out.println("----------------------");
        for (SysUser sysUser : sysUserList) {
            System.out.println(sysUser.toString());
        }

        Integer id = Integer.valueOf("11");
        SysUser sysUser = sysUserManager.getUserById(id);
        System.out.println("----------------------");
        System.out.println(sysUser.toString());
    }
}
