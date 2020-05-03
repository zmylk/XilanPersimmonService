package com.xilan.begin.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xilan.begin.entity.WXSessionAndIdModel;
import com.xilan.begin.map.UserMapper;
import com.xilan.begin.pojo.User;
import com.xilan.begin.service.LoginService;
import com.xilan.begin.service.WXApiService;
import com.xilan.common.enums.ExceptionEnumm;
import com.xilan.common.exception.LyException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/23 22:17
 */
@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    UserMapper userMapper;
    @Autowired
    WXApiService wxApiService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User addUser(String code, String rawData, String signature) {
        if (code==null){
            throw new LyException(ExceptionEnumm.CODE_CANNOT_BE_NULL);
        }
        JSONObject rawDataJson = JSON.parseObject(rawData);
        WXSessionAndIdModel openIdAndSeesionKey = wxApiService.getOpenIdAndSeesionKey(code);

        String openid = openIdAndSeesionKey.getOpenid();
        String sessionKey = openIdAndSeesionKey.getSession_key();
        User user = userMapper.selectByPrimaryKey(openIdAndSeesionKey.getOpenid());
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            throw new LyException(ExceptionEnumm.SIGNATURE_CHECK_BE_FAIL);
        }
        String skey ="!@##$$%%^^";
        if (user==null){
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            String gender = rawDataJson.getString("gender");
            String city = rawDataJson.getString("city");
            String country = rawDataJson.getString("country");
            String province = rawDataJson.getString("province");
            user = new User();
            user.setOpenId(openid);
            user.setSkey(skey);
            user.setCreateTime(new Date());
            user.setLastVisitTime(new Date());
            user.setSessionKey(sessionKey);
            user.setCity(city);
            user.setProvince(province);
            user.setCountry(country);
            user.setAvatarUrl(avatarUrl);
            user.setGender(Integer.parseInt(gender));
            user.setNickName(nickName);
            userMapper.insert(user);
        }else {
            user.setLastVisitTime(new Date());
            user.setSkey(skey);
            userMapper.updateByPrimaryKey(user);
        }
        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.add(user);
        //PageInfo<User> userPageInfo = new PageInfo<>(userArrayList);

        return user;
    }

}
