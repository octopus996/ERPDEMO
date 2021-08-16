package com.zyd.sys.service;

import com.zyd.sys.dao.PermissionMapper;
import com.zyd.sys.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyd
 * @since 2021-07-29
 */

public interface PermissionService extends IService<Permission> {

}
