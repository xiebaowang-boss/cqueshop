package com.xielaoban.cqueshop.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.AdminMenu;
import com.xielaoban.cqueshop.Mapper.AdminMenuMapper;
import com.xielaoban.cqueshop.Service.AdminMenuService;
import com.xielaoban.cqueshop.Util.GenerateUUID;
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
    public List<AdminMenu> getAll() {
        return adminMenuMapper.getAll();
    }

    @Override
    public AdminMenu get(String id) {
        return adminMenuMapper.get(id);
    }

    @Override
    public List<AdminMenu> getAllEnabled() {
        return adminMenuMapper.getAllEnabled();
    }

    @Override
    public List<AdminMenu> getAllChild(String parentId) {
        return adminMenuMapper.getAllChild(parentId);
    }

    @Override
    public int add(AdminMenu adminMenu) {
        adminMenu.setId(GenerateUUID.getUUID());
        adminMenu.setStatus(1);
        return adminMenuMapper.add(adminMenu);
    }

    @Override
    public int update(AdminMenu adminMenu) {
        return adminMenuMapper.update(adminMenu);
    }

    @Override
    public int del(String id) {
        return adminMenuMapper.del(id);
    }

    @Override
    public int updateMenuStatus(String id, Integer status) {
        return adminMenuMapper.updateStatus(id, status);
    }
}
