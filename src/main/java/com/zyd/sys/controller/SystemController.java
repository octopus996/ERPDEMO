package com.zyd.sys.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/sys")
public class SystemController {

    /**
     * 去到后台首页
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(){
        return "system/home/index";
    }


    @RequestMapping("/desktopManager")
    public String toDesktopManager(){

        return "system/home/desktopManager";
    }

    /**
     * 去到日志管理页面
     * @return
     */
    @RequestMapping("/logManager")
    public String toLogManager(){
        return "system/log/logManager";
    }

    /**
     * 去到公告管理页面
     * @return
     */
    @RequestMapping("toNoticeManager")
    public  String toNoticeManager(){
        return "system/notice/noticeManager";
    }

    /**
     * 去到部门管理页面
     * @return
     */
    @RequestMapping("/toDeptManager")
    public String toDeptManager(){
        return "system/dept/deptManager";
    }
    @RequestMapping("/toDeptLeft")
    public String toDeptLeft(){
        return "system/dept/left";
    }
    @RequestMapping("/toDeptRight")
    public String toDeptRight(){
        return "system/dept/right";
    }
}
