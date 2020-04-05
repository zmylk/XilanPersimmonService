package com.xilan.begin.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author like
 * @version 1.0
 * @date 2020/4/1 22:33
 */
@Data
public class TodayInformation {
    int time;
    int effectiveTime;
    List<Map<String,Long>> line;
    List<Map<String,Long>> Pie;
    List<Integer> Period;
}
