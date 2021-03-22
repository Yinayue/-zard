package com.example.demo.service;

import com.example.demo.entity.Houses;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-04
 */
public interface IHousesService extends IService<Houses> {
	public List<Houses> selectAll();
	public List<Houses> select(Houses houses);
}
