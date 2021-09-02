package com.zyd.sys.dao;

import com.zyd.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyd
 * @since 2021-08-18
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Delete("delete from sys_role_user where rid=#{id}")
    void deleteUserByRoleId(Serializable id);
    @Delete("delete from sys_role_permission where rid=#{id}")
    void deletePermissionByRoleId(Serializable id);

    @Insert(" insert into sys_role_permission(rid,pid)  values(#{rid},#{pid})")
    void insertRolePermissoin(@Param("rid") int rid, @Param("pid") String pid);

    @Select("select rid from sys_role_user where uid=#{id}")
    Set<Integer> findRoleByUserId(Integer id);
}
