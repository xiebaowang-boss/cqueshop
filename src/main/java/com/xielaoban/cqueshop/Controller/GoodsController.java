package com.xielaoban.cqueshop.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Goods;
import com.xielaoban.cqueshop.Service.GoodsService;
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
    GoodsService goodsService;

    @GetMapping("/{goodsId}")
    public Result get(@PathVariable String goodsId) {
        try {
            log.info("根据商品id查询商品的信息：id=" + goodsId);
            if (redisUtil.hasKey("goodsid-"+goodsId)){
                String goodsStr = redisUtil.get("goodsid-"+goodsId).toString();
                Goods goods = JSON.parseObject(goodsStr,Goods.class);
                return Result.Success(goods);
            }else {
                Goods goods = goodsService.get(goodsId);
                redisUtil.set("goodsid-"+goodsId,JSON.toJSONString(goods),30);
                log.info("根据商品id查询商品的信息：" + goods);
                return Result.Success(goods);
            }
        } catch (Exception e) {
            log.error("根据商品id查询商品的信息出错了：id=" + goodsId, e);
            return Result.Error();
        }
    }

    @GetMapping("/getAllEnabled")
    public Result getAllEnabled() {
        try {
            log.info("获取所有的商品信息");
            List<Goods> goodsList = goodsService.getAllEnabled();
            log.info("获取到的所有的商品信息："+goodsList.toString());
            return Result.Success(goodsList);
        } catch (Exception e) {
            log.error("获取所有的商品信息出错了", e);
            return Result.Error();
        }
    }

    @GetMapping("/getByCategoryId")
    public Result getByCategoryId(String categoryId) {
        try {
            log.info("根据商品类别ID获取所有的商品信息：categoryId=" + categoryId);
            List<Goods> goodsList = goodsService.getByCategoryId(categoryId);
            return Result.Success(goodsList);
        } catch (Exception e) {
            log.error("根据商品类别ID获取所有的商品信息出错了：categoryId=" + categoryId, e);
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
    public Result getHot(){
        try {
            log.info("获取热门商品");
            if (redisUtil.hasKey(getHotRedisKey)){
                JSONArray data = JSON.parseArray(redisUtil.get(getHotRedisKey).toString());
                log.info("从Redis获取的热门商品个数:"+data.size());
                return Result.Success(data);
            }else {
                List<Goods> goodsList = goodsService.getHot();
                redisUtil.set(getHotRedisKey,JSON.toJSONString(goodsList),60);
                log.info("从数据库获取到的热门商品个数："+goodsList.size());
                return Result.Success(goodsList);
            }
        }catch (Exception e){
            log.error("获取热门商品出错了！",e);
            return Result.Error();
        }
    }
}
