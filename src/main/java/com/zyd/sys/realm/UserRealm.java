package com.zyd.sys.realm;

import com.zyd.sys.Vo.LoginUserVo;


import com.zyd.sys.entity.User;
import com.zyd.sys.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class UserRealm  extends AuthorizingRealm {

    @Resource
    private UserService userService;
    /**
     * 验证身份
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取当前登录的主体
        String userName= (String) token.getPrincipal();



        try {
            //根据用户名查询用户信息的方法
            User user=userService.findUserByName(userName);
            if (user!=null){
                //创建当前用户对象
                //创建登录用户对象，传入用户信息，角色列表，权限列表
                LoginUserVo loginUserVo=new LoginUserVo(user,null,null);
                //创建盐值（以用户名作为盐值）
                ByteSource salt=ByteSource.Util.bytes(user.getSalt());
                //参数1：当前登录对象  参数2：密码  参数3：盐值  参数4：域名
                SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(loginUserVo,user.getLoginpwd(),salt,"");
                return info;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //验证失败
        return null;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
