package com.xilan.begin.service;

import com.xilan.begin.entity.EventList;
import com.xilan.begin.entity.TodayInformation;
import com.xilan.begin.pojo.TimeOpe;

import java.util.List;
import java.util.Map;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/24 22:12
 */
public interface TimeOpeService {
    TimeOpe addRecording(String openId, int comment, String event);
    long isBeginTime(String openId);
    List<EventList> getTodayEventList(String openId);
     TodayInformation getTodayInformation(String openid);
     Map<Integer,Double> getLineList(String openid);

}
