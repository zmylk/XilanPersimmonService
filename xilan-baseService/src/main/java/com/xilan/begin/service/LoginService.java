package com.xilan.begin.service;

import com.xilan.begin.pojo.User;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/23 22:14
 */
public interface LoginService {

    User addUser(String code, String rawData, String signature);

}
