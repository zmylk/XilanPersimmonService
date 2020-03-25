package com.xilan.bengin.service;

import com.xilan.bengin.entity.WXAccessTokenModel;
import com.xilan.bengin.entity.WXSessionAndIdModel;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/25 22:39
 */
public interface WXApiService {
    WXSessionAndIdModel getOpenIdAndSeesionKey(String code);
    WXAccessTokenModel getAccessToken();
}
