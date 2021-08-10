package com.zyd.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyd.sys.entity.Dept;
import com.zyd.sys.service.DeptService;
import com.zyd.sys.util.DataGridViewResult;
import com.zyd.sys.util.SystemConstant;
import com.zyd.sys.util.TreeNode;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyd
 * @since 2021-08-09
 */
@RestController
@RequestMapping("/sys/dept")
public class DeptController {

    @Resource
    private DeptService deptService;
    @RequestMapping("/loadDeptTreeLeft")
    public DataGridViewResult loadDeptTreeLeft(){
        QueryWrapper<Dept> queryWrapper=new QueryWrapper<Dept>();
        List<Dept> depts = deptService.list(queryWrapper);
        List<TreeNode> treeNodes=new ArrayList<TreeNode>();
        for (Dept dept:depts) {
            TreeNode treeNode=new TreeNode();
            boolean spread= SystemConstant.SPREAD==dept.getOpen()?true:false;
            treeNode.setId(dept.getId());
            treeNode.setPid(dept.getPid());
            treeNode.setTitle(dept.getTitle());
            treeNode.setSpread(spread);
            treeNodes.add(treeNode);
        }

        return new DataGridViewResult(treeNodes);
    }

}

