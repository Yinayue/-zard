package com.example.demo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
public class Preference extends Model<Preference> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("buyer_id")
	private Integer buyerId;
	private BigDecimal price;
	private String address;
	private Double size;
	@TableField("delete_flag")
	private Integer deleteFlag;


	public Integer getId() {
		return id;
	}

	public Preference setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public Preference setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
		return this;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Preference setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Preference setAddress(String address) {
		this.address = address;
		return this;
	}

	public Double getSize() {
		return size;
	}

	public Preference setSize(Double size) {
		this.size = size;
		return this;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public Preference setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
