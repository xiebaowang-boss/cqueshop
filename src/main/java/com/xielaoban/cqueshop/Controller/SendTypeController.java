package com.xielaoban.cqueshop.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xielaoban.cqueshop.Common.Result;
import com.xielaoban.cqueshop.Entity.Order.SendType;
import com.xielaoban.cqueshop.Service.SendTypeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-10 16:11
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Controller
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/sendType")
public class SendTypeController extends BaseController {
    private static final Log log = LogFactory.getLog(SendTypeController.class);
    private final String sendTypeRedisKey = "sendTypeRedisKey";

    @Autowired
    SendTypeService sendTypeService;

    @GetMapping("/getSendTypeEnabled")
    public Result getSendTypeEnabled() {
        try {
            log.info("获取送货方式！");
            if (redisUtil.hasKey(sendTypeRedisKey)) {
                //将送货方式从Redis提取出来
                List<SendType> sendTypeList = JSONArray.parseArray(redisUtil.get(sendTypeRedisKey).toString(), SendType.class);
                return Result.Success(sendTypeList);
            } else {
                List<SendType> sendTypeList = sendTypeService.getAllEnabled();
                redisUtil.set(sendTypeRedisKey, JSON.toJSONString(sendTypeList), 60);
                return Result.Success(sendTypeList);
            }
        } catch (Exception e) {
            log.error("获取送货方式出错了！", e);
            return Result.Error("获取失败", null);
        }
    }
}
