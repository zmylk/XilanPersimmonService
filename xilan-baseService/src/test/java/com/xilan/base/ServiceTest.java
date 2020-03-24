package com.xilan.base;

import com.xilan.bengin.BaseServiceApplication;
import com.xilan.bengin.entity.WXAccessTokenModel;
import com.xilan.bengin.service.Impl.LoginServiceImpl;
import com.xilan.bengin.service.TimeOpeService;
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

    @Test
    public void getAccessTokenTest(){
        WXAccessTokenModel accessToken = loginService.getAccessToken();
        System.out.println(accessToken);
    }

    //o4dta5Ls4UVCXBR3aOImFGtNXbyQ
    //o4dta5Ls4UVCXBR3aOImFGtNXbyQ
    @Test
    public void getUserInfoTest(){
        loginService.getUserInfo("o4dta5Ls4UVCXBR3aOImFGtNXbyQ");
    }

    @Test
    public void addTime(){
        timeOpeService.addRecording("o4dta5Ls4UVCXBR3aOImFGtNXbyQ");
    }
}
