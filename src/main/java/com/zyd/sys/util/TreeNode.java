package com.zyd.sys.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 树节点属性类
 */
public class TreeNode {

    private Integer id;//菜单节点编号
    //返回json数据格式时生效
    @JsonProperty(value = "parentId")
    private Integer pid;//父节点菜单编号
    private String title;//菜单节点名称
    private String icon;//菜单节点图标
    private String href;//菜单路径
    private Boolean spread;//是否展开
    private String checkArr="0";//是否选中
    private String rolecode;//角色编码
    private String rolename;//角色名称
    private String remark;//备注

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(String checkArr) {
        this.checkArr = checkArr;
    }

    //子节点菜单
    private List<TreeNode> children = new ArrayList<TreeNode>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public TreeNode() {
    }

    /**
     * 构建树节点菜单
     *
     * @param id     节点编号
     * @param pid    父节点
     * @param title  节点标题
     * @param icon   节点图标
     * @param href   节点菜单路径
     * @param spread 节点展开状态
     */
    public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread, String checkArr, List<TreeNode> children) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
        this.checkArr = checkArr;
        this.children = children;
    }
}