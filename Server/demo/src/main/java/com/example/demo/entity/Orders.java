package com.example.demo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

public class Orders extends Model<Orders>{

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long bid;
    private Long hid;

    public Orders setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Orders setHid(Long hid) {
        this.hid = hid;
        return this;
    }

    public Long getHid() {
        return hid;
    }

    public Orders setBid(Long bid) {
        this.bid = bid;
        return this;
    }

    public Long getBid() {
        return bid;
    }


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
