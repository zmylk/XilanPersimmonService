package com.xilan.bengin.service.Impl;

import com.xilan.bengin.map.TimeOpeMapper;
import com.xilan.bengin.pojo.TimeOpe;
import com.xilan.bengin.service.TimeOpeService;
import com.xilan.common.utils.IdWorker;
import com.xilan.common.utils.time.TimeOper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/24 22:14
 */
@Service
public class TimeOpeServiceImpl implements TimeOpeService {

    @Autowired
    TimeOpeMapper timeOpeMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    IdWorker idWorker = new IdWorker();
    @Override
    public void addRecording(String openId) {
        String statTime = redisTemplate.opsForValue().get(openId);
        if (statTime==null){
            String timeString = TimeOper.getTimeString();
            redisTemplate.opsForValue().set(openId,timeString,1, TimeUnit.DAYS);
        }else {
            redisTemplate.opsForValue().getOperations().delete(openId);
            Date redisStart = TimeOper.getTimeDate(statTime);
            int year = TimeOper.getYear(redisStart);
            int month = TimeOper.getMonth(redisStart);
            int day = TimeOper.getDay(redisStart);
            int hour = TimeOper.getHour(redisStart);
            System.out.println(redisStart);
            System.out.println(year);
            Date redisEnd = new Date();
            TimeOpe timeOpe = new TimeOpe();
            timeOpe.setOpenId(openId);
            timeOpe.setStartime(redisStart);
            timeOpe.setEndtime(redisEnd);
            timeOpe.setYear(year);
            timeOpe.setMonth(month);
            timeOpe.setDay(day);
            timeOpe.setHour(hour);
            timeOpeMapper.insert(timeOpe);
        }
    }
}
