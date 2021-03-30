package com.xielaoban.cqueshop.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.User;
import com.xielaoban.cqueshop.Service.UserService;
import com.xielaoban.cqueshop.Util.GenerateUUID;
import com.xielaoban.cqueshop.Util.MD5Util;
import com.xielaoban.cqueshop.Util.RedisUtil;
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
    @Autowired
    RedisUtil redisUtil;

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
    public Result userLogin(@RequestBody User user) {
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
                String userToken = GenerateUUID.getToken();
                log.info("将获取到的用户信息放进redis：key-" + userToken + "-value-" + JSON.toJSONString(userInfo));
                redisUtil.set(userToken, JSON.toJSONString(userInfo), 60 * 60 * 24);
                return Result.Success("登录成功！将为你跳转到首页!", userToken);
            }
            return Result.Error("账户名不存在或密码错误！", null);
        } catch (Exception e) {
            log.error("用户登录出错了！", e);
            return Result.Error("用户登录出错！请联系管理员！", null);
        }
    }

    @PostMapping("/getUserInfoByToken")
    public Result getUserInfoByToken(@RequestBody String tokenInfo) {
        try {
            JSONObject tokenJson = JSON.parseObject(tokenInfo);
            String token = tokenJson.get("token").toString();
            log.info("根据Token获取用户信息得到的Token为：" + token);
            //
            boolean hasUser = redisUtil.hasKey(token);
            //如果不存在用户就返回
            if (!hasUser) {
                log.info("该用户信息登录信息已经过期！-  " + token);
                return Result.Error();
            }
            User user = JSON.parseObject(redisUtil.get(token).toString(), User.class);
            return Result.Success(user);
        } catch (Exception e) {
            log.error("根据Token查询用户信息出错了！获取到的Token：" + tokenInfo, e);
            return Result.Error();
        }
    }

    @GetMapping("/destroyUserToken")
    public Result destroyUserToken(String userToken) {
        try {
            log.info("销毁用户Token：" + userToken);
            if (redisUtil.hasKey(userToken)) {
                redisUtil.deleteKey(userToken);
                return Result.Success();
            } else {
                return Result.Success();
            }
        } catch (Exception e) {
            log.info("销毁用户Token出错了：" + userToken, e);
            return Result.Error();
        }
    }
}
