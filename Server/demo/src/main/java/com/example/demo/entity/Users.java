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
 * @since 2021-03-04
 */
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String name;
	@TableField("phone_number")
	private String phoneNumber;
	private String email;
	private Integer status;
	private String password;
	@TableField("delete_flag")
	private Integer deleteFlag;


	public Integer getId() {
		return id;
	}

	public Users setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Users setName(String name) {
		this.name = name;
		return this;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Users setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Users setEmail(String email) {
		this.email = email;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public Users setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Users setPassword(String password) {
		this.password = password;
		return this;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public Users setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
