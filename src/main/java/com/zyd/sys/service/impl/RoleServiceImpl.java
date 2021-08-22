package com.zyd.sys.service.impl;

import com.zyd.sys.entity.Role;
import com.zyd.sys.dao.RoleMapper;
import com.zyd.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyd
 * @since 2021-08-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Override
    public boolean removeById(Serializable id) {

        //根据角色id分别删除sys_role_user中的数据，sys_role_permission中的数据
        roleMapper.deleteUserByRoleId(id);
        roleMapper.deletePermissionByRoleId(id);
        return super.removeById(id);
    }

    @Override
    public boolean saveRolePermission(int rid, String ids) {
        try {
            //先删除原有的权限
            roleMapper.deletePermissionByRoleId(rid);
            //保存选中的权限
            String[] pids = ids.split(",");
            for (int i = 0; i < pids.length; i++) {
                roleMapper.insertRolePermissoin(rid,pids[i]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
