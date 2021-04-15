package com.xielaoban.cqueshop.Service;

import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.Category;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2020/11/5 15:57
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service
 * @Description
 */
public interface CategoryService {
    PageInfo<Category> getAll(Integer pageSize, Integer currentPage);

    List<Category> getAllEnabled();

    int add(Category category);

    int update(Category category);

    int updateStatus(String id, Integer status);

    int del(String id);

    Category get(String catId);
}
