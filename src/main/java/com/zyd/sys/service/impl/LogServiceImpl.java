package com.zyd.sys.service.impl;

import com.zyd.sys.entity.Log;
import com.zyd.sys.dao.LogMapper;
import com.zyd.sys.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyd
 * @since 2021-08-01
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
