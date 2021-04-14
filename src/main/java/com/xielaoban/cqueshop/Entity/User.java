package com.xielaoban.cqueshop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 蟹老板
 * @Date 2021-3-22 11:28
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Entity
 * @Description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 用户id
     */
    private String id;
    /**
     * 角色id 1普通用户 2管理员 3超级管理员
     */
    private Integer role;
    /**
     *
     */
    private String name;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     * 往前端传用户的时候不传密码
     */
    @ToString.Exclude
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户头像
     */
    private Image avatar;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 1正常 0封号
     */
    private Integer status;
    /**
     * 地址
     */
    private String address;

    //
    private String code;

}
