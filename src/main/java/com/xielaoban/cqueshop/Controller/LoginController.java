package com.xielaoban.cqueshop.Controller;

import com.xielaoban.cqueshop.Entity.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 蟹老板
 * @Date 2021-3-23 14:09
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Controller
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {
    public User getCurrentUser() {
        return null;
    }
}
