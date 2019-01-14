package com.guangfuxiong.dao;

import com.guangfuxiong.entity.UserInfo;

public interface RegisterDao {
    Integer addUser(UserInfo userInfo);
    UserInfo checkName(String userName);
}
