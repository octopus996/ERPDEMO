package com.zyd.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyd.sys.Vo.LeavebillVo;
import com.zyd.sys.entity.Leavebill;
import com.zyd.sys.dao.LeavebillMapper;
import com.zyd.sys.service.LeavebillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyd
 * @since 2021-09-16
 */
@Service
@Transactional
public class LeavebillServiceImpl extends ServiceImpl<LeavebillMapper, Leavebill> implements LeavebillService {

    @Resource
    private LeavebillMapper leavebillMapper;


    @Override
    public IPage<Leavebill> findLeaveBillList(IPage<Leavebill> page, LeavebillVo leavebillVo) {
        return leavebillMapper.findLeaveBillList(page,leavebillVo);
    }
}
