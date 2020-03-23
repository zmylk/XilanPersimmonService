package com.xilan.bengin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("realtime-hour")
    public void test(){
        System.out.println("正确的开始");
    }
}
