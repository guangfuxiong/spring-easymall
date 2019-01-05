package com.guangfuxiong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "toPage")
public class ToPageController {
    //跳转首页
    @RequestMapping(value = "index")
    public String toIndex(){
        return "index";
    }
    //注册页面
    @RequestMapping(value = "regist")
    public String toRegist(){
        return "regist";
    }
    //登录页面
    @RequestMapping(value = "toLogin")
    public String toLogin(){
        return "login";
    }
}
