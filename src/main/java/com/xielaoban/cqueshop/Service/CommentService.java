package com.xielaoban.cqueshop.Service;

import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-10 18:27
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service
 * @Description
 */
public interface CommentService {
    int add(Comment comment) throws ParseException;

    PageInfo<Comment> getByGoodsId(String goodsId, Integer pageSize, Integer currentPage);

    @Transactional
    boolean changeIsCommAndSaveComment(String oederItemId, Comment comment) throws ParseException;
}
