package com.zyd.sys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.sys.Vo.UserVo;
import com.zyd.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyd
 * @since 2021-07-20
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询我们的用户列表
     *
     *  @Paran映射的是XXXmapper.xml中预编译内容
     *
     * @param page      分页信息
     * @param userVo    查询条件
     * @return
     */
    IPage<User> findUserListByPage(@Param("page") IPage<User> page, @Param("user") UserVo userVo);

    @Delete("delete from sys_role_user where uid=#{id}")
    void deleteRoleByUserId(Serializable id);

    @Insert("insert into sys_role_user(uid,rid) values(#{uid},#{rid})")
    void insertUserRole(@Param("uid") int userId,@Param("rid") String id);





}
