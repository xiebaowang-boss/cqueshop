package com.xielaoban.cqueshop.Service;

import com.xielaoban.cqueshop.Entity.User;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

/**
 * @Author 蟹老板
 * @Date 2021-3-25 20:26
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service
 * @Description
 */
public interface UserService {
    int userRegister(User user) throws ParseException, NoSuchAlgorithmException;


    User userLogin(String userName, String password);

    User get(String id);
}
