package com.xielaoban.cqueshop.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Order.Order;
import com.xielaoban.cqueshop.Entity.Order.OrderItem;
import com.xielaoban.cqueshop.Service.OrderItemService;
import com.xielaoban.cqueshop.Service.OrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author 蟹老板
 * @Date 2021-4-6 21:52
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Controller
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    private static final Log log = LogFactory.getLog(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/getOrderByUserId/{userId}")
    public Result getOrderByUserId(@PathVariable String userId, @RequestBody JSONObject queryInfo) {
        try {
            log.info("获取用户订单：userid:" + userId);
            int currentPage = queryInfo.getInteger("currentPage");
            int pageSize = queryInfo.getInteger("pageSize");
            PageInfo<Order> orderPageInfo = orderService.getOrderByUserId(userId, pageSize, currentPage);
            log.info("获取到的用户订单：" + orderPageInfo.getList().toString());
            return Result.Success(orderPageInfo);
        } catch (Exception e) {
            log.info("获取用户订单出错了：userid:" + userId);
            return Result.Error();
        }
    }

    @PostMapping("/getOrderItemByOrderId/{orderId}")
    public Result getOrderItemByOrderId(@PathVariable String orderId, @RequestBody JSONObject queryInfo) {
        try {
            log.info("获取用户订单item：orderId:" + orderId + queryInfo.toJSONString());
            int currentPage = queryInfo.getInteger("currentPage");
            int pageSize = queryInfo.getInteger("pageSize");
            PageInfo<OrderItem> orderPageInfo = orderItemService.getByOrderId(orderId, pageSize, currentPage);
            log.info("获取到的用户订单详情" + orderPageInfo.getList().toString());
            return Result.Success(orderPageInfo);
        } catch (Exception e) {
            log.info("获取用户订单item出错了：orderId:" + orderId);
            return Result.Error();
        }
    }


}
