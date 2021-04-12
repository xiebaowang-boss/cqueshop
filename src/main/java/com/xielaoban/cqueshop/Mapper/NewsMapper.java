package com.xielaoban.cqueshop.Mapper;

import com.xielaoban.cqueshop.Entity.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-11 0:02
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Mapper
 * @Description
 */
@Mapper
public interface NewsMapper {
    List<News> getAllEnabled();

    int add(News news);

    int update(News news);
}
