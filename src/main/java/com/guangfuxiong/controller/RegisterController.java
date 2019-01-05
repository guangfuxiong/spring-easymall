package com.guangfuxiong.controller;

import com.guangfuxiong.common.ResultRet;
import com.guangfuxiong.entity.UserInfo;
import com.guangfuxiong.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;
    @RequestMapping(value = "register")
    public String register(UserInfo userInfo, Model model){
        Integer num = registerService.addUser(userInfo);
        ResultRet resultRet = new ResultRet();
        if(num>=1){
            resultRet.setCode(1);
            resultRet.setMessage("注册成功，3秒后返回登录页面");
            model.addAttribute("result",resultRet);
            return "registerSuccessTip";
        }
        resultRet.setCode(0);
        resultRet.setMessage("注册失败，请检查填入信息是否合法");
        model.addAttribute("result",resultRet);
        return "registerFiledTip";
    }
}
