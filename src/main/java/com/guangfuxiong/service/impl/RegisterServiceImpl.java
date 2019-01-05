package com.guangfuxiong.service.impl;

import com.guangfuxiong.dao.RegisterDao;
import com.guangfuxiong.entity.UserInfo;
import com.guangfuxiong.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterDao registerDao;
    public Integer addUser(UserInfo userInfo) {
        return  registerDao.addUser(userInfo);
    }

}
