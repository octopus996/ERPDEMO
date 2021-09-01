package com.zyd.sys.service;

import com.zyd.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyd
 * @since 2021-08-18
 */
public interface RoleService extends IService<Role> {

    boolean saveRolePermission(int rid, String ids);

    List<Integer> findRoleByUserId(Integer id);
}
