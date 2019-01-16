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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}
