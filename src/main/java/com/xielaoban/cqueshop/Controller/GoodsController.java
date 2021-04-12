package com.xielaoban.cqueshop.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Goods;
import com.xielaoban.cqueshop.Entity.Order.Order;
import com.xielaoban.cqueshop.Entity.Order.OrderItem;
import com.xielaoban.cqueshop.Entity.Order.SendType;
import com.xielaoban.cqueshop.Service.GoodsService;
import com.xielaoban.cqueshop.Service.OrderItemService;
import com.xielaoban.cqueshop.Service.OrderService;
import com.xielaoban.cqueshop.Service.UserService;
import com.xielaoban.cqueshop.Util.DateUtil;
import com.xielaoban.cqueshop.Util.GenerateUUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-3-30 11:32
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Controller
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {
    private final Log log = LogFactory.getLog(GoodsController.class);
    private final String getHotRedisKey = "getHotRedisKey";

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/{goodsId}")
    public Result get(@PathVariable String goodsId) {
        try {
            log.info("根据商品id查询商品的信息：id=" + goodsId);
            if (redisUtil.hasKey("goodsid-" + goodsId)) {
                String goodsStr = redisUtil.get("goodsid-" + goodsId).toString();
                Goods goods = JSON.parseObject(goodsStr, Goods.class);
                return Result.Success(goods);
            } else {
                Goods goods = goodsService.get(goodsId);
                redisUtil.set("goodsid-" + goodsId, JSON.toJSONString(goods), 30);
                log.info("根据商品id查询商品的信息：" + goods);
                return Result.Success(goods);
            }
        } catch (Exception e) {
            log.error("根据商品id查询商品的信息出错了：id=" + goodsId, e);
            return Result.Error();
        }
    }

    @PostMapping("/getAllEnabled")
    public Result getAllEnabled(@RequestBody JSONObject queryInfo) {
        try {
            log.info("获取所有的商品信息");
            int pageSize = queryInfo.getInteger("pageSize");
            int currentPage = queryInfo.getInteger("currentPage");
            PageInfo<Goods> goodsPageInfo = goodsService.getAllEnabled(pageSize, currentPage);
            log.info("获取到的所有的商品信息：" + goodsPageInfo.getList().toString());
            return Result.Success(goodsPageInfo);
        } catch (Exception e) {
            log.error("获取所有的商品信息出错了", e);
            return Result.Error();
        }
    }

    @PostMapping("/getByCategoryId")
    public Result getByCategoryId(@RequestBody JSONObject queryInfo) {
        try {
            String categoryId = queryInfo.getString("categoryId");
            int pageSize = queryInfo.getInteger("pageSize");
            int currentPage = queryInfo.getInteger("currentPage");
            //log.info("根据商品类别ID获取所有的商品信息：categoryId=" + categoryId);
            PageInfo<Goods> goodsPageInfo = null;
            if ("0".equals(categoryId)) {
                goodsPageInfo = goodsService.getAllEnabled(pageSize, currentPage);
            } else {
                goodsPageInfo = goodsService.getByCategoryId(categoryId, pageSize, currentPage);
            }
            return Result.Success(goodsPageInfo);
        } catch (Exception e) {
            log.error("根据商品类别ID获取所有的商品信息出错了：categoryId=" + queryInfo.getString("categoryId"), e);
            return Result.Error();
        }
    }

    @PostMapping("/search")
    public Result search(@RequestBody JSONObject queryInfo) {
        try {
            log.info("搜索商品，条件信息：" + queryInfo.toJSONString());
            String keywords = queryInfo.getString("keywords");
            int pageSize = queryInfo.getInteger("pageSize");
            int currentPage = queryInfo.getInteger("currentPage");
            PageInfo<Goods> goodsPageInfo = goodsService.search(keywords, pageSize, currentPage);
            return Result.Success(goodsPageInfo);
        } catch (Exception e) {
            log.error("搜索商品出错了，条件信息：" + queryInfo.toJSONString());
            return Result.Error();
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody Goods goods) {
        try {
            log.info("添加商品获取到的商品：" + goods);
            int result = goodsService.add(goods);
            if (result > 0) {
                return Result.Success("添加商品成功！", 1);
            } else {
                return Result.Error("添加商品失败！", 0);
            }
        } catch (Exception e) {
            log.error("添加商品出错了：" + goods, e);
            return Result.Error("添加商品失败！服务器错误！", 0);
        }
    }

    /***
     * @Description: 获取热门商品，返回销量最高的6个商品
     * @Name: getHotGoods
     * @Param: []
     * @return: com.xielaoban.cqueshop.Common.Result
     * @Author: 12105
     * @Date: 2021-3-31
     * @Time: 16:13
     */
    @GetMapping("/getHot")
    public Result getHot() {
        try {
            log.info("获取热门商品");
            if (redisUtil.hasKey(getHotRedisKey)) {
                JSONArray data = JSON.parseArray(redisUtil.get(getHotRedisKey).toString());
                log.info("从Redis获取的热门商品个数:" + data.size());
                return Result.Success(data);
            } else {
                List<Goods> goodsList = goodsService.getHot();
                redisUtil.set(getHotRedisKey, JSON.toJSONString(goodsList), 60);
                log.info("从数据库获取到的热门商品个数：" + goodsList.size());
                return Result.Success(goodsList);
            }
        } catch (Exception e) {
            log.error("获取热门商品出错了！", e);
            return Result.Error();
        }
    }

    @PostMapping("/buyOne/{goodsId}")
    public Result buyOne(@PathVariable String goodsId, @RequestBody JSONObject orderInfo) {
        try {
            log.info("购买一个商品：" + goodsId + orderInfo.toJSONString());
            //填充订单相关信息
            int num = orderInfo.getInteger("num");
            String orderId = GenerateUUID.getUUID();
            String userId = orderInfo.getString("userId");
            String sendTypeId = orderInfo.getString("sendTypeId");
            Goods goods = goodsService.get(goodsId);
            log.info("获取到的商品信息" + goods);
            Order order = new Order();
            order.setId(orderId);
            order.setOrderUser(userService.get(userId));
            order.setCreateDate(DateUtil.getCurrentDate());
            order.setStatus(1);
            order.setTotalNum(num);
            order.setTotalPrice(num * goods.getNowprice());
            order.setName(orderInfo.getString("name"));
            order.setAddress(orderInfo.getString("address"));
            order.setPhone(orderInfo.getString("phone"));
            order.setRemarks(orderInfo.getString("remarks"));
            //设置订单送货方式
            order.setSendType(new SendType(sendTypeId, null, 1));
            //填充订单详情相关信息
            OrderItem orderItem = new OrderItem(GenerateUUID.getUUID(), goods, num, num * goods.getNowprice(), orderId, 1);
            //将订单和订单项保存至数据库中
            boolean result = orderService.saveOrderAndOrderItem(order, orderItem);
            //如果成功插入返回成功 否则返回失败
            return result ? Result.Success() : Result.Error();
        } catch (Exception e) {
            log.error("用户购买商品出错了", e);
            return Result.Error();
        }
    }
}
