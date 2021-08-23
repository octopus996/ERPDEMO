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

    /**
     * 去到菜单管理
     * @return
     */
    @RequestMapping("/toMenuManager")
    public String toMenuManager(){
        return "system/menu/menuManager";
    }
    @RequestMapping("/toMenuLeft")
    public String toMenuLeft(){
        return "system/menu/left";
    }
    @RequestMapping("/toMenuRight")
    public String toMenuRight(){
        return "system/menu/right";
    }

    /**
     * 去到权限管理页面
     * @return
     */
    @RequestMapping("/toPermissionManager")
    public String toPermissionManager(){
        return "system/permission/permissionManager";
    }
    @RequestMapping("/toPermissionLeft")
    public String toPermissionLeft(){
        return "system/permission/left";
    }
    @RequestMapping("toPermissionRight")
    public String toPermissionRight(){
        return "system/permission/right";
    }

    /**
     * 去到角色管理页面
     * @return
     */
    @RequestMapping("toRoleManager")
    public String toRoleManager(){
        return "system/role/roleManager";
    }

    /***
     * 去到用户管理页面
     * @return
     */
    @RequestMapping("/toUserManager")
    public String toUserManager(){
        return "system/user/userManager";
    }
    @RequestMapping("toUserLeft")
    public String toUserLeft(){
        return "system/user/left";
    }
    @RequestMapping("toUserRight")
    public String toUserRight(){
        return "system/user/right";
    }
}
