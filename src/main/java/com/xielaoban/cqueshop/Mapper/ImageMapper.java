package com.xielaoban.cqueshop.Mapper;

import com.xielaoban.cqueshop.Entity.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-3-22 10:40
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Mapper
 * @Description
 */
@Mapper
public interface ImageMapper {
    Image getImg(String id);

    List<Image> getAll();

    List<Image> getCarouselImg();

    List<Image> getUpdateGoodsImage();

    int save(Image image);


}
