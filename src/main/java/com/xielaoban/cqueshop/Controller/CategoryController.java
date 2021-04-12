package com.xielaoban.cqueshop.Controller;

import com.alibaba.fastjson.JSON;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Category;
import com.xielaoban.cqueshop.Service.CategoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2020/11/5 15:54
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Controller
 * @Description
 */

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {
    private final Log log = LogFactory.getLog(CategoryController.class);
    private final String categeryRedisKey = "categoryRedisKey";

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/getAllEnabled")
    public Result findAll() {
        try {
            log.info("获取所有的商品分类");
            if (redisUtil.hasKey(categeryRedisKey)) {
                log.info("从Redis获取到的所有商品分类："+redisUtil.get(categeryRedisKey));
                return Result.Success(JSON.parse(redisUtil.get(categeryRedisKey).toString()));
            } else {
                List<Category> categoryList = categoryService.getAllEnabled();
                //60s过期
                redisUtil.set(categeryRedisKey, JSON.toJSONString(categoryList), 60);
                return Result.Success(categoryList);
            }
        }catch (Exception e){
            log.error("获取所有的商品分类出错了",e);
            return Result.Error();
        }

    }
}
