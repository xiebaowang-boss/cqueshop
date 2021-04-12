package com.xielaoban.cqueshop.Mapper;

import com.xielaoban.cqueshop.Entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-10 18:15
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Mapper
 * @Description
 */
@Mapper
public interface CommentMapper {
    int add(Comment comment);

    List<Comment> getByGoodsId(String GoodsId);
}
