package com.xielaoban.cqueshop;

import com.xielaoban.cqueshop.Service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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

    @Test
    void md5Test() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("md5");

        byte[] bytes = md.digest("x1210579586".getBytes());

        String str = Base64.getEncoder().encodeToString(bytes);

        System.out.println(str);//rp2ZxN0Zlcr53ecP0h1PCA==
    }

}
