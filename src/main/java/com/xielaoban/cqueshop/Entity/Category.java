package com.xielaoban.cqueshop.Entity;

import lombok.*;

/**
 * @Author 蟹老板
 * @Date 2020/11/5 15:52
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Entity
 * @Description 商品的分类
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String id;
    private String categoryId;
    private String categoryName;
    private Integer status;
}
