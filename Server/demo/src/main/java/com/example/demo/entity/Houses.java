package com.example.demo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-04
 */
public class Houses extends Model<Houses> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("seller_id")
	private Integer sellerId;
	@TableField("launch_date")
	private Date launchDate;
	private String address;
	private Double size;
	private BigDecimal price;
	@TableField("has_been_sold")
	private Integer hasBeenSold;
	@TableField("current_holder_id")
	private Integer currentHolderId;
	@TableField("second_hand")
	private Integer secondHand;
	@TableField("rooms_type")
	private String roomsType;
	private Integer floor;
	private Integer age;
	private Integer decoration;
	@TableField("house_type")
	private Integer houseType;
	private Integer property;
	private Integer face;
	private String remarks;
	@TableField("delete_flag")
	private Integer deleteFlag;


	public Integer getId() {
		return id;
	}

	public Houses setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public Houses setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
		return this;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public Houses setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Houses setAddress(String address) {
		this.address = address;
		return this;
	}

	public Double getSize() {
		return size;
	}

	public Houses setSize(Double size) {
		this.size = size;
		return this;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Houses setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}

	public Integer getHasBeenSold() {
		return hasBeenSold;
	}

	public Houses setHasBeenSold(Integer hasBeenSold) {
		this.hasBeenSold = hasBeenSold;
		return this;
	}

	public Integer getCurrentHolderId() {
		return currentHolderId;
	}

	public Houses setCurrentHolderId(Integer currentHolderId) {
		this.currentHolderId = currentHolderId;
		return this;
	}

	public Integer getSecondHand() {
		return secondHand;
	}

	public Houses setSecondHand(Integer secondHand) {
		this.secondHand = secondHand;
		return this;
	}

	public String getRoomsType() {
		return roomsType;
	}

	public Houses setRoomsType(String roomsType) {
		this.roomsType = roomsType;
		return this;
	}

	public Integer getFloor() {
		return floor;
	}

	public Houses setFloor(Integer floor) {
		this.floor = floor;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public Houses setAge(Integer age) {
		this.age = age;
		return this;
	}

	public Integer getDecoration() {
		return decoration;
	}

	public Houses setDecoration(Integer decoration) {
		this.decoration = decoration;
		return this;
	}

	public Integer getHouseType() {
		return houseType;
	}

	public Houses setHouseType(Integer houseType) {
		this.houseType = houseType;
		return this;
	}

	public Integer getProperty() {
		return property;
	}

	public Houses setProperty(Integer property) {
		this.property = property;
		return this;
	}

	public Integer getFace() {
		return face;
	}

	public Houses setFace(Integer face) {
		this.face = face;
		return this;
	}

	public String getRemarks() {
		return remarks;
	}

	public Houses setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public Houses setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
