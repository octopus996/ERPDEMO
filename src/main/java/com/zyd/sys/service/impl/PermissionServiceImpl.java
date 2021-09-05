package com.zyd.sys.service.impl;

import com.zyd.sys.entity.Permission;
import com.zyd.sys.dao.PermissionMapper;
import com.zyd.sys.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyd
 * @since 2021-07-29
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public boolean removeById(Serializable id) {
        //删除第三张关系表的数据（sys_role_permission）
        //根据菜单id或权限id删除sys_role_permission
        permissionMapper.deleteRolePermissionByPid(id);
        return super.removeById(id);
    }

    @Override
    public List<Integer> findRolePermissionByRoleId(int roleId) {
        return permissionMapper.findRolePermissionByRoleId(roleId);
    }

    @Override
    public Set<Integer> findPermissionIdsByRoleId(Integer roleId) {
        return permissionMapper.findPermissionIdsByRoleId(roleId);
    }
}
