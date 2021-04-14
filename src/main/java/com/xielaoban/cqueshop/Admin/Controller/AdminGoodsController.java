package com.xielaoban.cqueshop.Admin.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Controller.GoodsController;
import com.xielaoban.cqueshop.Entity.Goods;
import com.xielaoban.cqueshop.Service.GoodsService;
import com.xielaoban.cqueshop.Service.OrderItemService;
import com.xielaoban.cqueshop.Service.OrderService;
import com.xielaoban.cqueshop.Service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 蟹老板
 * @Date 2021-4-13 15:30
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Admin.Controller
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/goods")
public class AdminGoodsController {
    private static final Log log = LogFactory.getLog(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/getAll{query}")
    public Result getAllEnabled(@PathVariable(required = false) String query, @RequestBody JSONObject queryInfo) {
        try {
            log.info("获取所有的商品信息：" + query + queryInfo.toJSONString());
            int pageSize = queryInfo.getInteger("pageSize");
            int currentPage = queryInfo.getInteger("currentPage");
            PageInfo<Goods> goodsPageInfo = goodsService.getAll(query, pageSize, currentPage);
            log.info("获取到的所有的商品信息：" + goodsPageInfo.getList().toString());
            return Result.Success(goodsPageInfo);
        } catch (Exception e) {
            log.error("获取所有的商品信息出错了", e);
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

    @PostMapping("/update")
    public Result update(@RequestBody Goods goods) {
        try {
            log.info("更新商品获取到的商品：" + goods);
            int result = goodsService.update(goods);
            if (result > 0) {
                return Result.Success("更新商品成功！", 1);
            } else {
                return Result.Error("更新商品失败！", 0);
            }
        } catch (Exception e) {
            log.error("更新商品出错了：" + goods, e);
            return Result.Error("更新商品失败！服务器错误！", 0);
        }
    }

    @GetMapping("/updateGoodsStatus")
    public Result updateGoodsStatus(String goodsId, Integer status) {
        try {
            log.info("修改商品状态获取到的商品：" + goodsId);
            int result = goodsService.updateStatus(goodsId, status);
            if (result > 0) {
                return Result.Success(result);
            } else {
                return Result.Error(result);
            }
        } catch (Exception e) {
            log.error("修改商品状态出错了：" + goodsId, e);
            return Result.Error();
        }
    }

    @DeleteMapping("/del/{goodsId}")
    public Result del(@PathVariable String goodsId) {
        try {
            log.info("删除商品获取到的商品：" + goodsId);
            int result = goodsService.del(goodsId);
            if (result > 0) {
                return Result.Success(result);
            } else {
                return Result.Error(result);
            }
        } catch (Exception e) {
            log.error("删除商品出错了：" + goodsId, e);
            return Result.Error();
        }
    }
}
