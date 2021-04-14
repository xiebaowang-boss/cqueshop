package com.xielaoban.cqueshop.Admin.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.User;
import com.xielaoban.cqueshop.Service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 蟹老板
 * @Date 2021-4-12 22:36
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Admin.Controller
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    private final Log log = LogFactory.getLog(AdminUserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/getAll{query}")
    public Result getAll(@PathVariable(required = false) String query, @RequestBody JSONObject pageInfo) {
        try {
            log.info("获取所有用户");
            int currentPage = pageInfo.getInteger("currentPage");
            int pageSize = pageInfo.getInteger("pageSize");
            PageInfo<User> userPageInfo = userService.getAll(query, pageSize, currentPage);
            return Result.Success(userPageInfo);
        } catch (Exception e) {
            log.error("获取所有用户出错了", e);
            return Result.Error();
        }
    }

    @RequestMapping("/updateUserStatus")
    public Result updateUserStatus(@RequestBody User user) {
        try {
            log.info("更新用户状态！");
            int result = userService.updateUserStatus(user);
            return result > 0 ? Result.Success(result) : Result.Error();
        } catch (Exception e) {
            log.error("更新用户状态出错了", e);
            return Result.Error();
        }
    }

    @RequestMapping("/get/{userId}")
    public Result get(@PathVariable String userId) {
        try {
            log.info("获取用户！");
            User user = userService.get(userId);
            return Result.Success(user);
        } catch (Exception e) {
            log.error("获取用户出错了", e);
            return Result.Error();
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        try {
            log.info("更新用户！");
            int result = userService.update(user);
            return result > 0 ? Result.Success(result) : Result.Error();
        } catch (Exception e) {
            log.error("更新用户出错了", e);
            return Result.Error();
        }
    }

    @DeleteMapping("/del/{userId}")
    public Result update(@PathVariable String userId) {
        try {
            log.info("删除用户:" + userId);
            int result = userService.del(userId);
            return result > 0 ? Result.Success(result) : Result.Error();
        } catch (Exception e) {
            log.error("删除用户出错了：", e);
            return Result.Error();
        }
    }
}
