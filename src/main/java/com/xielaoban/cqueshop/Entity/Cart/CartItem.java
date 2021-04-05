package com.xielaoban.cqueshop.Entity.Cart;

import com.xielaoban.cqueshop.Entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 蟹老板
 * @Date 2021-4-2 12:15
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Entity.Cart
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Goods goods;
    private Integer num;
    private Double totalPrice;

    public void setGoodsNum(Integer goodsNum){
        this.num += goodsNum;
        this.totalPrice = (goods.getNowprice()*this.num);
    }

    public void addOne() {
        this.num++;
        this.totalPrice = (goods.getNowprice()*this.num);
    }
}
