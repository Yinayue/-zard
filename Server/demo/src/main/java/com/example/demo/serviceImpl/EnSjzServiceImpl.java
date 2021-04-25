package com.example.demo.serviceImpl;

import com.example.demo.entity.EnSjz;
import com.example.demo.mapper.EnSjzMapper;
import com.example.demo.service.IEnSjzService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-04-25
 */
@Service
public class EnSjzServiceImpl extends ServiceImpl<EnSjzMapper, EnSjz> implements IEnSjzService {

    @Autowired
    EnSjzMapper enSjzMapper;

    @Override
    public List<EnSjz> select(EnSjz enSjz) {
        return enSjzMapper.select(enSjz);
    }
}
