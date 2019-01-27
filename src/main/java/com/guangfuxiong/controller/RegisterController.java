package com.guangfuxiong.controller;

import com.guangfuxiong.common.ResultRet;
import com.guangfuxiong.entity.UserInfo;
import com.guangfuxiong.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "register")
    public String register(UserInfo userInfo, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext servletContext = request.getSession().getServletContext();
        String path = servletContext.getRealPath("");
        model.addAttribute("path",path);
        Integer num = registerService.addUser(userInfo);
        ResultRet resultRet = new ResultRet();
        if(num>=1){
            resultRet.setCode(1);
            resultRet.setMessage("注册成功，3秒后返回登录页面");
            model.addAttribute("result",resultRet);
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().write("注册成功，3秒后返回登录页面-response");
//            response.setHeader("refresh","3;url=toPage/toLogin.do");
            return "registerSuccessTip";
        }
        resultRet.setCode(0);
        resultRet.setMessage("注册失败，请检查填入信息是否合法");
        model.addAttribute("result",resultRet);
        return "registerFiledTip";
    }
    @RequestMapping(value = "checkName")
    public @ResponseBody ResultRet checkName(String userName){
        ResultRet result = new ResultRet();
        try {
            UserInfo user = registerService.checkName(userName);
            if(!("".equals(user)) || null != user){
                result.setCode(1);
                result.setMessage("用户名已存在");
            }
        }catch (Exception e){
            result.setCode(0);
            result.setMessage("查询失败");
            e.printStackTrace();
        }
        return result;
    }
    //登录
    @RequestMapping(value = "loginInto")
    public String loginInto(UserInfo userInfo,Model model,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        String remname = request.getParameter("remname");
        //根据session识别原理，实现session长时间保存
//        Cookie sessionCo = new Cookie("JSESSIONID",request.getSession().getId());
//        sessionCo.setPath(request.getContextPath()+"/");
//        sessionCo.setMaxAge(60);
//        response.addCookie(sessionCo);
        if("true".equals(remname)){//记住用户名勾选了
            Cookie cookie = new Cookie("remname", URLEncoder.encode(userInfo.getUsername(),"utf-8"));
            //设置path, 让浏览器访问当前WEB应用下任何一个资源都能带Cookie!!
            cookie.setPath(request.getContextPath()+"/");
            //设置最大生存时间(保存30天,以秒计算)
            cookie.setMaxAge(3600*24*30);
            //让响应将cookie带回浏览器
            response.addCookie(cookie);
        }
        String autoLogin = request.getParameter("autologin");
        if("true".equals(autoLogin)){//30天自动登录勾选了
            Cookie autoCK = new Cookie("autoLogin",URLEncoder.encode(userInfo.getUsername()+":"+userInfo.getPassword(),"utf-8"));
            autoCK.setMaxAge(30*24*3600);
            autoCK.setPath(request.getContextPath()+"/");
            response.addCookie(autoCK);
        }
        Boolean flag = registerService.loginInto(userInfo);
        if(flag){
            String name = userInfo.getUsername();
            HttpSession session = request.getSession();
            session.setAttribute("user",name);
            //session.setMaxInactiveInterval(30);//设置session销毁时间，单位为秒，也可以在web.xml中配置（单位为分）
            model.addAttribute("user",name);
            return "index";
        }
        model.addAttribute("msg","用户名或密码不正确");
        return "login";
    }
}
