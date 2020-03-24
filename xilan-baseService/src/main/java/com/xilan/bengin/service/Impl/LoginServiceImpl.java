package com.xilan.bengin.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xilan.bengin.entity.WXAccessTokenModel;
import com.xilan.bengin.entity.WXSessionAndIdModel;
import com.xilan.bengin.map.UserMapper;
import com.xilan.bengin.pojo.User;
import com.xilan.bengin.service.LoginService;
import com.xilan.common.utils.JsonUtils;
import com.xilan.common.utils.http.HttpClientUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Value("${xilan.base.appid}")
    private String appid;
    @Value("${xilan.base.secret}")
    private String secret;

    @Override
    public WXSessionAndIdModel getOpenIdAndSeesionKey(String code) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<String, String>();
        param.put("appid", appid);
        param.put("secret", secret);
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String wxResult = null;
        try {
            wxResult = httpClientUtil.doGet(url, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXSessionAndIdModel wxSessionAndIdModel = JsonUtils.parse(wxResult, WXSessionAndIdModel.class);
        return wxSessionAndIdModel;
    }

    @Override
    public void getUserInfo(String openId) {
        WXAccessTokenModel accessToken = getAccessToken();
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String url = "https://api.weixin.qq.com/sns/userinfo";
        Map<String, String> param = new HashMap<String, String>();
        param.put("access_token", "31_xatqdUs_9gkkBxsOLSEeKHUiF5gvk7KNHC_S9xsnBb36hBuOMHNl4p9WwaAMABWzbi6aofvCh1-YcS1aW6xuBrhvsN1AH7ZrK1n1uTwjzTHP0Syf6MBA67-C7J05El5Gl8uSr-qwaPH6gNecZZQhAFAVVM");
        param.put("openid", openId);
        param.put("lang", "zh_CN");
        String wxResult = null;
        try {
            wxResult = httpClientUtil.doGet(url, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(wxResult);

    }

    @Override
    public WXAccessTokenModel getAccessToken() {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> param = new HashMap<String, String>();
        param.put("appid", appid);
        param.put("secret", secret);
        param.put("grant_type","client_credential");
        String wxResult = null;
        try {
            wxResult = httpClientUtil.doGet(url, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXAccessTokenModel wxAccessTokenModel = JsonUtils.parse(wxResult, WXAccessTokenModel.class);

        return wxAccessTokenModel;
    }

    @Override
    public String addUser(String code, String rawData, String signature) {
        JSONObject rawDataJson = JSON.parseObject(rawData);
        WXSessionAndIdModel openIdAndSeesionKey = getOpenIdAndSeesionKey(code);
        String openid = openIdAndSeesionKey.getOpenid();
        String sessionKey = openIdAndSeesionKey.getSession_key();
        User user = userMapper.selectByPrimaryKey(openIdAndSeesionKey.getOpenid());
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            System.out.println("++++++++false++++++++++++");
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
        return skey;
    }
}
