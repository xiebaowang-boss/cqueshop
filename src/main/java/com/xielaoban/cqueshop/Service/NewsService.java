package com.xielaoban.cqueshop.Service;

import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.News;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-11 0:12
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service
 * @Description
 */
public interface NewsService {
    int add(News news);

    int update(News news);

    PageInfo<News> getAllEnabled(Integer pageSize, Integer currentPage);
}
