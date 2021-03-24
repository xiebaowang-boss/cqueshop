package com.xielaoban.cqueshop.Util;

import java.util.UUID;

/**
 * @Author 蟹老板
 * @Date 2021-3-19 16:53
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Util.UUID
 * @Description
 */
public class GenerateUUID {
    /**
     * 获取一个随机数，去除掉了内置产生的“-”
     *
     * @Param
     * @Return String
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
