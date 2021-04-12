package com.xielaoban.cqueshop.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Comment;
import com.xielaoban.cqueshop.Service.CommentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 蟹老板
 * @Date 2021-4-10 18:29
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Controller
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
    private static final Log log = LogFactory.getLog(CommentController.class);

    @Autowired
    private CommentService commentService;

    @PostMapping("/addFromOrderItem/{orderItemId}")
    public Result addFromOrderItem(@PathVariable String orderItemId, @RequestBody Comment comment) {
        try {
            log.info("获取到的订单详情ID：" + orderItemId);
            log.info("添加商品评论" + comment);
            //将评论写到数据库当中去然后将订单详情的评论状态更改
            boolean result = commentService.changeIsCommAndSaveComment(orderItemId, comment);
            return result ? Result.Success() : Result.Error();
        } catch (Exception e) {
            log.error("添加商品评论出错了！", e);
            return Result.Error();
        }
    }

    @PostMapping("/getByGoodsId/{goodsId}")
    public Result getByGoodsId(@PathVariable String goodsId, @RequestBody JSONObject queryInfo) {
        try {
            log.info("获取商品评论信息：goodsId-" + goodsId);
            int pageSize = queryInfo.getInteger("pageSize");
            int currentPage = queryInfo.getInteger("currentPage");
            PageInfo<Comment> commentPageInfo = commentService.getByGoodsId(goodsId, pageSize, currentPage);
            return Result.Success(commentPageInfo);
        } catch (Exception e) {
            log.info("获取商品评论信息失败了：goodsId-" + goodsId, e);
            return Result.Error();
        }
    }
}
