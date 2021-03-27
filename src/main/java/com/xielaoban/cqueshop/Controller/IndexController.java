package com.xielaoban.cqueshop.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Image;
import com.xielaoban.cqueshop.Service.CategoryService;
import com.xielaoban.cqueshop.Service.ImageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageService imageService;

    /**
     * des:获取到首页轮播图
     *
     * @Param
     * @Return
     */
    @RequestMapping("/getCarouselImg")
    public Result getIndexImg() {
        if (redisUtil.hasKey(indexCarouselImgRedisKey)) {
            JSONArray data = JSON.parseArray((String) redisUtil.get(indexCarouselImgRedisKey));
            return Result.Success(data);
        } else {
            List<Image> imageList = imageService.getCarouselImg();
            redisUtil.set(indexCarouselImgRedisKey, JSON.toJSONString(imageList), 60);
            return Result.Success(imageList);
        }
    }
}
