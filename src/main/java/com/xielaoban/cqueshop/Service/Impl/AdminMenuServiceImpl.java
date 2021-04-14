package com.xielaoban.cqueshop.Service.Impl;

import com.xielaoban.cqueshop.Entity.AdminMenu;
import com.xielaoban.cqueshop.Mapper.AdminMenuMapper;
import com.xielaoban.cqueshop.Service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-12 21:10
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service.Impl
 * @Description
 */
@Service
public class AdminMenuServiceImpl implements AdminMenuService {
    @Autowired
    private AdminMenuMapper adminMenuMapper;

    @Override
    public List<AdminMenu> getAllEnabled() {
        return adminMenuMapper.getAllEnabled();
    }

    @Override
    public List<AdminMenu> getAllChild(String parentId) {
        return adminMenuMapper.getAllChild(parentId);
    }

    @Override
    public int addMenu(AdminMenu adminMenu) {
        return adminMenuMapper.addMenu(adminMenu);
    }

    @Override
    public int update(AdminMenu adminMenu) {
        return adminMenuMapper.update(adminMenu);
    }

    @Override
    public int del(String id) {
        return adminMenuMapper.del(id);
    }
}
