package com.xilan.base;

import com.xilan.bengin.BaseServiceApplication;
import com.xilan.bengin.map.TimeOpeMapper;
import com.xilan.bengin.pojo.TimeOpe;
import com.xilan.common.utils.time.TimeOper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        TimeOpe timeOpe = new TimeOpe();
        timeOpe.setOpenId("123456");
        timeOpeMapper.insert(timeOpe);
    }
}
