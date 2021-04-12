package com.xielaoban.cqueshop.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Entity.Comment;
import com.xielaoban.cqueshop.Mapper.CommentMapper;
import com.xielaoban.cqueshop.Mapper.OrderItemMapper;
import com.xielaoban.cqueshop.Service.CommentService;
import com.xielaoban.cqueshop.Util.DateUtil;
import com.xielaoban.cqueshop.Util.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-10 18:28
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service.Impl
 * @Description
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public int add(Comment comment) throws ParseException {
        comment.setId(GenerateUUID.getUUID());
        comment.setCreateTime(DateUtil.getCurrentDate());
        return commentMapper.add(comment);
    }

    @Override
    public PageInfo<Comment> getByGoodsId(String goodsId, Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<Comment> commentList = commentMapper.getByGoodsId(goodsId);
        PageInfo<Comment> commentPageInfo = new PageInfo<>(commentList);
        return commentPageInfo;
    }

    @Override
    public boolean changeIsCommAndSaveComment(String orderItemId, Comment comment) throws ParseException {
        int save = this.add(comment);
        int change = orderItemMapper.changeIsComm(orderItemId);
        return (save > 0) && (change > 0);
    }
}
