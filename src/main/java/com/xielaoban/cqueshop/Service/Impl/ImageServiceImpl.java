package com.xielaoban.cqueshop.Service.Impl;

import com.xielaoban.cqueshop.Entity.Image;
import com.xielaoban.cqueshop.Mapper.ImageMapper;
import com.xielaoban.cqueshop.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-3-22 10:55
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service.Impl
 * @Description
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageMapper imageMapper;

    @Override
    public Image getImg(String id) {
        return imageMapper.getImg(id);
    }

    @Override
    public List<Image> getAll() {
        return imageMapper.getAll();
    }

    @Override
    public List<Image> getCarouselImg() {
        return imageMapper.getCarouselImg();
    }

    @Override
    public List<Image> getUpdateGoodsImage() {
        return imageMapper.getUpdateGoodsImage();
    }

    @Override
    public int save(Image image) {
        return imageMapper.save(image);
    }
}
