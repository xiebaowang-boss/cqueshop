package com.xielaoban.cqueshop.Mapper;

import com.xielaoban.cqueshop.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-3-22 11:53
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Mapper
 * @Description
 */
@Mapper
public interface UserMapper {
    List<User> getAll(String query);

    User get(String id);

    int updateUserStatus(User user);

    int userRegister(User user);

    int hasUsername(String username, String phone);

    User userLogin(String userName, String password);

    int update(User user);

    int del(String id);
}
