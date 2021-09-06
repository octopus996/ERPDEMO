package com.zyd.bus.Vo;

import com.zyd.bus.entity.Customer;

public class CustomerVo extends Customer {
    private Integer page;//当前页码
    private Integer limit;//一页显示个数

    public CustomerVo(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

    public CustomerVo() {
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
