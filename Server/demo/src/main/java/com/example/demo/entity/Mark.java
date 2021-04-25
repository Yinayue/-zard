package com.example.demo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

public class Mark extends Model<Mark> {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer uid;
    private Integer hid;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Integer getHid() {
        return hid;
    }

    public Mark setHid(Integer hid) {
        this.hid = hid;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Mark setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUid() {
        return uid;
    }

    public Mark setUid(Integer uid) {
        this.uid = uid;
        return this;
    }

}
