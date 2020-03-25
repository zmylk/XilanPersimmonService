package com.xilan.base;

import com.xilan.bengin.BaseServiceApplication;
import com.xilan.bengin.entity.WXAccessTokenModel;
import com.xilan.bengin.pojo.User;
import com.xilan.bengin.service.Impl.LoginServiceImpl;
import com.xilan.bengin.service.TimeOpeService;
import com.xilan.bengin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/23 23:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseServiceApplication.class)
public class ServiceTest {

    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private TimeOpeService timeOpeService;
    @Autowired
    private UserService userService;


    @Test
    public void addTime(){
        timeOpeService.addRecording("o4dta5Ls4UVCXBR3aOImFGtNXbyQ");
    }

    @Test
    public void getUser(){
        User usr = userService.getUserById("o4dta5Ls4UVCXBR3aOImFGtNXbyQ");
        System.out.println(usr);
    }

    @Test
    public void getDay(){
        Integer integer = userService.useTime("o4dta5Ls4UVCXBR3aOImFGtNXbyQ");
    }
}
