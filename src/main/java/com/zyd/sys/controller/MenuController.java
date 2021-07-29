package com.zyd.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyd.sys.entity.Permission;
import com.zyd.sys.entity.User;
import com.zyd.sys.service.PermissionService;
import com.zyd.sys.util.DataGridViewResult;
import com.zyd.sys.util.SystemConstant;
import com.zyd.sys.util.TreeNode;
import com.zyd.sys.util.TreeNodeBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/sys/menu")
public class MenuController {
    @Resource
    private PermissionService permissionService;

    /**
     * 加载首页左侧菜单树
     * @param session
     * @return
     */
    @RequestMapping("LoadIndexLeftMenuTree")
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
}
