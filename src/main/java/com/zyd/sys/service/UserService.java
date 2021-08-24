package com.zyd.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyd.sys.Vo.UserVo;
import com.zyd.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyd
 * @since 2021-07-20
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    User findUserByName(String userName) throws Exception;

    IPage<User> findUserListByPage(IPage<User> page,UserVo userVo);

}
