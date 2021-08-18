package com.zyd.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.sys.Vo.RoleVo;
import com.zyd.sys.entity.Role;

import com.zyd.sys.service.RoleService;
import com.zyd.sys.util.DataGridViewResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @RequestMapping("rolelist")
    public DataGridViewResult rolelist(RoleVo roleVo){
        //创建分页构造器
        IPage page=new Page(roleVo.getPage(),roleVo.getLimit());
        //创建条件构造器
        QueryWrapper<Role> queryWrapper=new QueryWrapper();
        //角色编码搜索
        queryWrapper.like(StringUtils.isNotEmpty(roleVo.getRolecode()),"rolecode",roleVo.getRolecode());
        //角色名称搜索
        queryWrapper.like(StringUtils.isNotEmpty(roleVo.getRolename()),"rolename",roleVo.getRolename());
        //返回数据
        roleService.page(page,queryWrapper);
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }


}

