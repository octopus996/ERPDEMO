package com.zyd.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.sys.Vo.NoticeVo;
import com.zyd.sys.entity.Notice;
import com.zyd.sys.entity.User;
import com.zyd.sys.service.NoticeService;
import com.zyd.sys.util.DataGridViewResult;
import com.zyd.sys.util.JSONResult;
import com.zyd.sys.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyd
 * @since 2021-08-05
 */
@RestController
@RequestMapping("/sys/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    @RequestMapping("noticeList")
    public DataGridViewResult notice(NoticeVo noticeVo){
        //创建分页工具：参数1：当前页码 参数2：每页数量
        IPage<Notice> page=  new Page<Notice>(noticeVo.getPage(),noticeVo.getLimit());
        //创建条件构造器
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<Notice>();
        //公告标题
        queryWrapper.like(StringUtils.isNotEmpty(noticeVo.getTitle()),"title",noticeVo.getTitle());
        //发布人
        queryWrapper.eq(StringUtils.isNotEmpty(noticeVo.getOpername()),"opername",noticeVo.getOpername());
        //开始时间
        queryWrapper.ge(noticeVo.getStartTime()!=null,"createtime",noticeVo.getStartTime());
        //结束时间
        queryWrapper.le(noticeVo.getEndTime()!=null,"createtime",noticeVo.getEndTime());
        //调用查询内容的方法
        noticeService.page(page,queryWrapper);


        //返回数据   参数1：数据总量   参数2：数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    @PostMapping("addNotice")
    public JSONResult addNotice(Notice notice, HttpSession session){
        //取创建人
        User user= (User) session.getAttribute(SystemConstant.LOGINUSER);
        notice.setCreatetime(new Date());//创建时间
        notice.setOpername(user.getLoginname());//创建人
        //判断是否登录成功
        if (noticeService.save(notice)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }

    @RequestMapping("/delete")
    public JSONResult delete(String id){
        //将字符拆分未字符穿
        String[] idStr=id.split(",");
        //判断是否删除
        if (noticeService.removeByIds(Arrays.asList(idStr))){
           return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }

}

