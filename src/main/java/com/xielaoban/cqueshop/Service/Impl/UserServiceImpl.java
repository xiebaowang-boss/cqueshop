package com.xielaoban.cqueshop.Service.Impl;

import com.xielaoban.cqueshop.Entity.User;
import com.xielaoban.cqueshop.Mapper.UserMapper;
import com.xielaoban.cqueshop.Service.UserService;
import com.xielaoban.cqueshop.Util.DateUtil;
import com.xielaoban.cqueshop.Util.GenerateUUID;
import com.xielaoban.cqueshop.Util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

/**
 * @Author 蟹老板
 * @Date 2021-3-25 20:26
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service.Impl
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int userRegister(User user) throws ParseException, NoSuchAlgorithmException {
        //如果用户名存在返回0
        if (userMapper.hasUsername(user.getUsername(), user.getPhone()) > 0) {
            return 0;
        }
        //将用户密码加密
        user.setPassword(MD5Util.encryption(user.getPassword()));
        user.setId(GenerateUUID.getUUID());
        user.setStatus(1);
        user.setRole(1);
        user.setCreatetime(DateUtil.getCurrentDate());
        return userMapper.userRegister(user);
    }

    @Override
    public User userLogin(String userName, String password) {
        //List<User> userList = userMapper.userLogin(userName, password);
        //userList.size() > 0 ? userList.get(0) : null
        return userMapper.userLogin(userName, password);
    }

    @Override
    public User get(String id) {
        return userMapper.get(id);
    }
}
