package com.example.demo.service;

import com.example.demo.entity.Preference;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-17
 */
public interface IPreferenceService extends IService<Preference> {
	public List<Preference> select(Preference preference);
}
