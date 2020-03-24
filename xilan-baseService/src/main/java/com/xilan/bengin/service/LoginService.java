package com.xilan.bengin.service;

import com.xilan.bengin.entity.WXAccessTokenModel;
import com.xilan.bengin.entity.WXSessionAndIdModel;
import com.xilan.bengin.pojo.User;
import com.xilan.common.vo.PageResult;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/23 22:14
 */
public interface LoginService {
    WXSessionAndIdModel getOpenIdAndSeesionKey(String code);
    void getUserInfo(String openId);
    WXAccessTokenModel getAccessToken();
    PageResult<User> addUser(String code, String rawData, String signature);
}
