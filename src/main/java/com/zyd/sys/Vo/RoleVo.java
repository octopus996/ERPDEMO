package com.zyd.sys.Vo;


import com.zyd.sys.entity.Role;

public class RoleVo extends Role {
    private int page;//当前页码
    private int limit;//每页的个数

    public RoleVo(){

    }
    public RoleVo(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
