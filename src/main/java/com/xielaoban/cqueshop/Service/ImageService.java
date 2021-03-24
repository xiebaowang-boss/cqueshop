package com.xielaoban.cqueshop.Service;

import com.xielaoban.cqueshop.Entity.Image;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-3-22 10:53
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service
 * @Description
 */
public interface ImageService {
    Image getImg(String id);

    List<Image> getAll();

    List<Image> getCarouselImg();

    int save(Image image);
}
