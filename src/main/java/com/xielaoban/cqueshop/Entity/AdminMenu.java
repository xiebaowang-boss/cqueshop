package com.xielaoban.cqueshop.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-12 20:26
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Entity
 * @Description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminMenu {
    private String id;
    private String name;
    private String path;
    private String parent;
    private Integer type;
    private Integer status;
    private List<AdminMenu> menuItems;
}
