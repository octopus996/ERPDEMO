package com.zyd.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.common.util.DataGridViewResult;
import com.zyd.common.util.JSONResult;
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
import java.util.Date;

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
    @RequestMapping("/addLeavebill")
    public JSONResult addLeavebill(Leavebill leavebill,HttpSession session){
        //获取当前登录对象
        User loginUser = (User) session.getAttribute(SystemConstant.LOGINUSER);
        //设置请假人
        leavebill.setUserid(loginUser.getId());
        //设置创建时间
        leavebill.setCreatetime(new Date());
        //设置审批人
        leavebill.setCheckPerson(loginUser.getMgr());
        if (leavebill.getState()!=null){
            //新创建
            if (leavebill.getState()==SystemConstant.LEAVE_CREATED){
                leavebill.setState(SystemConstant.LEAVE_CREATED);
            }else if (leavebill.getState()==SystemConstant.LEAVE_CHECKING){
                //待审批
                leavebill.setState(SystemConstant.LEAVE_CHECKING);
            }
        }
        if (leavebillService.save(leavebill)){
            return  SystemConstant.ADD_SUCCESS;
        }else {
            return SystemConstant.ADD_SUCCESS;
        }

    }
}

