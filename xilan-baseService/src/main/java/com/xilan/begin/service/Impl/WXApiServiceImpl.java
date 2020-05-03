package com.xilan.begin.service.Impl;

import com.xilan.begin.entity.WXAccessTokenModel;
import com.xilan.begin.entity.WXSessionAndIdModel;
import com.xilan.begin.service.WXApiService;
import com.xilan.common.enums.ExceptionEnumm;
import com.xilan.common.exception.LyException;
import com.xilan.common.utils.JsonUtils;
import com.xilan.common.utils.http.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/25 22:40
 */
@Service
public class WXApiServiceImpl implements WXApiService {

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
        if (wxResult.contains("errcode")){
            throw new LyException(ExceptionEnumm.NOT_CANT_GET_SESSIONID_OPENID);
        }
        WXSessionAndIdModel wxSessionAndIdModel = JsonUtils.parse(wxResult, WXSessionAndIdModel.class);
        return wxSessionAndIdModel;
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
}
