package com.zyd.sys.util;

public interface SystemConstant {

    /**
     * 当前登录用户的Key
     */
     String LOGINUSER ="loginUser" ;
    JSONResult SUCCESS = new JSONResult("登录成功！",true);
    JSONResult ERROR = new JSONResult("登录失败！账号或密码错误！",false);




    /**
     * 类型为菜单：用于首页左侧导航栏
     */
    String TYPE_MENU = "menu";

    /**
     * 类型为权限
     */
    String TYPE_PERMISSION = "permission";

    /**
     * 菜单是否展开，1展开
     */
    Integer SPREAD = 1;

    /**
     * 菜单是否展开，0不展开
     */
    Integer OPEN_FALSE = 0;

    /**
     * 角色为超级管理员
     */
    Integer SUPERUSER = 0;

    /**
     * 登录操作
     */
    String LOGIN_ACTION = "登录操作";
    /**
     * 注销操作
     */
    String LOGOUT_ACTION = "注销操作";
    /**
     * 查询操作
     */
    String SEARCH_ACTION = "查询操作";
    /**
     * 更新操作
     */
    String UPDATE_ACTION = "更新操作";
    /**
     * 添加操作
     */
    String ADD_ACTION = "添加操作";
    /**
     * 删除操作
     */
    String DELETE_ACTION = "删除操作";

    /**
     * 删除成功
     */
    JSONResult DELETE_SUCCESS = new JSONResult("删除成功", true);
    /**
     * 删除失败
     */
    JSONResult DELETE_ERROR = new JSONResult("删除失败", false);
    /**
     * 添加成功
     */
    JSONResult ADD_SUCCESS = new JSONResult("添加成功", true);
    /**
     * 添加失败
     */
    JSONResult ADD_ERROR = new JSONResult("添加失败", false);
    /**
     * 修改成功
     */
    JSONResult UPDATE_SUCCESS = new JSONResult("修改成功", true);
    /**
     * 修改失败
     */
    JSONResult UPDATE_ERROR = new JSONResult("修改失败", false);

    /**
     * 是否存在
     */
    String EXIST = "exist";
    /**
     * 验证信息
     */
    String MESSAGE = "message";


}
