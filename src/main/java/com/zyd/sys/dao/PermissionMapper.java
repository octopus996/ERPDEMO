package com.zyd.sys.dao;

import com.zyd.sys.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyd
 * @since 2021-07-29
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Delete("delete from sys_role_permission where pid=#{id}" )
    void deleteRolePermissionByPid(Serializable id);
}
