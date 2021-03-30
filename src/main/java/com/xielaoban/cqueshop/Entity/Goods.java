package com.xielaoban.cqueshop.Entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 蟹老板
 * @Date 2021-3-22 10:21
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Entity
 * @Description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    private String id;
    private String name;
    private String desc;
    private Image image;
    private double oriprice;
    private double nowprice;
    private Date createtime;
    private Date lastupdatetime;
    private Category category;
    private Integer status;

}
