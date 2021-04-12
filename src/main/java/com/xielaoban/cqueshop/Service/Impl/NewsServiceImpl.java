package com.xielaoban.cqueshop.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.News;
import com.xielaoban.cqueshop.Mapper.NewsMapper;
import com.xielaoban.cqueshop.Service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-11 0:13
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service.Impl
 * @Description
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public int add(News news) {
        return newsMapper.add(news);
    }

    @Override
    public int update(News news) {
        return newsMapper.update(news);
    }

    @Override
    public PageInfo<News> getAllEnabled(Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<News> newsList = newsMapper.getAllEnabled();
        PageInfo<News> newsPageInfo = new PageInfo<>(newsList);
        return newsPageInfo;
    }
}
