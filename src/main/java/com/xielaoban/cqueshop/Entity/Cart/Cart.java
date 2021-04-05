package com.xielaoban.cqueshop.Entity.Cart;

import com.xielaoban.cqueshop.Entity.Goods;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 蟹老板
 * @Date 2021-4-2 11:51
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Entity
 * @Description
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    @Setter
    private Double totalPrice = 0.00;
    @Setter
    private Integer totalNum = 0;
    @Setter
    @Getter
    private Map<String, CartItem> cartItemMap = new HashMap<>();

    public Double getTotalPrice() {
        for (String key : cartItemMap.keySet()) {
            this.totalPrice += cartItemMap.get(key).getTotalPrice();
        }
        return totalPrice;
    }

    public Integer getTotalNum() {
        int num = 0;
        for (String key : cartItemMap.keySet()) {
            num += cartItemMap.get(key).getNum();
        }
        this.totalNum = num;
        return totalNum;
    }

    public Cart addGoods(Goods goods) {
        String goodsId = goods.getId();
        if (this.cartItemMap.containsKey(goodsId)) {
            CartItem cartItemNew = this.cartItemMap.get(goodsId);
            cartItemNew.addOne();
            this.cartItemMap.replace(goodsId, cartItemNew);
        } else {
            CartItem cartItemNew = new CartItem(goods, 1, goods.getNowprice());
            this.cartItemMap.put(goodsId, cartItemNew);
        }
        return this;
    }

    public Cart delGoods(Goods goods) {
        String goodsId = goods.getId();
        if (this.cartItemMap.containsKey(goodsId)) {
            cartItemMap.remove(goodsId);
        }
        return this;
    }

    public Cart setGoodsNum(Goods goods, Integer goodsNum) {
        if (this.cartItemMap.containsKey(goods.getId())) {
            CartItem cartItem = this.cartItemMap.get(goods.getId());
            cartItem.setGoodsNum(goodsNum);
            this.cartItemMap.replace(goods.getId(), cartItem);
        }
        return this;
    }
}
