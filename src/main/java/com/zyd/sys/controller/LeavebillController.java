package com.zyd.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.common.util.DataGridViewResult;
import com.zyd.common.util.SystemConstant;
import com.zyd.sys.Vo.LeavebillVo;
import com.zyd.sys.entity.Leavebill;
import com.zyd.sys.entity.User;
import com.zyd.sys.service.LeavebillService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyd
 * @since 2021-09-16
 */
@RestController
@RequestMapping("/sys/leavebill")
public class LeavebillController {
    @Resource
    private LeavebillService leavebillService;

    /**
     * 请假条管理
     *
     * @param leavebillVo
     * @param session
     * @return
     */
    @RequestMapping("/leavebillList")
    public DataGridViewResult leavebillList(LeavebillVo leavebillVo, HttpSession session){
        //创建分页对象
        IPage<Leavebill> page =new Page<>(leavebillVo.getPage(),leavebillVo.getLimit());
        //获取登陆对象
        User loginUser = (User) session.getAttribute(SystemConstant.LOGINUSER);
        leavebillVo.setUserid(loginUser.getId());
        //调用返回数据
        IPage<Leavebill> leaveBillList = leavebillService.findLeaveBillList(page, leavebillVo);
        return  new DataGridViewResult(leaveBillList.getTotal(),leaveBillList.getRecords());

    }

}

