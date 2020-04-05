package com.xilan.begin.controller;

import com.xilan.begin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/26 22:13
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;


}
