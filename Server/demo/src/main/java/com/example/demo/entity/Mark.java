package com.example.demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;
@TableName("mark")
public class Mark extends Model<Mark> {
    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    private Long uid;
    private Long hid;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Long getHid() {
        return hid;
    }

    public Mark setHid(Long hid) {
        this.hid = hid;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Mark setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUid() {
        return uid;
    }

    public Mark setUid(Long uid) {
        this.uid = uid;
        return this;
    }

}
