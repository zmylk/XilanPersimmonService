package com.xilan.bengin.service.Impl;

import com.xilan.bengin.entity.EventList;
import com.xilan.bengin.map.TimeOpeMapper;
import com.xilan.bengin.pojo.TimeOpe;
import com.xilan.bengin.pojo.User;
import com.xilan.bengin.service.TimeOpeService;
import com.xilan.common.enums.ExceptionEnumm;
import com.xilan.common.exception.LyException;
import com.xilan.common.utils.IdWorker;
import com.xilan.common.utils.time.TimeOper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Override
    public TimeOpe addRecording(String openId, int comment, String event) {
        String statTime = redisTemplate.opsForValue().get(openId);
        TimeOpe timeOpe = new TimeOpe();
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

            Date redisEnd = new Date();
            int useHourTime = TimeOper.hourBetween(redisEnd, redisStart);
            timeOpe = new TimeOpe();
            timeOpe.setOpenId(openId);
            timeOpe.setStartime(redisStart);
            timeOpe.setEndtime(redisEnd);
            timeOpe.setYear(year);
            timeOpe.setMonth(month);
            timeOpe.setDay(day);
            timeOpe.setHour(hour);
            timeOpe.setEvaluation1(useHourTime);
            timeOpe.setEvaluation2(comment);
            timeOpe.setEvaluation3(event);
            timeOpeMapper.insert(timeOpe);
        }
        return timeOpe;
    }

    @Override
    public long isBeginTime(String openId) {
        if (openId==null){
            throw new LyException(ExceptionEnumm.CODE_CANNOT_BE_NULL);
        }
        String statTime = redisTemplate.opsForValue().get(openId);
        if (statTime==null){
            return 0L;
        }
        Date timeDate = TimeOper.getTimeDate(statTime);
        return timeDate.getTime();
    }

    @Override
    public List<EventList> getTodayEventList(String openId) {
        if (openId==null){
            throw  new LyException(ExceptionEnumm.OPENID_CANNOT_BE_NULL);
        }
        // System.out.println("返回日期年份:"+getYear(new Date()));
        //        System.out.println("返回月份："+getMonth(new Date()));
        //        System.out.println("返回当天日份"+getDay(new Date()));
        List<EventList> res = new ArrayList<>();
        Date date = new Date();
        TimeOpe timeOpe = new TimeOpe();
        timeOpe.setOpenId(openId);
        timeOpe.setYear(TimeOper.getYear(date));
        timeOpe.setMonth(TimeOper.getMonth(date));
        timeOpe.setDay(TimeOper.getDay(date));
        List<TimeOpe> select = timeOpeMapper.select(timeOpe);
        EventList eventList = null;
        for (TimeOpe ope : select) {
            eventList = new EventList();
            eventList.setStudyTime(ope.getEvaluation1());
            eventList.setScore(ope.getEvaluation2());
            eventList.setEvent(ope.getEvaluation3());
            res.add(eventList);
        }
        return res;
    }
}
