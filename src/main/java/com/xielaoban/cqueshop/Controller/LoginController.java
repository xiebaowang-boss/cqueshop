package com.xielaoban.cqueshop.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.User;
import com.xielaoban.cqueshop.Service.UserService;
import com.xielaoban.cqueshop.Util.MD5Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

/**
 * @Author 蟹老板
 * @Date 2021-3-23 14:09
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Controller
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {
    private final Log log = LogFactory.getLog(LoginController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/userRegister")
    public Result userRegister(@RequestBody User user) {
        try {
            log.info("用户注册上传信息：" + user);
            int i = userService.userRegister(user);
            if (i == 1) {
                return Result.Success();
            }
        } catch (Exception e) {
            log.error("用户注册失败", e);
            return Result.Error();
        }
        return Result.Error();
    }

    @PostMapping("/userLogin")
    public Result userLogin(@RequestBody String info) {
        try {
            log.info("进入登录方法，获取到的登录信息：" + info);
            JSONObject loginInfo = JSON.parseObject(info);
            String userName = loginInfo.get("username").toString();
            String password = loginInfo.get("password").toString();
            User user = userService.userLogin(userName, MD5Util.encryption(password));
            user.setPassword(null);
            return Result.Success(user);
        } catch (Exception e) {
            log.error("用户登录出错了", e);
            return Result.Error();
        }
    }
}
