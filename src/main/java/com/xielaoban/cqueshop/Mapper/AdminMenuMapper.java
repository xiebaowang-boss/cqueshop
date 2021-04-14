package com.xielaoban.cqueshop.Mapper;

import com.xielaoban.cqueshop.Entity.AdminMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-12 20:28
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Mapper
 * @Description
 */
@Mapper
public interface AdminMenuMapper {
    List<AdminMenu> getAllEnabled();

    List<AdminMenu> getAllChild(String parentId);

    int addMenu(AdminMenu adminMenu);

    int update(AdminMenu adminMenu);

    int del(String id);
}
