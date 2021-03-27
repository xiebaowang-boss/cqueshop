package com.xielaoban.cqueshop.Controller;

import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Image;
import com.xielaoban.cqueshop.Entity.User;
import com.xielaoban.cqueshop.Service.ImageService;
import com.xielaoban.cqueshop.Util.GenerateUUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.xielaoban.cqueshop.Util.UploadImgUtil;

import java.io.IOException;

/**
 * @Author 蟹老板
 * @Date 2021-3-22 15:23
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Controller
 * @Description
 */
@RestController
@CrossOrigin
@RequestMapping("/uploadImg")
public class UploadImageController extends BaseController {
    private static Log log = LogFactory.getLog(UploadImageController.class);
    @Autowired
    private ImageService imageService;

    //上传用户头像
    @PostMapping("/uploadUserAvatar")
    public Result uploadUserAvatar(MultipartFile file) {
        try {
            String newAvatarPath = UploadImgUtil.uploadUserAvatar(file);
            Image newAvatar = new Image(GenerateUUID.getUUID(), "", newAvatarPath, "", 1);
            int result = imageService.save(newAvatar);
            return Result.Success(newAvatar);
        } catch (Exception e) {
            log.error("上传用户头像出错了", e);
            return Result.Error();
        }
    }

    //上传首页轮播图
    @PostMapping("/uploadCarouselImg")
    public Result uploadCarouselImg(MultipartFile file) {
        try {
            log.info("首页轮播图上传");
            String newCarousePath = UploadImgUtil.uploadCarouselImg(file);
            Image newCarouse = new Image(GenerateUUID.getUUID(), "", newCarousePath, "", 1);
            int result = imageService.save(newCarouse);
            return Result.Success(newCarouse);
        } catch (Exception e) {
            log.error("首页轮播图上传出错了", e);
            return Result.Error();
        }
    }

    //上传首页轮播图
    @PostMapping("/uploadGoodsImg")
    public Result uploadGoodsImg(MultipartFile file) {
        try {
            log.info("商品图像上传");
            String goodsPath = UploadImgUtil.uploadGoodsImg(file);
            Image goodsImg = new Image(GenerateUUID.getUUID(), "", goodsPath, "", 1);
            imageService.save(goodsImg);
            return Result.Success(goodsImg);
        } catch (Exception e) {
            log.error("商品图像上传出错了", e);
            return Result.Error();
        }
    }
}
