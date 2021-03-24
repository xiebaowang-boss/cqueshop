package com.xielaoban.cqueshop;

import com.xielaoban.cqueshop.Service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

//@SpringBootTest
class CqueshopApplicationTests {

    @Autowired
    CategoryService categoryService;

    @Test
    void contextLoads() {
        Date curDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    }

}
