package com.zyd.sys.Vo;

import com.zyd.sys.entity.Log;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class LogVo extends Log {
    private int page;//当前页码
    private int limit;//每页的个数

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;//开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;//结束时间

    public LogVo(){

    }

    public LogVo(int page, int limit, Date startTime, Date endTime) {
        this.page = page;
        this.limit = limit;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LogVo(String content, String type, String loginname, Integer userid, String loginip, Date createtime, int page, int limit, Date startTime, Date endTime) {
        super(content, type, loginname, userid, loginip, createtime);
        this.page = page;
        this.limit = limit;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


}
