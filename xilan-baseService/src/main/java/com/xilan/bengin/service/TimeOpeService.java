package com.xilan.bengin.service;

import com.xilan.bengin.pojo.TimeOpe;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/24 22:12
 */
public interface TimeOpeService {
    TimeOpe addRecording(String openId);
    long isBeginTime(String openId);
}
