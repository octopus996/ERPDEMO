package com.zyd.sys.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.sys.Vo.RoleVo;
import com.zyd.sys.entity.Permission;
import com.zyd.sys.entity.Role;

import com.zyd.sys.service.PermissionService;
import com.zyd.sys.service.RoleService;
import com.zyd.sys.util.DataGridViewResult;
import com.zyd.sys.util.JSONResult;
import com.zyd.sys.util.SystemConstant;
import com.zyd.sys.util.TreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyd
 * @since 2021-08-18
 */
@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @RequestMapping("/rolelist")
    public DataGridViewResult rolelist(RoleVo roleVo){
        //创建分页构造器
        IPage page=new Page(roleVo.getPage(),roleVo.getLimit());
        //创建条件构造器
        QueryWrapper<Role> queryWrapper=new QueryWrapper();
        //角色编码搜索
        queryWrapper.like(StringUtils.isNotEmpty(roleVo.getRolecode()),"rolecode",roleVo.getRolecode());
        //角色名称搜索
        queryWrapper.like(StringUtils.isNotEmpty(roleVo.getRolename()),"rolename",roleVo.getRolename());
        //根据id升序
        queryWrapper.orderByAsc("id");
        //返回数据
        roleService.page(page,queryWrapper);
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    /**
     * 增加角色
     * @param role
     * @return
     */
    @RequestMapping("/addRole")
    public JSONResult addRole(Role role){
        role.setCreatetime(new Date());
        if (roleService.save(role)){
           return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
   }

    /**
     * 更新角色
     * @param role
     * @return
     */
   @RequestMapping("/updateRole")
    public JSONResult updateRole(Role role){
        if (roleService.updateById(role)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
   }

    /**
     * 删除角色
     * @param id
     * @return
     */
   @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
       if (roleService.removeById(id)){
           return SystemConstant.DELETE_SUCCESS;
       }
       return SystemConstant.DELETE_ERROR;
   }

   @RequestMapping("/saveRolePermission")
   public JSONResult saveRolePermission(int id){

       return null;
   }
   @RequestMapping("/initPermissionByRoleId")
    public DataGridViewResult initPermissionByRoleId(int roleId){

       //创建条件构造器
       QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
       //列出所有的权限
       List<Permission> permissionList = permissionService.list(queryWrapper);
       //根据前端返回的角色id查询当前角色的权限id
       List<Integer> currentRolePermissionIds = permissionService.findRolePermissionByRoleId(roleId);
       //根据当前角色的权限id去查询权限
       List<Permission> currentPermissions=new ArrayList<Permission>();
       //判断当前角色是否拥有权限id
       if (currentRolePermissionIds!=null && currentRolePermissionIds.size()>0){
           //如果拥有权限id,则根据查询出来的菜单编号去查询菜单权限数据

            queryWrapper.in("id",currentRolePermissionIds);
            currentPermissions=permissionService.list(queryWrapper);
       }
       //构建TreeNode树节点
       List<TreeNode> treeNodes=new ArrayList<>();
       //遍历所有菜单权限列表
       for (Permission p1:permissionList){
           //定义变量，保存菜单是否被选中
           String checkArr="0";//不选中
           //内层循环当前角色拥有的菜单及权限，判断当前角色拥有哪些菜单和权限，将拥有的权限要选中
           for (Permission p2:currentPermissions){
               if (p1.getId()==p2.getId()){
                   checkArr="1";//选中
                   break;
               }
           }
           boolean spread=(p1.getOpen()==null || p1.getOpen()==1) ? true:false;
           TreeNode treeNode=new TreeNode();
           treeNode.setPid(p1.getPid());
           treeNode.setHref(p1.getHref());
           treeNode.setTitle(p1.getTitle());
           treeNode.setId(p1.getId());
           treeNode.setSpread(spread);
           treeNode.setIcon(p1.getIcon());
           treeNodes.add(treeNode);
       }
       return new DataGridViewResult(treeNodes);
   }

}

