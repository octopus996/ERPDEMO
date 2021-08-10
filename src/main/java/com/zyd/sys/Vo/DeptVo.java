package com.zyd.sys.Vo;

import com.zyd.sys.entity.Dept;
import com.zyd.sys.entity.Log;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DeptVo extends Dept {
    private int page;//当前页码
    private int limit;//每页的个数

    public DeptVo(int page, int limit) {
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
