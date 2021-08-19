package com.zyd.sys.service;

import com.zyd.sys.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyd
 * @since 2021-07-29
 */

public interface PermissionService extends IService<Permission> {

    List<Integer> findRolePermissionByRoleId(int roleId);
}
