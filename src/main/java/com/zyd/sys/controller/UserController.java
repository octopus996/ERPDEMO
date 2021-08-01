package com.zyd.sys.controller;


import com.zyd.sys.Vo.LoginUserVo;
import com.zyd.sys.entity.Log;

import com.zyd.sys.service.LogService;
import com.zyd.sys.util.JSONResult;
import com.zyd.sys.util.SystemConstant;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

    @Resource
    private LogService logService;

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
            //登录日志
            //操作内容，操作类型，操作人，操作人id，操作人ip，操作时间
            Log log=new Log("用户登录",SystemConstant.LOGIN_ACTION,
                    loginname,loginUserVo.getUser().getId(),
                    request.getRemoteAddr(),new Date());
            logService.save(log);
            return SystemConstant.SUCCESS;
                    } catch (AuthenticationException e) {
            e.printStackTrace();
        }


        return SystemConstant.ERROR;
    }

}

