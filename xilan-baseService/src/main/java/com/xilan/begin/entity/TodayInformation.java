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
    Map<Integer,Double> line;
    Map<String,Double> Pie;
    List<Double> Period;
}
