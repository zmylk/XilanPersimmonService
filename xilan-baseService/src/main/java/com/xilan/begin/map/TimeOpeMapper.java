package com.xilan.begin.map;

import com.xilan.begin.entity.TodaytLine;
import com.xilan.begin.entity.TodaytPie;
import com.xilan.begin.pojo.TimeOpe;
import com.xilan.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/24 21:44
 */
@Mapper
public interface TimeOpeMapper extends BaseMapper<TimeOpe> {

    Integer getOneDayTime(String openid);
    List<TodaytPie> getTodaytPie(String openid);
    List<TodaytLine> getTodaytLine(String openid);
}

