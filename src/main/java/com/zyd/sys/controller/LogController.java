package com.zyd.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.sys.Vo.LogVo;
import com.zyd.sys.entity.Log;
import com.zyd.sys.service.LogService;
import com.zyd.sys.util.DataGridViewResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public DataGridViewResult login(LogVo logVo){
        //创建分页信息，参数1：当前页码；参数2：每页显示数量
        IPage<Log> page=new Page<Log>(logVo.getPage(),logVo.getLimit()) ;
        //创建条件构造起
        QueryWrapper<Log> queryWrapper=new QueryWrapper<Log>();
        //调用查询日志列表的方法
        IPage<Log> logIPage = logService.page(page, queryWrapper);
        //返回数据 参数1：总页数;参数2：数据
        return  new DataGridViewResult(logIPage.getTotal(),logIPage.getRecords());
    }
}

