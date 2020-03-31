package com.xilan.bengin.controller;

import com.xilan.bengin.entity.EventList;
import com.xilan.bengin.pojo.TimeOpe;
import com.xilan.bengin.service.TimeOpeService;
import com.xilan.common.vo.R;
import org.apache.ibatis.ognl.IntHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        timeOpeService.addRecording(openid,-1,"null");
        Map<String,String> res = new HashMap<>();
        res.put("status","true");
        return R.ok(res);
    }

    @PostMapping("addRecordingEnd")
    public R addRecordingEnd(@RequestParam(value = "openid", required = true) String openid,
                          @RequestParam(value = "comment", required = false) Integer comment,
                          @RequestParam(value = "event", required = false) String event
                          ){
        TimeOpe timeOpe = timeOpeService.addRecording(openid,comment,event);
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

}
