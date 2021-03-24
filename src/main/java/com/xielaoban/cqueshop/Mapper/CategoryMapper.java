package com.xielaoban.cqueshop.Mapper;

import com.xielaoban.cqueshop.Entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2020/11/5 15:59
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Mapper
 * @Description
 */

@Mapper
public interface CategoryMapper {
    List<Category> getAll();
}
