package com.guangfuxiong.service;

import com.guangfuxiong.entity.UserInfo;

public interface RegisterService {
    Integer addUser(UserInfo userInfo);
    UserInfo checkName(String userName);
    Boolean loginInto(UserInfo userInfo);
}
