package com.xielaoban.cqueshop.Admin.Controller;

import com.alibaba.fastjson.JSON;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.User;
import com.xielaoban.cqueshop.Service.UserService;
import com.xielaoban.cqueshop.Util.MD5Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 蟹老板
 * @Date 2021-4-12 21:41
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Admin.Controller
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/login")
public class AdminLoginController {
    private final Log log = LogFactory.getLog(AdminLoginController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/adminLogin")
    public Result adminLogin(@RequestBody User user) {
        try {
            log.info("进入登录方法，获取到的登录信息：" + user);
            User userInfo = userService.userLogin(user.getUsername(), MD5Util.encryption(user.getPassword()));
            if (userInfo != null) {
                if (userInfo.getStatus() == 0) {
                    return Result.Error("该账户目前已被锁定，请联系管理员！");
                }
                //不将密码暴露给前端
                userInfo.setPassword(null);
                //将用户信息储存在redis里面，用token作为key，24小时过期
                String userToken = userInfo.getId();
                log.info("将获取到的用户信息放进redis：key-" + userToken + "-value-" + JSON.toJSONString(userInfo));
                if (userInfo.getRole() == 3) {
                    return Result.Success("登录成功", userToken);
                }
            }
            return Result.Error();
        } catch (Exception e) {
            log.error("用户登录出错了！", e);
            return Result.Error("用户登录出错！请联系管理员！", null);
        }
    }
}
