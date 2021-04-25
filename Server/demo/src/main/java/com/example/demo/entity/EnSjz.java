package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-04-25
 */
@TableName("en_sjz")
public class EnSjz extends Model<EnSjz> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	@TableField("seller_id")
	private Integer sellerId;
	@TableField("launch_date")
	private Date launchDate;
	private String remark;
	private String address;
	private String floor;
	private String year;
	private String style;
	private Double size;
	private String face;
	private BigDecimal price;
	@TableField("has_been_sold")
	private String hasBeenSold;
	@TableField("delete_flag")
	private String deleteFlag;


	public Integer getId() {
		return id;
	}

	public EnSjz setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public EnSjz setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
		return this;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public EnSjz setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public EnSjz setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public EnSjz setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getFloor() {
		return floor;
	}

	public EnSjz setFloor(String floor) {
		this.floor = floor;
		return this;
	}

	public String getYear() {
		return year;
	}

	public EnSjz setYear(String year) {
		this.year = year;
		return this;
	}

	public String getStyle() {
		return style;
	}

	public EnSjz setStyle(String style) {
		this.style = style;
		return this;
	}

	public Double getSize() {
		return size;
	}

	public EnSjz setSize(Double size) {
		this.size = size;
		return this;
	}

	public String getFace() {
		return face;
	}

	public EnSjz setFace(String face) {
		this.face = face;
		return this;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public EnSjz setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}

	public String getHasBeenSold() {
		return hasBeenSold;
	}

	public EnSjz setHasBeenSold(String hasBeenSold) {
		this.hasBeenSold = hasBeenSold;
		return this;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public EnSjz setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
