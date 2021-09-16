package com.zyd.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyd
 * @since 2021-09-16
 */
@TableName("sys_leavebill")
public class Leavebill implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 请假编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 请假标题
     */
    private String title;

    /**
     * 请假内容
     */
    private String content;

    /**
     * 请假天数
     */
    private Double days;

    /**
     * 请假开始时间
     */
    private LocalDateTime leavetime;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 请假人id
     */
    private Integer userid;

    /**
     * 状态：0-未提交，1-待审批，2-审核通过，3-审核不通过，4-已放弃
     */
    private Integer state;

    /**
     * 审批人
     */
    @TableField("checkPerson")
    private Integer checkPerson;

    /**
     * 请假单提交时间
     */
    private LocalDateTime committime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getDays() {
        return days;
    }

    public void setDays(Double days) {
        this.days = days;
    }

    public LocalDateTime getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(LocalDateTime leavetime) {
        this.leavetime = leavetime;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(Integer checkPerson) {
        this.checkPerson = checkPerson;
    }

    public LocalDateTime getCommittime() {
        return committime;
    }

    public void setCommittime(LocalDateTime committime) {
        this.committime = committime;
    }

    @Override
    public String toString() {
        return "Leavebill{" +
        "id=" + id +
        ", title=" + title +
        ", content=" + content +
        ", days=" + days +
        ", leavetime=" + leavetime +
        ", createtime=" + createtime +
        ", userid=" + userid +
        ", state=" + state +
        ", checkPerson=" + checkPerson +
        ", committime=" + committime +
        "}";
    }
}
