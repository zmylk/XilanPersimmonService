package com.xilan.begin.service.Impl;

import com.xilan.begin.map.UserMapper;
import com.xilan.begin.pojo.User;
import com.xilan.begin.service.UserService;
import com.xilan.common.enums.ExceptionEnumm;
import com.xilan.common.exception.LyException;
import com.xilan.common.utils.time.TimeOper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/25 22:57
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(String openId) {
       if (openId==null){
            throw  new LyException(ExceptionEnumm.OPENID_CANNOT_BE_NULL);
        }
        User user = userMapper.selectByPrimaryKey(openId);
        return user;
    }

    @Override
    public Integer useTime(String openId) {
        if (openId==null){
            throw  new LyException(ExceptionEnumm.OPENID_CANNOT_BE_NULL);
        }
        User user = userMapper.selectByPrimaryKey(openId);
        Date createTime = user.getCreateTime();
        Date lastVisitTime = user.getLastVisitTime();
        int day = TimeOper.daysBetween(createTime, lastVisitTime);
        System.out.println(day);
        return null;
    }


}
