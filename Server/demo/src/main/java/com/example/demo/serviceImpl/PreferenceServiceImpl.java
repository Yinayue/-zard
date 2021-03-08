package com.example.demo.serviceImpl;

import com.example.demo.entity.Preference;
import com.example.demo.mapper.PreferenceMapper;
import com.example.demo.service.IPreferenceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-04
 */
@Service
public class PreferenceServiceImpl extends ServiceImpl<PreferenceMapper, Preference> implements IPreferenceService {
	
}
