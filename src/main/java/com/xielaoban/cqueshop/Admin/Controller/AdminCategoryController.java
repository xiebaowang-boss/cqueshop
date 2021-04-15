package com.xielaoban.cqueshop.Admin.Controller;

import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Category;
import com.xielaoban.cqueshop.Service.CategoryService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-14 21:16
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Admin.Controller
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {
    private static Log log = LogFactory.getLog(AdminCategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get/{catId}")
    public Result get(@PathVariable String catId) {
        try {
            log.info("获取商品分类:" + catId);
            Category category = categoryService.get(catId);
            return category != null ? Result.Success(category) : Result.Error();
        } catch (Exception e) {
            log.error("获取商品分类出错了!", e);
            return Result.Error();
        }
    }

    @GetMapping("/getAll")
    public Result getAll(Integer pageSize, Integer currentPage) {
        try {
            log.info("获取商品分类:" + pageSize + "--" + currentPage);
            PageInfo<Category> categoryPageInfo = categoryService.getAll(pageSize, currentPage);
            return categoryPageInfo.getList().size() > 0 ? Result.Success(categoryPageInfo) : Result.Error();
        } catch (Exception e) {
            log.error("获取商品分类出错了!", e);
            return Result.Error();
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody Category category) {
        try {
            log.info("添加商品分类：" + category);
            int result = categoryService.add(category);
            return result > 0 ? Result.Success(result) : Result.Error();
        } catch (Exception e) {
            log.error("添加商品分类出错了!" + category, e);
            return Result.Error();
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Category category) {
        try {
            log.info("更新商品分类：" + category);
            int result = categoryService.update(category);
            return result > 0 ? Result.Success(result) : Result.Error();
        } catch (Exception e) {
            log.error("更新商品分类出错了!" + category, e);
            return Result.Error();
        }
    }

    @GetMapping("/updateStatus")
    public Result updateStatus(String catId, Integer status) {
        try {
            log.info("更新商品状态：" + catId);
            int result = categoryService.updateStatus(catId, status);
            return result > 0 ? Result.Success(result) : Result.Error();
        } catch (Exception e) {
            log.error("更新商品状态出错了!" + catId, e);
            return Result.Error();
        }
    }

    @DeleteMapping("/del/{catId}")
    public Result del(@PathVariable String catId) {
        try {
            log.info("删除商品分类：" + catId);
            int result = categoryService.del(catId);
            return result > 0 ? Result.Success(result) : Result.Error();
        } catch (Exception e) {
            log.error("删除商品分类出错了!" + catId, e);
            return Result.Error();
        }
    }
}
