package com.xilan.bengin.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xilan.bengin.entity.WXAccessTokenModel;
import com.xilan.bengin.entity.WXSessionAndIdModel;
import com.xilan.bengin.map.UserMapper;
import com.xilan.bengin.pojo.User;
import com.xilan.bengin.service.LoginService;
import com.xilan.bengin.service.WXApiService;
import com.xilan.common.enums.ExceptionEnumm;
import com.xilan.common.exception.LyException;
import com.xilan.common.utils.JsonUtils;
import com.xilan.common.utils.http.HttpClientUtil;
import com.xilan.common.utils.time.TimeOper;
import com.xilan.common.vo.PageResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        PageInfo<User> userPageInfo = new PageInfo<>(userArrayList);

        return user;
    }

}
