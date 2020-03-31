package com.xilan.bengin.service;

import com.xilan.bengin.entity.EventList;
import com.xilan.bengin.pojo.TimeOpe;

import java.util.List;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/24 22:12
 */
public interface TimeOpeService {
    TimeOpe addRecording(String openId, int comment, String event);
    long isBeginTime(String openId);
    List<EventList> getTodayEventList(String openId);
}
