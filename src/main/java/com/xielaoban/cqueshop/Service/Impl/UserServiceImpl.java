package com.xielaoban.cqueshop.Service.Impl;

import com.xielaoban.cqueshop.Entity.User;
import com.xielaoban.cqueshop.Mapper.UserMapper;
import com.xielaoban.cqueshop.Service.UserService;
import com.xielaoban.cqueshop.Util.GenerateUUID;
import com.xielaoban.cqueshop.Util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
        String nowTime = sdf.format(date);
        Date time = sdf.parse(nowTime);
        //将用户密码加密
        user.setPassword(MD5Util.encryption(user.getPassword()));
        user.setId(GenerateUUID.getUUID());
        user.setStatus(1);
        user.setRole(1);
        user.setCreatetime(time);
        return userMapper.userRegister(user);
    }

    @Override
    public User userLogin(String userName, String password) {
        return userMapper.userLogin(userName, password);
    }
}
