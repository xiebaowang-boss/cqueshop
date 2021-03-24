package com.xielaoban.cqueshop.Controller;

import com.xielaoban.cqueshop.Entity.Image;
import com.xielaoban.cqueshop.Service.CategoryService;
import com.xielaoban.cqueshop.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class IndexController {
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
    public List<Image> getIndexImg() {
        return imageService.getCarouselImg();
    }
}
