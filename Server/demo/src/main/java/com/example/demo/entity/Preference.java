package com.example.demo.entity;


import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2021-03-17
 */
public class Preference extends Model<Preference> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("buyer_id")
	private Integer buyerId;
	@TableField("house_id")
	private Integer houseId;
	private Integer score;


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

	public Integer getHouseId() {
		return houseId;
	}

	public Preference setHouseId(Integer houseId) {
		this.houseId = houseId;
		return this;
	}

	public Integer getScore() {
		return score;
	}

	public Preference setScore(Integer score) {
		this.score = score;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
