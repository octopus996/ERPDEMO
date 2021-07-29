package com.zyd.sys.controller;


import com.zyd.sys.Vo.LoginUserVo;
import com.zyd.sys.util.JSONResult;
import com.zyd.sys.util.SystemConstant;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyd
 * @since 2021-07-20
 */

@RestController
@RequestMapping("/sys/user")
public class UserController {

    @PostMapping("/login")
    public JSONResult login(String loginname, String pwd, HttpServletRequest request){

        //获取当前登录主体
        Subject subject = SecurityUtils.getSubject();
        //创建令牌对象
        UsernamePasswordToken token=new UsernamePasswordToken(loginname,pwd);
        //System.out.println(token);
        try {
            //执行登录
            subject.login(token);
            //获取当前登录用户
            LoginUserVo loginUserVo= (LoginUserVo) subject.getPrincipal();
            //保存当前用户
            request.getSession().setAttribute(SystemConstant.LOGINUSER,loginUserVo.getUser());
            return SystemConstant.SUCCESS;
                    } catch (AuthenticationException e) {
            e.printStackTrace();
        }


        return SystemConstant.ERROR;
    }

}

