package com.xilan.base;

import com.xilan.begin.BaseServiceApplication;
import com.xilan.begin.entity.EventList;
import com.xilan.begin.pojo.User;
import com.xilan.begin.service.Impl.LoginServiceImpl;
import com.xilan.begin.service.TimeOpeService;
import com.xilan.begin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

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
        timeOpeService.addRecordingBegin("1714020334666",3,"China");
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

    @Test
    public void getBeginTime(){
        long time = timeOpeService.isBeginTime("o4dta5Ls4UVCXBR3aOImFGtNXbyQ");
        System.out.println(time);
    }

    @Test
    public void getTodayEventList(){
        List<EventList> tmp = timeOpeService.getTodayEventList("o4dta5Ls4UVCXBR3aOImFGtNXbyQ");

        for (EventList eventList : tmp) {
            System.out.println(eventList.getEvent());
            System.out.println(eventList.getStudyTime());
            System.out.println(eventList.getScore());
            System.out.println("=============================================");
        }
    }

    @Test
    public void getTodayLine(){
        Map<Integer, Double> line = timeOpeService.getLineList("o4dta5Ls4UVCXBR3aOImFGtNXbyQ");
        for (Map.Entry<Integer, Double> entry : line.entrySet()) {
            System.out.println(entry.getKey()+"->"+entry.getValue());
        }
    }

    @Test
    public void getTodayPeriod(){
        List<Double> period = timeOpeService.getPeriod("o4dta5Ls4UVCXBR3aOImFGtNXbyQ");
        for (Double aDouble : period) {
            System.out.println(aDouble);
        }
    }

    @Test
    public void getTodaypie(){
        Map<String, Double> pie = timeOpeService.getpieList("o4dta5Ls4UVCXBR3aOImFGtNXbyQ");
        for (Map.Entry<String, Double> entry : pie.entrySet()) {
            System.out.println(entry.getKey()+"->"+entry.getValue());
        }
    }
}
