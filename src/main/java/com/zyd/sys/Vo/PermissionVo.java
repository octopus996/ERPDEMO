package com.zyd.sys.Vo;

import com.zyd.sys.entity.Permission;

public class PermissionVo  extends Permission {
    private Integer page;//当前页码
    private Integer limit;//每页展示的数量

    public PermissionVo(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

    public PermissionVo() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
