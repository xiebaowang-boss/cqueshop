package com.xielaoban.cqueshop.Service.Impl;

import com.xielaoban.cqueshop.Entity.Order.SendType;
import com.xielaoban.cqueshop.Mapper.SendTypeMapper;
import com.xielaoban.cqueshop.Service.SendTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.List;

/**
 * @Author 蟹老板
 * @Date 2021-4-10 16:27
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Service.Impl
 * @Description
 */
@Service
public class SendTypeImpl implements SendTypeService {
    @Autowired
    SendTypeMapper sendTypeMapper;

    @Override
    public List<SendType> getAllEnabled() {
        return sendTypeMapper.getAllEnabled();
    }
}
