package com.zyd.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.common.util.DataGridViewResult;
import com.zyd.sys.Vo.LeavebillVo;
import com.zyd.sys.entity.Leavebill;
import com.zyd.sys.service.LeavebillService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

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
@Controller
@RequestMapping("/sys/leavebill")
public class LeavebillController {
    @Resource
    private LeavebillService leavebillService;

    @RequestMapping("/leavebillList")
    public DataGridViewResult leavebillList(LeavebillVo leavebillVo, HttpSession session){
        IPage<Leavebill> page =new Page<>(leavebillVo.getPage(),leavebillVo.getLimit());
        IPage<Leavebill> leaveBillList = leavebillService.findLeaveBillList(page, leavebillVo);
        return  new DataGridViewResult(page.getTotal(),page.getRecords());

    }

}

