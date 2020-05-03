package com.xilan.begin.controller;

import com.xilan.begin.entity.*;
import com.xilan.begin.pojo.TimeOpe;
import com.xilan.begin.service.TimeOpeService;
import com.xilan.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/26 22:12
 */
@RestController
public class TimeOpeController {

    @Autowired
    TimeOpeService timeOpeService;

    @GetMapping("timeope/{openid}")
    public R getBeginTime(@PathVariable("openid") String openid){
        Long beginTime = timeOpeService.isBeginTime(openid);
        Map<String,Object> map = new HashMap<>();
        if (beginTime!=0L){
            map.put("status","true");
            map.put("beginTimestamp",beginTime);
        }else {
            map.put("status","false");
            map.put("beginTimestamp","null");
        }
        return R.ok(map);
    }

    @GetMapping("addRecordingBegin/{openid}")
    public R addRecordingBegin(@PathVariable("openid")String openid){
        timeOpeService.addRecordingBegin(openid,-1,"null");
        Map<String,String> res = new HashMap<>();
        res.put("status","true");
        return R.ok(res);
    }

    @PostMapping("addRecordingEnd")
    public R addRecordingEnd(@RequestParam(value = "openid", required = true) String openid,
                          @RequestParam(value = "comment", required = false) Integer comment,
                          @RequestParam(value = "event", required = false) String event
                          ){
        TimeOpe timeOpe = timeOpeService.addRecordingEnd(openid,comment,event);
        Date startime = timeOpe.getStartime();
        Map<String,String> res = new HashMap<>();
        if (startime==null){
            res.put("status","true");
        }else {
            res.put("status","true");
        }
        return R.ok(res);
    }

    @GetMapping("addRecordingFake/{openid}")
    public R addRecordingFake(@PathVariable("openid") String openid){
        // TODO 这里没有通数据
        long beginTime = timeOpeService.isBeginTime(openid);
        Map<String,String> res = new HashMap<>();
        if (true){
            res.put("studytime","1234");
            res.put("effectivetime","1234");
        }else {
            res.put("status","false");
        }
        return R.ok(res);
    }
    @GetMapping("getEventList/{openid}")
    public R getEventList(@PathVariable("openid") String openid){
        List<EventList> todayEventList = timeOpeService.getTodayEventList(openid);
        Map<String,Object> res = new HashMap<>();
        res.put("eventlist",todayEventList);
        res.put("status","true");
        return R.ok(res);
    }

    @GetMapping("todayInformation/{openid}")
    public TodayInformation getTodayInformation(@PathVariable("openid") String openid){
        TodayInformation todayInformation = timeOpeService.getTodayInformation(openid);
        return todayInformation;
    }

    //获取一天数据
    @GetMapping("getTodayInformation/{openid}")
    public Map getTodayInformationById(@PathVariable("openid") String openid){
        //返回结果
        Map<String,Object> map = new HashMap<>();
        //pie
        Map<String, Double> stringDoubleMap = timeOpeService.getpieList(openid);
        int index = 0;
        String[] course = new String[stringDoubleMap.size()];
        List<Double> num = new ArrayList<>();
        for (Map.Entry<String, Double> entry : stringDoubleMap.entrySet()) {
            course[index++] = entry.getKey();
            num.add(entry.getValue());
        }
        //构建饼图模拟数据
        Pie pie = new Pie();
        pie.setAria(course);
        pie.setData(num);


        //构建折线图模拟数据
        Line line = new Line();
        Map<Integer, Double> lineList = timeOpeService.getLineList(openid);
        index=0;
        String[] time = new String[lineList.size()];
        List<Double> timeNum = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : lineList.entrySet()) {
            time[index++] = entry.getKey()+":00";
            Double value = entry.getValue()*60;
            timeNum.add(value);

        }
        line.setXAria(time);
        line.setStData(timeNum);
        line.setEtData(timeNum);
        line.setPtData(timeNum);
        map.put("pie",pie);
        map.put("line",line);
        return map;
    }

    //获取一周数据
    @GetMapping("getWeekInformation/{startDate}/{endDate}/{openid}")
    public Map getWeekInformation(@PathVariable("startDate") String startDate,
                                   @PathVariable("endDate") String endDate,
                                   @PathVariable("openid") String openid){
        Map<String,Object> map = new HashMap<>();
        List<WeekLine> weekLineByTwo = timeOpeService.getWeekLineByTwo(startDate, endDate, openid);
        String[] name = new String[weekLineByTwo.size()];
        List<Double> list =  new ArrayList<>();

        for (int i = 0; i < weekLineByTwo.size(); i++) {
            name[i] = weekLineByTwo.get(i).getDay();
            list.add(weekLineByTwo.get(i).getSumTime()+0.0);
        }

        //构建折线图模拟数据
        Line line = new Line();
        line.setXAria(name);
        line.setStData(list);
        line.setEtData(list);
        line.setPtData(list);
        map.put("line",line);
        return map;
    }

    //获取一月数据
    @GetMapping("getMoonInformation/{year}/{month}/{openid}")
    public Map getMoonInformation(@PathVariable("year") String year,
                                    @PathVariable("month") String month,
                                   @PathVariable("openid") String openid){
        Map<String,Object> map = new HashMap<>();
        List<MoonLine> moonLine = timeOpeService.getMoonLine(year,month,openid);
        String[] name = new String[moonLine.size()];
        List<Double> list =  new ArrayList<>();

        for (int i = 0; i < moonLine.size(); i++) {
            String weekDay = getWeekDay(moonLine.get(i).getWeek());
            name[i] = weekDay;
            list.add(moonLine.get(i).getSumTime()+0.0);
        }

        //构建折线图模拟数据
        Line line = new Line();
        line.setXAria(name);
        line.setStData(list);
        line.setEtData(list);
        line.setPtData(list);
        map.put("line",line);
        return map;
    }


    private  String getWeekDay(Integer num){

        switch (num){
            case 1: return "第一周";
            case 2: return "第二周";
            case 3: return "第三周";
            case 4: return "第四周";
            case 5: return "第五周";
            default: return "错误输入";
        }
    }

}
