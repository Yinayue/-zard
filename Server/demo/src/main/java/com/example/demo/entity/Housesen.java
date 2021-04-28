package com.example.demo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;

public class Housesen extends Model<Housesen> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    private long id;
    private long sid;
    private String direction;
    private String district;
    private String elevator;
    private int floor;
    private String address;
    private int layout;
    private float price;
    private String region;
    private String renovation;
    private int size;
    private int year;
    @TableField("delete_flag")
    private int deleteFlag;

    public long getId() {
        return id;
    }

    public Housesen setId(long id) {
        this.id = id;
        return this;
    }

    public long getSid() {
        return sid;
    }

    public Housesen setSid(long sid) {
        this.sid = sid;
        return this;
    }

    public String getDirection() {
        return direction;
    }

    public Housesen setDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public Housesen setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getElevator() {
        return elevator;
    }

    public Housesen setElevator(String elevator) {
        this.elevator = elevator;
        return this;
    }

    public int getFloor() {
        return floor;
    }

    public Housesen setFloor(int floor) {
        this.floor = floor;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Housesen setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getLayout() {
        return layout;
    }

    public Housesen setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Housesen setPrice(float price) {
        this.price = price;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public Housesen setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getRenovation() {
        return renovation;
    }

    public Housesen setRenovation(String renovation) {
        this.renovation = renovation;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Housesen setSize(int size) {
        this.size = size;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Housesen setYear(int year) {
        this.year = year;
        return this;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public Housesen setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }
}
