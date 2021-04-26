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

	private Long id;
	@TableField("user_id")
	private Long userId;
	@TableField("house_id")
	private Long houseId;
	@TableField("delete_flag")
	private Integer deleteFlag;


	public Long getId() {
		return id;
	}

	public Chat setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public Chat setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public Long getHouseId() {
		return houseId;
	}

	public Chat setHouseId(Long houseId) {
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
