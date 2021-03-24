package com.xielaoban.cqueshop.Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author 蟹老板
 * @Date 2021-3-22 17:58
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Common
 * @Description
 */
@Component
public class UploadPathInfo {
    //用户头像上传地址
    public static String userAvatarPath;
    //用户头像网络地址
    public static String userAvatarNetPath;
    //商品图片上传地址
    public static String goodsImagePath;
    //商品图片网络地址
    public static String goodsImageNetPath;
    //首页轮播图上传地址
    public static String carouselImagePath;
    //首页轮播图网络地址
    public static String carouselImageNetPath;

    @Value("${user.avatar-path}")
    public void setUserAvatarPath(String userAvatarPath) {
        UploadPathInfo.userAvatarPath = userAvatarPath;
    }

    @Value("${user.avatar-net-path}")
    public void setUserAvatarNetPath(String userAvatarNetPath) {
        UploadPathInfo.userAvatarNetPath = userAvatarNetPath;
    }

    @Value("${goods.images-path}")
    public void setGoodsImagePath(String goodsImagePath) {
        UploadPathInfo.goodsImagePath = goodsImagePath;
    }

    @Value("${goods.images-net-path}")
    public void setGoodsImageNetPath(String goodsImageNetPath) {
        UploadPathInfo.goodsImageNetPath = goodsImageNetPath;
    }

    @Value("${index.carousel-images-path}")
    public void setCarouselImagePath(String carouselImagePath) {
        UploadPathInfo.carouselImagePath = carouselImagePath;
    }

    @Value("${index.carousel-images-net-path}")
    public void setCarouselImageNetPath(String carouselImageNetPath) {
        UploadPathInfo.carouselImageNetPath = carouselImageNetPath;
    }
}
