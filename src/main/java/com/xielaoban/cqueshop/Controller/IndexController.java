package com.xielaoban.cqueshop.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Goods;
import com.xielaoban.cqueshop.Entity.Image;
import com.xielaoban.cqueshop.Service.CategoryService;
import com.xielaoban.cqueshop.Service.GoodsService;
import com.xielaoban.cqueshop.Service.ImageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-3-22 9:45
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Controller
 * @Description 首页相关api
 */
@CrossOrigin
@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {
    private final Log log = LogFactory.getLog(IndexController.class);
    private final String indexCarouselImgRedisKey = "indexCarouselImgRedisKey";
    private final String getUpdateGoodsImageRedisKey = "getUpdateGoodsImageRedisKey";
    private final String getUpdateGoodsRediskey = "getUpdateGoodsRedisKey";
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageService imageService;
    @Autowired
    GoodsService goodsService;
    /**
     * des:获取到首页轮播图
     *
     * @Param
     * @Return
     */
    @GetMapping("/getCarouselImg")
    public Result getIndexImg() {
        try {
            log.info("获取首页轮播图");
            if (redisUtil.hasKey(indexCarouselImgRedisKey)) {
                JSONArray data = JSON.parseArray((String) redisUtil.get(indexCarouselImgRedisKey));
                return Result.Success(data);
            } else {
                List<Image> imageList = imageService.getCarouselImg();
                redisUtil.set(indexCarouselImgRedisKey, JSON.toJSONString(imageList), 60);
                return Result.Success(imageList);
            }
        } catch (Exception e) {
            log.error("获取首页走马灯图片出错了！",e);
            return Result.Error();
        }

    }

    /**
    * @Description: 获取首页最新商品推荐图片 （作废：图片从最新的商品里面获取）
    * @Name: getUpdateGoodsImage
    * @Param: []
    * @return: com.xielaoban.cqueshop.Common.Result
    * @Author: 12105
    * @Date: 2021-4-1
    * @Time: 18:51        
    */
    @GetMapping("/getUpdateGoodsImage")
    public Result getUpdateGoodsImage() {
        try {
            log.info("获取首页最新商品推荐图片");
            if (redisUtil.hasKey(getUpdateGoodsImageRedisKey)){
                log.info("从Redis获取首页最新商品推荐图片");
                JSONArray data = JSON.parseArray(redisUtil.get(getUpdateGoodsImageRedisKey).toString());
                return Result.Success(data);
            }else{
                log.info("从数据库获取最新商品图片");
                List<Image> imageList = imageService.getUpdateGoodsImage();
                redisUtil.set(getUpdateGoodsImageRedisKey,JSON.toJSONString(imageList),60);
                return Result.Success(imageList);
            }
        } catch (Exception e) {
            log.info("获取首页最新商品推荐图片出错了",e);
            return Result.Error();
        }
    }

    @GetMapping("/getUpdateGoods")
    public Result getUpdateGoods(){
        try{
            log.info("获取首页最新商品");
            if (redisUtil.hasKey(getUpdateGoodsRediskey)){
                log.info("从Redis获取首页最新商品");
                JSONArray data = JSON.parseArray(redisUtil.get(getUpdateGoodsRediskey).toString());
                return Result.Success(data);
            }else {
                log.info("从数据库获取最新商品");
                List<Goods> goodsList = goodsService.getUpdateGoods();
                redisUtil.set(getUpdateGoodsRediskey,JSON.toJSONString(goodsList),60);
                return Result.Success(goodsList);
            }
        }catch (Exception e){
            log.info("获取首页最新商品出错了",e);
            return Result.Error();
        }
    }
}
