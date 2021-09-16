package com.zyd.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyd.sys.Vo.LeavebillVo;
import com.zyd.sys.entity.Leavebill;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyd
 * @since 2021-09-16
 */
public interface LeavebillService extends IService<Leavebill> {


    IPage<Leavebill> findLeaveBillList(IPage<Leavebill> page, LeavebillVo leavebillVo);
}
