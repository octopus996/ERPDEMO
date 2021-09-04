package com.zyd.sys.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyd.sys.Vo.LoginUserVo;


import com.zyd.sys.entity.Permission;
import com.zyd.sys.entity.User;
import com.zyd.sys.service.PermissionService;
import com.zyd.sys.service.RoleService;
import com.zyd.sys.service.UserService;
import com.zyd.sys.util.SystemConstant;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm  extends AuthorizingRealm {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

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
                //**************************************认证管理部分****************************************
                //创建条件构造器
                QueryWrapper queryWrapper=new QueryWrapper();
                //只查询type为permission的菜单权限
                queryWrapper.eq("type",SystemConstant.TYPE_PERMISSION);
                //根据用户id查询当前用户所拥有的角色id
                Set<Integer> roleIdSet = roleService.findRoleByUserId(user.getId());
                //创建一个存放权限的set集合
                Set<Permission> permissionSet=new HashSet<>();
                //更具角色id查询所用户哪些权限
                for (Integer roleId : roleIdSet) {
                    permissionService.findRolePermissionByRoleId(roleId);
                    permissionSet.addAll(permissionSet);
                }
                //创建一个保存权限菜单的list集合
                List<Permission> list=new ArrayList<Permission>();
                //判断权限集合是否为空
                if (permissionSet!=null && permissionSet.size()>0){
                    queryWrapper.in("id",permissionSet);
                    list = permissionService.list(queryWrapper);
                }
                //设置权限编码
                Set<String> perCode=new HashSet<>();
                for (Permission permission : list) {
                    perCode.add(permission.getPercode());
                }
                loginUserVo.setPermissions(perCode);
                //**************************************认证管理部分****************************************
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
        //创建权限认证主体
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();

        //创建当前登录主体
        LoginUserVo loginUserVo = (LoginUserVo) principals.getPrimaryPrincipal();
        //获取当前用户拥有的权限
        Set<String> permissions = loginUserVo.getPermissions();
        //判断当前用户是否为超级管理员
        if (loginUserVo.getUser().getId()== SystemConstant.SUPERUSER){
            //如果是超级管理员
            simpleAuthorizationInfo.addStringPermission("*:*");
        }else {
            //如果是普通用户
            if (permissions!=null && permissions.size()>0){
                simpleAuthorizationInfo.addStringPermissions(permissions);
            }

        }
        return null;
    }
}
