package com.xilan.bengin.entity;

import lombok.Data;

/**
 * @author:liwenkang
 * @date:2019/8/9 14:30
 */
@Data
public class WXAccessTokenModel {
    private String access_token;
    private Integer expires_in;
    private Integer errcode;
    private String string;
}
