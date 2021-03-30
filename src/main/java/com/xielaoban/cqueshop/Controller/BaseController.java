package com.xielaoban.cqueshop.Controller;

import com.xielaoban.cqueshop.Util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 蟹老板
 * @Date 2021-3-25 9:29
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Controller
 * @Description
 */
@Component
public class BaseController {
    /**
     * redis工具 以便其他Controller重复使用
     */
    @Autowired
    RedisUtil redisUtil;
}
