package com.zyd.sys.Vo;

import com.zyd.sys.entity.Leavebill;

public class LeavebillVo extends Leavebill {
    private Integer page;//当前页码
    private Integer limit;//每页显示数量

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
