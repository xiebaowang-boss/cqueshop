package com.xielaoban.cqueshop.Admin.Controller;

import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.AdminMenu;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xielaoban.cqueshop.Service.AdminMenuService;

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

    @RequestMapping("/getAllChild/{parentId}")
    public Result getAllChild(@PathVariable String parentId) {
        try {
            log.info("获取管理员子菜单");
            List<AdminMenu> menuList = adminMenuService.getAllChild(parentId);
            return Result.Success(menuList);
        } catch (Exception e) {
            log.info("获取管理员菜单出错了！", e);
            return Result.Error();
        }
    }

    @GetMapping("/manageAdminMenu")
    public Result manageAdminMenu() {
        try {
            log.info("获取管理员菜单");
            List<AdminMenu> adminMenuList = adminMenuService.getAll();
            return Result.Success(adminMenuList);
        } catch (Exception e) {
            log.info("获取管理员菜单出错了！", e);
            return Result.Error();
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody AdminMenu adminMenu) {
        try {
            log.info("更新管理员菜单");
            int result = adminMenuService.update(adminMenu);
            return result > 0 ? Result.Success(result) : Result.Error();
        } catch (Exception e) {
            log.info("更新管理员菜单出错了！", e);
            return Result.Error();
        }
    }

    @GetMapping("/updateMenuStatus")
    public Result updateMenuStatus(String id, Integer status) {
        try {
            log.info("更新管理员菜单状态");
            int result = adminMenuService.updateMenuStatus(id, status);
            return result > 0 ? Result.Success(result) : Result.Error();
        } catch (Exception e) {
            log.info("更新管理员菜单状态出错了！", e);
            return Result.Error();
        }
    }


    @PostMapping("/add")
    public Result add(@RequestBody AdminMenu adminMenu) {
        try {
            log.info("新增菜单" + adminMenu);
            int result = adminMenuService.add(adminMenu);
            return result > 0 ? Result.Success(result) : Result.Error();
        } catch (Exception e) {
            log.info("新增菜单出错了！", e);
            return Result.Error();
        }
    }

    @DeleteMapping("/del/{menuId}")
    public Result del(@PathVariable String menuId) {
        try {
            log.info("删除菜单" + menuId);
            int result = adminMenuService.del(menuId);
            return result > 0 ? Result.Success(result) : Result.Error();
        } catch (Exception e) {
            log.info("删除菜单出错了！", e);
            return Result.Error();
        }
    }

    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        try {
            log.info("获取菜单" + id);
            AdminMenu adminMenu = adminMenuService.get(id);
            log.info("获取到的菜单信息:" + adminMenu);
            return adminMenu != null ? Result.Success(adminMenu) : Result.Error();
        } catch (Exception e) {
            log.info("获取菜单出错了！" + id, e);
            return Result.Error();
        }
    }
}
