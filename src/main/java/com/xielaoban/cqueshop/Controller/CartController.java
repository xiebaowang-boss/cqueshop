package com.xielaoban.cqueshop.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Cart.Cart;
import com.xielaoban.cqueshop.Entity.Cart.CartItem;
import com.xielaoban.cqueshop.Entity.Goods;
import com.xielaoban.cqueshop.Service.GoodsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author 蟹老板
 * @Date 2021-4-2 12:46
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Controller
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController{
    private static final Log log = LogFactory.getLog(CartController.class);

    @Autowired
    GoodsService goodsService;

    @GetMapping("/get/{userId}")
    public Result getCart(@PathVariable String userId){
        try{
            log.info("获取用户购物车,用户ID："+userId);
            if (redisUtil.hasKey("cart-"+userId)){
                String cartStr = redisUtil.get("cart-"+userId).toString();
                Cart userCart = JSON.parseObject(cartStr, Cart.class);
                //因前端需要数组 所以将数据取出来组装进集合
                Map<String,CartItem> dataMap = userCart.getCartItemMap();
                List<CartItem> dataList = new ArrayList<>();
                for (String key : dataMap.keySet()){
                    dataList.add(dataMap.get(key));
                }
                log.info("获取到的用户购物车"+JSON.toJSONString(dataList));
                return Result.Success(dataList);
            }else {
                //未获取到购物车 表示用户尚未添加商品到购物车
                return Result.Error();
            }
        }catch (Exception e){
            log.info("获取用户购物车出错了,用户ID："+userId);
            return Result.Error();
        }
    }

    /**
    * @Description: 暂时作废
    * @Name: addGoodsToCart
    * @Param: [com.alibaba.fastjson.JSONObject]
    * @return: com.xielaoban.cqueshop.Common.Result
    * @Author: 12105
    * @Date: 2021-4-2
    * @Time: 16:19        
    */
    @PostMapping("/addGoodsToCart")
    public Result addGoodsToCart(@RequestBody JSONObject addInfo){
        try{
            log.info("添加商品到购物车：addInfo"+addInfo.toJSONString());
            String goodsId = addInfo.getString("goodsid");
            String userId = addInfo.getString("userId");
            if (redisUtil.hasKey("cart-"+userId)){
                String cartStr = redisUtil.get("cart-"+userId).toString();
                Cart userCart = JSON.parseObject(cartStr, Cart.class);
                Goods goods = goodsService.get(goodsId);
                userCart.addGoods(goods);
                //7天过期
                redisUtil.set("cart-"+userId,JSON.toJSONString(userCart),60*60*24*7);
                return Result.Success(userCart);
            }else {
                //未获取到购物车 表示用户尚未添加商品到购物车
                log.info("未获取到购物车 表示用户尚未添加商品到购物车");
                Cart userCart = new Cart();
                userCart.addGoods(goodsService.get(goodsId));
                //7天过期
                redisUtil.set("cart-"+userId,JSON.toJSONString(userCart),60*60*24*7);
                return Result.Success(userCart);
            }
        }catch (Exception e){
            log.error("添加商品到购物车出错了：addInfo"+addInfo.toJSONString(),e);
            return Result.Error();
        }
    }

    @PostMapping("/delGoodsFromCart")
    public Result delGoodsFromCart(@RequestBody JSONObject delInfo){
        try{
            log.info("从购物车删除商品：delInfo"+delInfo.toJSONString());
            String goodsId = delInfo.getString("goodsId");
            String userId = delInfo.getString("userId");
            if (redisUtil.hasKey("cart-"+userId)){
                String cartStr = redisUtil.get("cart-"+userId).toString();
                Cart userCart = JSON.parseObject(cartStr, Cart.class);
                Goods goods = goodsService.get(goodsId);
                userCart.addGoods(goods);
                //7天过期
                redisUtil.set("cart-"+userId,JSON.toJSONString(userCart),60*60*24*7);
                return Result.Success(userCart);
            }
            return Result.Error();
        }catch (Exception e){
            log.error("添加商品到购物车出错了：addInfo"+delInfo.toJSONString(),e);
            return Result.Error();
        }
    }

    @PostMapping("/setGoodsNum")
    public Result setGoodsNum(@RequestBody JSONObject info){
        try {
            log.info("设置商品数量");
            String goodsId = info.getString("goodsId");
            String userToken = info.getString("userToken");
            Integer goodsNum = info.getInteger("goodsNum");
            if (redisUtil.hasKey("cart-"+userToken)){
                String cartStr = redisUtil.get("cart-"+userToken).toString();
                Cart userCart = JSON.parseObject(cartStr, Cart.class);
                Goods goods = goodsService.get(goodsId);
                userCart.setGoodsNum(goods,goodsNum);
                //7天过期
                redisUtil.set("cart-"+userToken,JSON.toJSONString(userCart),60*60*24*7);
                return Result.Success(userCart);
            }
            return Result.Error("购物车信息不存在");
        }catch (Exception e){
            log.error("设置商品数量出错了");
            return Result.Error("服务器错误",null);
        }
    }

    @PostMapping("/getGoodsTotalNum")
    public Result getGoodsTotalNum(@RequestBody JSONObject user){
        try{
            log.info("获取购物车商品总数");
            String userToken = user.getString("token");
            if (redisUtil.hasKey("cart-"+userToken)){
                String cartStr = redisUtil.get("cart-"+userToken).toString();
                Cart userCart = JSON.parseObject(cartStr, Cart.class);
                Integer num = userCart.getTotalNum();
                return Result.Success(num);
            }
            return Result.Error();
        }catch (Exception e){
            log.info("获取购物车商品总数出错了",e);
            return Result.Error();
        }
    }

    @GetMapping("/del/{userToken}")
    public Result delCart(@PathVariable String userToken){
        try {
            log.info("清空用户购物车！user："+userToken);
            if (redisUtil.hasKey("cart-"+userToken)){
                redisUtil.deleteKey("cart-"+userToken);
                return Result.Success();
            }else {
                return Result.Success();
            }
        }catch (Exception e){
            log.error("清空用户购物车失败了！user:"+userToken,e);
            return Result.Error();
        }
    }
}
