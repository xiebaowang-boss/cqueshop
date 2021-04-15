package com.xielaoban.cqueshop.Service;

import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.AdminMenu;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-12 21:10
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service
 * @Description
 */
public interface AdminMenuService {
    List<AdminMenu> getAll();

    AdminMenu get(String id);

    List<AdminMenu> getAllEnabled();

    List<AdminMenu> getAllChild(String parentId);

    int add(AdminMenu adminMenu);

    int update(AdminMenu adminMenu);

    int del(String id);

    int updateMenuStatus(String id, Integer status);
}
