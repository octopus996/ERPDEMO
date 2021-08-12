package com.zyd.sys.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.sys.Vo.DeptVo;
import com.zyd.sys.entity.Dept;
import com.zyd.sys.service.DeptService;
import com.zyd.sys.util.DataGridViewResult;
import com.zyd.sys.util.JSONResult;
import com.zyd.sys.util.SystemConstant;
import com.zyd.sys.util.TreeNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

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

    /**
     * 加载左侧树节点
     * @return
     */
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

    /**
     * 部门列表
     * @param deptVo
     * @return
     */
    @RequestMapping("/deptlist")
    public DataGridViewResult deptlist(DeptVo deptVo){
        //创建条件构造器对象
        QueryWrapper<Dept> queryWrapper=new QueryWrapper<Dept>();
        //创建分页
        IPage<Dept> page=new Page<Dept>(deptVo.getPage(),deptVo.getLimit());
        //部门名称查询
        queryWrapper.like(StringUtils.isNotEmpty(deptVo.getTitle()),"title",deptVo.getTitle());
        //地址
        queryWrapper.like(StringUtils.isNoneEmpty(deptVo.getAddress()),"address",deptVo.getAddress());
        //部门id
        queryWrapper.eq(deptVo.getId()!=null,"id",deptVo.getId()).or().
                eq(deptVo.getId()!=null,"pid",deptVo.getId());
        //排序
        queryWrapper.orderByAsc("id");
        //调用查询的方法
        deptService.page(page,queryWrapper);
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @RequestMapping("/addDept")
    public JSONResult addDept(Dept dept){
        dept.setCreatetime(new Date());
        if (deptService.save(dept)){
           return SystemConstant.ADD_SUCCESS;
        }
        return  SystemConstant.ADD_ERROR;
    }

    /**
     * 更新部门
     * @param dept
     * @return
     */
    @RequestMapping("/updateDept")
    public JSONResult updateDept(Dept dept){
        if (deptService.updateById(dept)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }

    /**
     * 判断有无子部门
     * @param id
     * @return
     */
    @RequestMapping("/checkDeptHasChildren")
    public String checkDeptHasChildren(int id){
        Map<String,Object> map=new HashMap();
        //创建条件构造器
        QueryWrapper<Dept> queryWrapper =new QueryWrapper<Dept>();
        queryWrapper.eq("pid",id);
        if (deptService.count(queryWrapper)>0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"存在子节点，无法删除！");


        }else {
            map.put(SystemConstant.EXIST,false);
        }

        return JSON.toJSONString(map);
    }
    /**
     * 删除部门
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int  id){
        if (deptService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }
}

