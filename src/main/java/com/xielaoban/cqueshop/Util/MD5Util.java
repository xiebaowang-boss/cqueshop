package com.xielaoban.cqueshop.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @Author 蟹老板
 * @Date 2021-3-27 16:10
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Util
 * @Description
 */
public class MD5Util {

    /**
     * @Description: 将字符串进行MD5加密
     * @Name: encryption
     * @Param: [java.lang.String]
     * @return: java.lang.String
     * @Author: 12105
     * @Date: 2021-3-27
     * @Time: 16:31
     */
    public static String encryption(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] bytes = md.digest(str.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }
}
