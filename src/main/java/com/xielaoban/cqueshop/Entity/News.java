package com.xielaoban.cqueshop.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author 蟹老板
 * @Date 2021-4-10 23:59
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Entity
 * @Description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private String id;
    private String title;
    private String content;
    private Date createTime;
    private Integer status;
}
