package com.zyd.bus.Vo;

import com.zyd.bus.entity.GoodsType;

public class GoodsTypeVo extends GoodsType {
    private Integer page;//当前页码
    private Integer limit;//每页显示个数

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

    public GoodsTypeVo(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

    public GoodsTypeVo() {
    }
}
