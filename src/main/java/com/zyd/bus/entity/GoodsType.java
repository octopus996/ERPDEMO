package com.zyd.bus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyd
 * @since 2021-09-07
 */
@TableName("bus_goods_type")
public class GoodsType implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 分类编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    private String title;

    /**
     * 父类编号
     */
    private Integer pid;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 是否展开（0展开，1不展开）
     */
    private Integer open;

    /**
     * 备注
     */
    private String remark;


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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
        "id=" + id +
        ", title=" + title +
        ", pid=" + pid +
        ", icon=" + icon +
        ", open=" + open +
        ", remark=" + remark +
        "}";
    }
}
