package com.example.demo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.sql.Date;

@TableName("Housesen")
public class Housesen extends Model<Housesen> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    @TableId(value="id", type= IdType.AUTO)
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
    @TableField("launch_time")
    private String launchTime;
    private String information;
    private String path;

    @TableField(exist = false)
    private int startYear;
    @TableField(exist = false)
    private int endYear;
    @TableField(exist = false)
    private int startPrice;
    @TableField(exist = false)
    private int endPrice;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getEndPrice() {
        return endPrice;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndPrice(int endPrice) {
        this.endPrice = endPrice;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public int getStartYear() {
        return startYear;
    }

    public String getLaunchTime() {
        return launchTime;
    }

    public Housesen setInformation(String information) {
        this.information = information;
        return this;
    }

    public String getInformation() {
        return information;
    }

    public Housesen setLaunchTime(String launchTime) {
        this.launchTime = launchTime;
        return this;
    }

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
