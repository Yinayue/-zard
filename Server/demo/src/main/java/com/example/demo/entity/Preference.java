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
	private Long id;
	@TableField("buyer_id")
	private Long buyerId;
	@TableField("house_id")
	private Long houseId;
	private Integer score;
	private String review;

	public Long getId() {
		return id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Preference setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public Preference setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
		return this;
	}

	public Long getHouseId() {
		return houseId;
	}

	public Preference setHouseId(Long houseId) {
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
