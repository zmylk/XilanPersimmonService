package com.xilan.bengin.service;

import com.xilan.bengin.pojo.User;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/25 22:56
 */
public interface UserService {
    User getUserById(String openId);
    Integer useTime(String openId);
}