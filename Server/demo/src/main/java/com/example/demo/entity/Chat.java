package com.example.demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-30
 */
public class Chat extends Model<Chat> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	@TableField("user_id")
	private Integer userId;
	@TableField("house_id")
	private Integer houseId;
	@TableField("delete_flag")
	private Integer deleteFlag;


	public Integer getId() {
		return id;
	}

	public Chat setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public Chat setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public Chat setHouseId(Integer houseId) {
		this.houseId = houseId;
		return this;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public Chat setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
