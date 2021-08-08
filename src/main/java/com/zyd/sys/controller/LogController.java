package com.zyd.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.sys.Vo.LogVo;
import com.zyd.sys.entity.Log;
import com.zyd.sys.service.LogService;
import com.zyd.sys.util.DataGridViewResult;
import com.zyd.sys.util.JSONResult;
import com.zyd.sys.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyd
 * @since 2021-08-01
 */
@RestController
@RequestMapping("/sys/log")
public class LogController {

    @Resource
    private LogService logService;

    @RequestMapping("/logList")
    public DataGridViewResult log(LogVo logVo){
        //创建分页信息，参数1：当前页码；参数2：每页显示数量
        IPage<Log> page=new Page<Log>(logVo.getPage(),logVo.getLimit()) ;
        //创建条件构造起
        QueryWrapper<Log> queryWrapper=new QueryWrapper<Log>();
        //操作类型
        queryWrapper.eq(StringUtils.isNotEmpty(logVo.getType()),"type",logVo.getType());
        //操作人
       queryWrapper.like(StringUtils.isNotEmpty(logVo.getLoginname()),"loginname",logVo.getLoginname());
        //操作时间 大于等于创建时间ge 小于等于创建时间le
        queryWrapper.ge(logVo.getStartTime()!=null,"createtime",logVo.getStartTime());
        queryWrapper.le(logVo.getEndTime()!=null,"createtime",logVo.getEndTime());

        //根据创建时间降序
        queryWrapper.orderByDesc("createtime");

        //调用查询日志列表的方法
        logService.page(page, queryWrapper);
        //返回数据 参数1：总页数;参数2：数据
        return  new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    @RequestMapping("/delete")
    public JSONResult delete(String ids){
        //将字符串拆分成字符数组
        String[] idStr=ids.split(",");
        //判断是否删除
        if (logService.removeByIds(Arrays.asList(idStr))){
            return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }
}

