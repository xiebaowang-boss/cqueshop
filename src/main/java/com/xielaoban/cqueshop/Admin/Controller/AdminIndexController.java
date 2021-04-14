package com.xielaoban.cqueshop.Admin.Controller;

import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.AdminMenu;
import com.xielaoban.cqueshop.Service.AdminMenuService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-12 21:14
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Admin
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/index")
public class AdminIndexController {
    private static final Log log = LogFactory.getLog(AdminIndexController.class);

    @Autowired
    private AdminMenuService adminMenuService;

    @RequestMapping("/getAdminMenu")
    public Result getAdminMenu() {
        try {
            log.info("获取管理员菜单");
            List<AdminMenu> menuList = adminMenuService.getAllEnabled();
            return Result.Success(menuList);
        } catch (Exception e) {
            log.info("获取管理员菜单出错了！", e);
            return Result.Error();
        }
    }
}
