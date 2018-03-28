package com.dong1990.db;

import com.alibaba.druid.filter.config.ConfigTools;

public  class DecryptDruid {
    /**
     * 对密码进行解密
     * @throws Exception
     */
    public static String passwordDecrypt(String password) throws Exception {
        String decryptword = ConfigTools.decrypt(password);
        return decryptword;
    }

    /**
     * 对密码进行加密
     * @throws Exception
     */
    public static String passwordEncrypt(String password) throws Exception {
        // 加密
        String encryptword = ConfigTools.encrypt(password);
        return encryptword;
    }
}
