package com.xielaoban.cqueshop.Entity;

import lombok.*;

import java.io.Serializable;

/**
 * @Author 蟹老板
 * @Date 2021-3-22 10:19
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Entity
 * @Description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable {
    private String id;
    private String name;
    private String url;
    private String desc;
    private Integer status;
}
