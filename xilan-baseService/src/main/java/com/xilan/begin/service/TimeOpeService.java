package com.xilan.begin.service;

import com.xilan.begin.entity.EventList;
import com.xilan.begin.entity.MoonLine;
import com.xilan.begin.entity.TodayInformation;
import com.xilan.begin.entity.WeekLine;
import com.xilan.begin.pojo.TimeOpe;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/24 22:12
 */
public interface TimeOpeService {
    TimeOpe addRecordingBegin(String openId, int comment, String event);
    TimeOpe addRecordingEnd(String openId, int comment, String event);

    long isBeginTime(String openId);

    List<EventList> getTodayEventList(String openId);

    TodayInformation getTodayInformation(String openid);

    Map<Integer, Double> getLineList(String openid);

    Map<String, Double> getpieList(String openid);

    List<Double> getPeriod(String openid);

    List<WeekLine> getWeekLineByTwo(String startDay, String endDay, String openid);

    List<MoonLine> getMoonLine(String year, String moon, String openid);

}
