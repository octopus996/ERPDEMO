package com.zyd.sys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyd.sys.Vo.LeavebillVo;
import com.zyd.sys.entity.Leavebill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyd
 * @since 2021-09-16
 */
public interface LeavebillMapper extends BaseMapper<Leavebill> {

    IPage<Leavebill> findLeaveBillList(@Param("page") IPage<Leavebill> page, @Param("leavebill") LeavebillVo leavebillVo);
}
