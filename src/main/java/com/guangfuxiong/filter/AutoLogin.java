package com.guangfuxiong.filter;

import com.guangfuxiong.entity.UserInfo;
import com.guangfuxiong.service.RegisterService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

@Component
public class AutoLogin implements Filter {
    private RegisterService registerService;
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springmvc.xml");
        registerService = (RegisterService)applicationContext.getBean("registerServiceImpl");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //1、判断cookie是否保存的自动登录的信息
        //1.1强制类型转换
        HttpServletRequest req = (HttpServletRequest)request;
        //System.out.println((req==request)+".................");
        //1.2获取所有的cookie

        Cookie[] cks = req.getCookies();
        //1.3定义自动登录的cookie
        Cookie autoCk = null;
        //1.4遍历cks数组
        for(int i = 0;i<cks.length;i++){
            if("autoLogin".equals(cks[i].getName())){
                autoCk = cks[i];
                break;
            }
        }
        //1.5是否保存自动登陆的cookie
        if(autoCk!=null){
            //3、获取用户名和密码，
            String up = URLDecoder.decode(autoCk.getValue(), "UTF-8");
            String username = up.split(":")[0];
            String password = up.split(":")[1];
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(username);
            userInfo.setPassword(password);
            //4然后调用业务层的方法，根据用户名和密码查询用户的信息user
            Boolean flag = registerService.loginInto(userInfo);
            //5、用户名和密码是否正确  user=!=null
            if(flag){
                //将用户信息保存到session中
                req.getSession().setAttribute("user",username);
            }
        }
        //2放行
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
