package com.xielaoban.cqueshop.Util;

import com.xielaoban.cqueshop.Common.UploadPathInfo;
import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;


/**
 * @Author 蟹老板
 * @Date 2021-3-19 16:35
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Util.UploadImg
 * @Description
 */
@Component
@Data
public class UploadImgUtil {
    @Autowired
    private UploadPathInfo uploadPathInfo;

    /**
     * 上传一张用户头像,返回一张用户头像的f访问url
     *
     * @Param
     * @Return
     */
    public static String uploadUserAvatar(MultipartFile file) throws IOException {
        String filename = GenerateUUID.getUUID();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());//获得文件扩展名
        String filenames = filename + "." + ext;
        file.transferTo(new File(UploadPathInfo.userAvatarPath + filenames));
        return UploadPathInfo.userAvatarNetPath + filenames;
    }

    /**
     * 上传一张商品头像,返回一张商品头像的访问url
     *
     * @Param
     * @Return
     */
    public static String uploadGoodsImg(MultipartFile file) throws IOException {
        String filename = GenerateUUID.getUUID();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());//获得文件扩展名
        String filenames = filename + "." + ext;
        file.transferTo(new File(UploadPathInfo.goodsImagePath + filenames));
        return UploadPathInfo.goodsImageNetPath + filenames;
    }

    /**
     * 上传一张首页轮播图,返回一张轮播图的首页地址
     *
     * @Param
     * @Return
     */
    public static String uploadCarouselImg(MultipartFile file) throws IOException {
        String filename = GenerateUUID.getUUID();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());//获得文件扩展名
        String filenames = filename + "." + ext;
        file.transferTo(new File(UploadPathInfo.carouselImagePath + filenames));
        return UploadPathInfo.carouselImageNetPath + filenames;
    }
}
