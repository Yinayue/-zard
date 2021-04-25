package com.example.demo.service;

import com.example.demo.entity.EnSjz;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-04-25
 */
public interface IEnSjzService extends IService<EnSjz> {
	public List<EnSjz> select(EnSjz enSjz);
}
