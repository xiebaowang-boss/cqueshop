package com.xielaoban.cqueshop.Service.Impl;

import com.xielaoban.cqueshop.Entity.User;
import com.xielaoban.cqueshop.Mapper.UserMapper;
import com.xielaoban.cqueshop.Service.UserService;
import com.xielaoban.cqueshop.Util.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int userRegister(User user) throws ParseException {
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
        String nowTime = sdf.format(date);
        Date time = sdf.parse(nowTime);
        user.setId(GenerateUUID.getUUID());
        user.setStatus(1);
        user.setRole(1);
        user.setCreatetime(time);
        return userMapper.userRegister(user);
    }
}
