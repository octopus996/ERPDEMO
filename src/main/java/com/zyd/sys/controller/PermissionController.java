package com.zyd.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.sys.Vo.PermissionVo;
import com.zyd.sys.entity.Permission;
import com.zyd.sys.service.PermissionService;
import com.zyd.sys.util.DataGridViewResult;
import com.zyd.sys.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyd
 * @since 2021-07-29
 */
@RestController
@RequestMapping("/sys/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @RequestMapping("/permissionlist")
    public DataGridViewResult permissionlist(PermissionVo permissionVo){
        //创建分页构造器
        IPage page=new Page(permissionVo.getPage(),permissionVo.getLimit());
        //创建条件构造起
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        //只查询type为permission的权限
        queryWrapper.eq("type", SystemConstant.TYPE_PERMISSION);
        //权限名称
        queryWrapper.like(StringUtils.isNotEmpty(permissionVo.getTitle()),"title",permissionVo.getTitle());
        //权限编码
        queryWrapper.like(StringUtils.isNotEmpty(permissionVo.getPercode()),"percode",permissionVo.getPercode());
        //id
        queryWrapper.eq(permissionVo.getId()!=null,"id",permissionVo.getId())
                .or().eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        //按id升序
        queryWrapper.orderByAsc("id");
        //返回数据
        permissionService.page(page,queryWrapper);
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

}

