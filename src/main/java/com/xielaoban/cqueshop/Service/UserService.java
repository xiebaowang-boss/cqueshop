package com.xielaoban.cqueshop.Service;

import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.User;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-3-25 20:26
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service
 * @Description
 */
public interface UserService {
    int userRegister(User user) throws ParseException, NoSuchAlgorithmException;

    int updateUserStatus(User user);

    User userLogin(String userName, String password);

    User get(String id);

    PageInfo<User> getAll(String query, Integer pageSize, Integer currentPage);

    int update(User user);

    int del(String userId);
}
