package com.xilan.base;

import com.xilan.begin.BaseServiceApplication;
import com.xilan.begin.entity.TodaytLine;
import com.xilan.begin.entity.TodaytPie;
import com.xilan.begin.map.TimeOpeMapper;
import com.xilan.begin.pojo.TimeOpe;
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
 * @date 2020/3/25 22:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseServiceApplication.class)
public class MapperTest {
    @Autowired
    TimeOpeMapper timeOpeMapper;

    @Test
    public void timeTest(){
//        TimeOpe timeOpe = new TimeOpe();
//        timeOpe.setOpenId("123456");
//        timeOpeMapper.insert(timeOpe);
        List<TodaytLine> list = timeOpeMapper.getTodaytLine("o4dta5Ls4UVCXBR3aOImFGtNXbyQ");
        for (TodaytLine todaytPie : list) {
            System.out.println(todaytPie.getStartime() + "=="+todaytPie.getEndtime());
        }


    }
}
