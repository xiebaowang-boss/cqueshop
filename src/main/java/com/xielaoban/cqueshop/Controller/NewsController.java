package com.xielaoban.cqueshop.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.News;
import com.xielaoban.cqueshop.Mapper.NewsMapper;
import com.xielaoban.cqueshop.Service.NewsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 蟹老板
 * @Date 2021-4-11 0:14
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Controller
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsController extends BaseController {
    private static final Log log = LogFactory.getLog(NewsController.class);
    @Autowired
    private NewsService newsService;

    @PostMapping("/getAllEnabled")
    public Result getAllEnabled(@RequestBody JSONObject queryInfo) {
        try {
            log.info("获取公告信息！");
            int pageSize = queryInfo.getInteger("pageSize");
            int currentPage = queryInfo.getInteger("currentPage");
            PageInfo<News> newsPageInfo = newsService.getAllEnabled(pageSize, currentPage);
            return Result.Success(newsPageInfo);
        } catch (Exception e) {
            log.info("获取公告信息出错了！", e);
            return Result.Error();
        }
    }

    @PostMapping("/add")
    public Result getAllEnabled(@RequestBody News news) {
        try {
            log.info("添加公告信息！");
            int result = newsService.add(news);
            if (result > 0) {
                return Result.Success();
            } else {
                return Result.Error();
            }
        } catch (Exception e) {
            log.info("添加公告信息出错了！", e);
            return Result.Error();
        }
    }

}
