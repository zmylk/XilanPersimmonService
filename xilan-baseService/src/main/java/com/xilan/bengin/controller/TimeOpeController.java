package com.xilan.bengin.controller;

import com.xilan.bengin.pojo.TimeOpe;
import com.xilan.bengin.service.TimeOpeService;
import com.xilan.common.vo.R;
import org.apache.ibatis.ognl.IntHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
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
        Map<String,Long> map = new HashMap<>();
        map.put("beginTimestamp",beginTime);
        return R.ok(map);
    }

    @GetMapping("addRecording/{openid}")
    public R addRecording(@PathVariable("openid")String openid){
        TimeOpe timeOpe = timeOpeService.addRecording(openid);
        Date startime = timeOpe.getStartime();
        Map<String,String> res = new HashMap<>();
        if (startime==null){
            res.put("status","210");
            res.put("message","begin");
            return R.ok(res);
        }else {
            res.put("status","211");
            res.put("totaltime","1234");
            res.put("effective","123");
            return R.ok(res);
        }

    }
}
