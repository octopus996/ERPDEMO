package com.zyd.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyd.sys.entity.User;
import com.zyd.sys.dao.UserMapper;
import com.zyd.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyd
 * @since 2021-07-20
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByName(String userName) throws Exception{
        //创建条件构造器对象
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        //column:数据库中的列名；参数
        queryWrapper.eq("loginname",userName);

        return userMapper.selectOne(queryWrapper);
    }
}
