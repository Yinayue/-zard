package com.example.demo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

public class Order extends Model<Order>{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer bid;
    private Integer hid;

    public Order setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Order setHid(Integer hid) {
        this.hid = hid;
        return this;
    }

    public Integer getHid() {
        return hid;
    }

    public Order setBid(Integer bid) {
        this.bid = bid;
        return this;
    }

    public Integer getBid() {
        return bid;
    }


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
