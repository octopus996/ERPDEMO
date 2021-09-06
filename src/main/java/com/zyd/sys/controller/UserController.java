package com.zyd.sys.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.sys.Vo.LoginUserVo;
import com.zyd.sys.Vo.UserVo;
import com.zyd.sys.entity.Log;


import com.zyd.sys.entity.Permission;
import com.zyd.sys.entity.User;
import com.zyd.sys.service.LogService;
import com.zyd.sys.service.RoleService;
import com.zyd.sys.service.UserService;
import com.zyd.common.util.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;



import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private Permission permission;

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

    @RequestMapping("/userlist")
    public DataGridViewResult userlist(UserVo userVo){

        IPage page=new Page(userVo.getPage(),userVo.getLimit());

        IPage userListByPage = userService.findUserListByPage(page, userVo);


        return new DataGridViewResult(userListByPage.getTotal(),userListByPage.getRecords());
    }

    @RequestMapping("/loadUserByDeptId")
    public DataGridViewResult loadUserByDeptId(Integer deptId){
        //创建条件构造器
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        //只查询普通用户
        queryWrapper.eq("type",SystemConstant.NORMAL_USER);
        //查询该部门下的所有人
        queryWrapper.eq(deptId!=null,"deptid",deptId);
        //返回数据
        List<User> users = userService.list(queryWrapper);
        return new DataGridViewResult(users);
    }

    /**
     * 验证用户名是否存在
     * @param loginName
     * @return
     */
    @RequestMapping("/checkLoginName")
    public String checkLoginName(String loginName){
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("loginname",loginName);
        if (userService.list(queryWrapper).size()>0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"登录名已存在，请重新输入!");
        }else {
            map.put(SystemConstant.EXIST,false);
            map.put(SystemConstant.MESSAGE,"用户名可以使用！");

        }
        return JSON.toJSONString(map);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    public JSONResult addUser(User user){
        //入职日期
        user.setHiredate(new Date());
        //盐值
        String salt= UUIDUtil.randomUUID();
        //默认密码(默认密码123456，盐值，加密次数）
        user.setLoginpwd(PasswordUtil.md5(SystemConstant.DEFAULT_PWD,salt,SystemConstant.HASHITERATIONS));
        //用户类型
        user.setType(SystemConstant.NORMAL_USER);
        //默认头像
        user.setImgpath("defaultImage.jpg");
        //设置盐值
        user.setSalt(salt);
        //调用新增用户
        if (userService.save(user)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }
    @RequestMapping("/loadUserById")
    public DataGridViewResult loadUserById(Integer id){
        return new  DataGridViewResult(userService.getById(id));
    }

    @RequestMapping("/updateUser")
    public JSONResult updateUser(User user){

        if (userService.updateById(user)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }

    /**
     * 重置密码
     * @param user
     * @return
     */
    @RequestMapping("/resetPwd")
    public JSONResult resetPwd(User user){
        //盐值
        String salt=UUIDUtil.randomUUID();
        //重置密码
        user.setLoginpwd(PasswordUtil.md5(SystemConstant.DEFAULT_PWD,salt,SystemConstant.HASHITERATIONS));
        //重新设置盐值
        user.setSalt(salt);

        if (userService.updateById(user)){
            return SystemConstant.RESET_SUCCESS;
        }
        return SystemConstant.RESET_ERROR;
    }

    @RequestMapping("/initRoleByUserId")
    public DataGridViewResult initRoleByUserId(Integer id) {
        //查询所有的角色列表
        List<Map<String, Object>> mapList = roleService.listMaps();
        //根据前端传来的uid查询该用户
        Set<Integer> roleByUserId = roleService.findRoleByUserId(id);
        for (Map<String, Object> mapRoleId:mapList){
            boolean checkArr=false;//不选中
            //取出所有角色的id
            int roleId = (int) mapRoleId.get("id");
            for (Integer currentRoleId:roleByUserId){
                if(roleId == currentRoleId){
                   checkArr=true;
                   break;
                }
            }
            mapRoleId.put("LAY_CHECKED",checkArr);
        }

        return new DataGridViewResult(Long.valueOf(mapList.size()),mapList);
    }
    @RequestMapping("/saveUserRole")
    public JSONResult saveUserRole(int userId,String roleIds){
        if (userService.saveUserRole(userId,roleIds)){
            return SystemConstant.DISTRIBUTE_ROLE_SUCCESS;
        }
        return SystemConstant.DISTRIBUTE_ROLE_FALSE;
    }

}

