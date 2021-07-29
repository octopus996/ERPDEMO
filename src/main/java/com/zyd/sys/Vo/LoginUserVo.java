package com.zyd.sys.Vo;

import com.zyd.sys.entity.User;

import java.util.Set;

public class LoginUserVo {
    private User user;//用户信息
    private Set<String> roles;//角色列表
    private Set<String> permissions;//权限列表


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }


    public LoginUserVo() {
    }

    /***
     * 登录用户
     * @param user
     * @param roles
     * @param permissions
     */
    public LoginUserVo(User user, Set<String> roles, Set<String> permissions) {
        this.user = user;
        this.roles = roles;
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "LoginUserVo{" +
                "user=" + user +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }
}
