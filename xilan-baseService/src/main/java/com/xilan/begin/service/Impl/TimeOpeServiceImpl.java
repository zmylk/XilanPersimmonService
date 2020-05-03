package com.xilan.begin.service.Impl;

import com.xilan.begin.entity.*;
import com.xilan.begin.map.TimeOpeMapper;
import com.xilan.begin.pojo.TimeOpe;
import com.xilan.begin.service.TimeOpeService;
import com.xilan.common.enums.ExceptionEnumm;
import com.xilan.common.exception.LyException;
import com.xilan.common.utils.time.TimeOper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

    //添加一条记录开始
    @Override
    @Transactional
    public TimeOpe addRecordingBegin(String openId, int comment, String event) {
        String statTime = redisTemplate.opsForValue().get(openId);
        TimeOpe timeOpe = new TimeOpe();
        if (statTime!=null){
            throw new LyException(ExceptionEnumm.NOT_CANT_REQUEST_AGAIN);
        }
        String timeString = TimeOper.getTimeString();
        redisTemplate.opsForValue().set(openId,timeString,1, TimeUnit.DAYS);
        return timeOpe;
    }

    //一条记录结束
    @Transactional
    public TimeOpe addRecordingEnd(String openId, int comment, String event){
        String statTime = redisTemplate.opsForValue().get(openId);
        TimeOpe timeOpe;
        if (statTime==null){
            throw new LyException(ExceptionEnumm.NOT_RECORDING_BEGIN);
        }
        redisTemplate.opsForValue().getOperations().delete(openId);
        Date redisStart = TimeOper.getTimeDate(statTime);
        int year = TimeOper.getYear(redisStart);
        int month = TimeOper.getMonth(redisStart);
        int day = TimeOper.getDay(redisStart);
        int hour = TimeOper.getHour(redisStart);
        //获取对象
        Calendar c = Calendar.getInstance();
        // 当前内日期是本月第几周
        Integer weeks = c.get(Calendar.WEEK_OF_MONTH);
        // 当前是星期几 java中一周第一天为星期天，所以1代表星容期日，2代表星期一，以此类推，7代表星期6
        Integer week = c.get(Calendar.DAY_OF_WEEK);
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
        timeOpe.setWeek(weeks);
        timeOpe.setDayofweek(week);
        timeOpeMapper.insert(timeOpe);
        return timeOpe;
    }
    //获取开始时间
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

    //获取今天的链表
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
        if (select.size()==0){
            throw  new LyException(ExceptionEnumm.NOT_RECORDING);
        }
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

    //获取今天的信息
    public TodayInformation getTodayInformation(String openid){
        if (openid==null){
            throw  new LyException(ExceptionEnumm.OPENID_CANNOT_BE_NULL);
        }
        TodayInformation today = new TodayInformation();
        Integer oneDayTime = timeOpeMapper.getOneDayTime(openid);
        today.setTime(oneDayTime);
        today.setEffectiveTime(-1);
        // TODO today.setLine();
        Map<Integer, Double> lineList = getLineList(openid);
        today.setLine(lineList);
        // TODO today.setPie();
        Map<String, Double> pieList = getpieList(openid);
        today.setPie(pieList);
        // TODO today.setPeriod();
        List<Double> period = getPeriod(openid);
        today.setPeriod(period);
        return today;
    }


    public List<Double> getPeriod(String openid){
        List<TodaytLine> todaytLine = timeOpeMapper.getTodaytLine(openid);
        List<Double> period = new ArrayList<>();

        for (TodaytLine line : todaytLine) {
            period.add(TimeOper.getTenTime(line.getStartime()));
            period.add(TimeOper.getTenTime(line.getEndtime()));
        }
        return period;
    }

    @Override
    public List<WeekLine> getWeekLineByTwo(String startDay, String endDay, String openid) {
        if (startDay==null||endDay==null||openid==null){
            throw  new LyException(ExceptionEnumm.CANNOT_BE_NULL);
        }
        List<WeekLine> weekLine = timeOpeMapper.getWeekLine(startDay,endDay,openid);
        return weekLine;
    }

    @Override
    public List<MoonLine> getMoonLine(String year, String moon, String openid) {
        if (year==null||moon==null||openid==null){
            throw  new LyException(ExceptionEnumm.CANNOT_BE_NULL);
        }
        List<MoonLine> moonLine = timeOpeMapper.getMoonLine(year,moon,openid);
        return moonLine;
    }

    public Map<String,Double> getpieList(String openid){
        List<TodaytPie> todaytPie = timeOpeMapper.getTodaytPie(openid);
        Map<String,Double> pie = new HashMap<>();
        for (TodaytPie todaytPie1 : todaytPie) {
            pie.put(todaytPie1.getEvent(),todaytPie1.getProportion());
        }
        return pie;
    }

    public Map<Integer,Double> getLineList(String openid){
        List<TodaytLine> todaytLine = timeOpeMapper.getTodaytLine(openid);
        Map<Integer,Double> map = new HashMap<>();
        for (TodaytLine line : todaytLine) {
            Map<Integer, Double> needTime = TimeOper.getNeedTime(line.getStartime(), line.getEndtime());
            for (Map.Entry<Integer, Double> entry : needTime.entrySet()) {
                map.put(entry.getKey(),map.getOrDefault(entry.getKey(),0.0)+entry.getValue());
            }
        }
        return map;
    }
}
