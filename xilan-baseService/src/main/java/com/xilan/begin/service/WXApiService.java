package com.xilan.begin.service;

import com.xilan.begin.entity.WXAccessTokenModel;
import com.xilan.begin.entity.WXSessionAndIdModel;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/25 22:39
 */
public interface WXApiService {
    WXSessionAndIdModel getOpenIdAndSeesionKey(String code);
    WXAccessTokenModel getAccessToken();
}
