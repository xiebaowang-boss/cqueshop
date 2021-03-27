package com.xielaoban.cqueshop.Controller;

import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Category;
import com.xielaoban.cqueshop.Service.CategoryService;
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
    private final String categeryRedisKey = "categoryRedisKey";

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/findAll")
    public Result findAll() {
        if (redisUtil.hasKey(categeryRedisKey)) {
            return Result.Success(redisUtil.get(categeryRedisKey));
        } else {
            List<Category> categoryList = categoryService.getAll();
            //60s过期
            redisUtil.set(categeryRedisKey, categoryList.toString(), 60);
            return Result.Success(categoryList);
        }
    }
}
