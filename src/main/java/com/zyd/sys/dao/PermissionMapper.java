package com.zyd.sys.dao;

import com.zyd.sys.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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

    @Select("select pid from sys_role_permission where rid=#{roleId}")
    List<Integer> findRolePermissionByRoleId(int roleId);

    @Select("select pid from sys_role_permission where rid=#{roleId}")
    Set<Integer> findPermissionIdsByRoleId(Integer roleId);
}
