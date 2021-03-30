package com.xielaoban.cqueshop.Controller;

import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Goods;
import com.xielaoban.cqueshop.Service.GoodsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;
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

    @Autowired
    GoodsService goodsService;

    @GetMapping("/{goodsId}")
    public Result get(String goodsId) {
        try {
            log.info("根据商品id查询商品的信息：id=" + goodsId);
            Goods goods = goodsService.get(goodsId);
            log.info("根据商品id查询商品的信息：" + goods);
            return Result.Success(goods);
        } catch (Exception e) {
            log.error("根据商品id查询商品的信息出错了：id=" + goodsId, e);
            return Result.Error();
        }
    }

    @GetMapping("/getAll")
    public Result getAll() {
        try {
            log.info("获取所有的商品信息");
            List<Goods> goodsList = goodsService.getAll();
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
            log.info("添加商品：" + goods);
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
}
