package com.xielaoban.cqueshop.Entity;

import com.xielaoban.cqueshop.Util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author 蟹老板
 * @Date 2021-4-10 18:10
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Entity
 * @Description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String id;
    private Double rate;
    private String content;
    private Date createTime;
    private User user;
    private Goods goods;
}
