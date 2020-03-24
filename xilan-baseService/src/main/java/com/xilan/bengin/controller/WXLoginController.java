package com.xilan.bengin.controller;

import com.xilan.bengin.entity.WXSessionAndIdModel;
import com.xilan.common.utils.JsonUtils;
import com.xilan.common.utils.http.HttpClientUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/23 21:57
 */
@RestController
public class WXLoginController {
    @RequestMapping(value = "/wxLogin", method = RequestMethod.POST)
    public void wxLogin(String code) {

        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<String, String>();
        param.put("appid", "wx5f2cfd0f5d5a3f1c");
        param.put("secret", "a5e99a999e5f498a6d414da8d73b6f84");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String wxResult = null;
        try {
            wxResult = httpClientUtil.doGet(url, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXSessionAndIdModel wxSessionAndIdModel = JsonUtils.parse(wxResult, WXSessionAndIdModel.class);
    }
}
