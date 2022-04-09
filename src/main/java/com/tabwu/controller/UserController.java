package com.tabwu.controller;

import com.tabwu.config.WU;
import com.tabwu.entity.User;
import com.tabwu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: readWriteDynamicDb
 * @Author: tabwu
 * @Date: 2022/4/4 14:41
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public void add(@RequestBody User user) {
        userService.add(user);
    }

    @GetMapping("/get/{id}")
    @WU("slave")
    public User getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return user;
    }


}
