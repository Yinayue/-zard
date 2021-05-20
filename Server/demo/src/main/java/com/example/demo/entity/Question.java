package com.example.demo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

public class Question extends Model<Question> {
    private static final long serialVersionUID = 1L;



    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    private Long uid;
    private String direction;
    private String region;
    private String elevator;
    private String renovation;

    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUid() {
        return uid;
    }

    public String getDirection() {
        return direction;
    }

    public String getElevator() {
        return elevator;
    }

    public String getRegion() {
        return region;
    }

    public String getRenovation() {
        return renovation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setRenovation(String renovation) {
        this.renovation = renovation;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
