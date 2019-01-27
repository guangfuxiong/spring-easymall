package com.guangfuxiong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
    public String toLogin(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        for(Cookie co : cookies){
            if("remname".equals(co.getName())&&!("".equals(URLDecoder.decode(co.getValue(),"utf-8")))){
                model.addAttribute("userName", URLDecoder.decode(co.getValue(),"utf-8"));
                model.addAttribute("checkedName","checked");
                break;
            }
        }
        return "login";
    }
    //退出登录
    @RequestMapping(value = "outLogin")
    public String outLogin(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie= new Cookie("remname","");
        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath()+"/");
        response.addCookie(cookie);
        return "index";
    }
}
