package com.zyd.sys.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.sys.Vo.PermissionVo;
import com.zyd.sys.entity.Permission;
import com.zyd.sys.entity.User;
import com.zyd.sys.service.PermissionService;
import com.zyd.sys.service.RoleService;
import com.zyd.sys.service.UserService;
import com.zyd.sys.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/sys/menu")
public class MenuController {
    @Resource
    private PermissionService permissionService;
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    /**
     * 加载首页左侧菜单树
     * @param session
     * @return
     */
    @RequestMapping("/LoadIndexLeftMenuTree")
    public DataGridViewResult LoadIndexLeftMenuTree(HttpSession session){

        //创造条件构造器
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<Permission>();
        //菜单类型type只查询menu(type未menu的才是菜单)
        queryWrapper.eq("type",SystemConstant.TYPE_MENU);


        //从session中获取当前登录用户
        User loginUser = (User) session.getAttribute(SystemConstant.LOGINUSER);
        //创建集合保存权限菜单
        List<Permission> permissions=new ArrayList<Permission>();
        //判断当前用户是什么角色
        if (loginUser.getType()==SystemConstant.SUPERUSER){
            //调用查询权限菜单列表的方法
           permissions= permissionService.list(queryWrapper);

        }else {
            //普通用户需要按照角色何权限查询
           permissions  = permissionService.list(queryWrapper);
           //根据用户id查询该用户所拥有的角色id
            Set<Integer> currentRoleIds = roleService.findRoleByUserId(loginUser.getId());
            //创建一个存权限id的set集合
            Set<Integer> permissionSet=new HashSet<>();
            //更具角色id查询用户哪些权限
            for (Integer currentRoleId : currentRoleIds) {
                List<Integer> rolePermissionByRoleId = permissionService.findRolePermissionByRoleId(currentRoleId);
                permissionSet.addAll(rolePermissionByRoleId);
            }
            //判断权限是否存在
            if (permissionSet!=null && permissionSet.size()>0){
                queryWrapper.in("id",permissionSet);
                permissionService.list(queryWrapper);
            }
        }
        //创建集合保存树节点
        List<TreeNode> treeNodes=new ArrayList<TreeNode>();
        //循环遍历权限菜单列表
        for (Permission permission : permissions) {
            //判断当前节点是否展开，是则为true,不是则为false
            boolean spread =SystemConstant.SPREAD==permission.getOpen()?true:false;
            TreeNode treeNode=new TreeNode();

            treeNode.setHref(permission.getHref());//菜单路径
            treeNode.setIcon(permission.getIcon());//菜单图标
            treeNode.setId(permission.getId());//菜单节点id
            treeNode.setPid(permission.getPid());//父节点标号
            treeNode.setTitle(permission.getTitle());//菜单名称
            treeNode.setSpread(spread);//是否展开 1为展开 0未不展开
            //将树节点对象添加到树节点集合
            treeNodes.add(treeNode);
        }
        //构建层级关系
        List<TreeNode> build = TreeNodeBuilder.build(treeNodes, 1);

        return new DataGridViewResult(build);
    }

    /**
     * 加载左侧菜单节点
     * @return
     */
    @RequestMapping("/loadMenuTreeLeft")
    public DataGridViewResult loadMenuTreeLeft(){
        //创造条件构造器
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<Permission>();
        //菜单类型type只查询menu(type为menu的才是菜单)
        queryWrapper.eq("type",SystemConstant.TYPE_MENU);

        //查询所有菜单
        List<Permission> permissions = permissionService.list(queryWrapper);
        //创建集合保存权限菜单

        List<TreeNode> treeNodes=new ArrayList<>();
        //遍历权限菜单列表
        for (Permission permission : permissions) {

            TreeNode treeNode=new TreeNode();
            //判断当前节点是否展开，是则为true,不是则为false
            boolean spread=SystemConstant.SPREAD==permission.getOpen()?true:false;
            treeNode.setSpread(spread);//是否打开
            treeNode.setId(permission.getId());//菜单节点id
            treeNode.setPid(permission.getPid());//菜单节点父id
            treeNode.setTitle(permission.getTitle());//菜单名称
            treeNode.setHref(permission.getHref());//菜单路径

            //将树节点对象添加到树节点集合
            treeNodes.add(treeNode);

        }
        //List<TreeNode> build = TreeNodeBuilder.build(treeNodes, 1);
        return new DataGridViewResult(treeNodes);
    }

    /**
     * 遍历菜单列表
     * @param permissionVo
     * @return
     */
    @RequestMapping("/menulist")
    public DataGridViewResult menulist(PermissionVo permissionVo){
        //将建条件构造器
        QueryWrapper<Permission> queryWrapper=new QueryWrapper();
        //创建分页构造器
        IPage<Permission> page=new Page(permissionVo.getPage(),permissionVo.getLimit());
        //只查询菜单名称
        //queryWrapper.eq("type",SystemConstant.TYPE_MENU);
        //搜索菜单名称
        queryWrapper.like(StringUtils.isNotEmpty(permissionVo.getTitle()),"title",permissionVo.getTitle());
        //id
        queryWrapper.eq(permissionVo.getId()!=null,"id",permissionVo.getId()).or()
                .eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        //根据id排序
        queryWrapper.orderByAsc("id");
        //调用查询方法
        permissionService.page(page,queryWrapper);
        return  new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    /**
     * 添加菜单
     * @param permission
     * @return
     */
    @RequestMapping("addMenu")
    public JSONResult addMenu(Permission permission){
        permission.setType("menu");
        if (permissionService.save(permission)){
            return  SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }

    /**
     * 更新部门信息
     * @param permission
     * @return
     */
    @RequestMapping("updateMenu")
    public JSONResult updateMenu(Permission permission){
        if (permissionService.updateById(permission)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }

    /**
     * 判断是否存在子部门
     * @param id
     * @return
     */
    @RequestMapping("checkMenuHasChildren")
    public String checkMenuHasChildren(int id){
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("pid",id);
        if (permissionService.count(queryWrapper)>0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"存在子部门，无法删除！");
        }else{
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);

    }

    /**
     * 删除部门
     * @param permission
     * @return
     */
    @RequestMapping("/deleteById")
    public JSONResult deleteById(Permission permission){
        if (permissionService.removeById(permission)){
         return    SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }

}
