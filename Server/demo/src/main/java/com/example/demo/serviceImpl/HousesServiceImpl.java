package com.example.demo.serviceImpl;

import com.example.demo.entity.Houses;
import com.example.demo.mapper.HousesMapper;
import com.example.demo.service.IHousesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-04
 */
@Service
public class HousesServiceImpl extends ServiceImpl<HousesMapper, Houses> implements IHousesService {

    @Autowired
    private HousesMapper housesMapper;

    @Override
    public List<Houses> selectAll() {
        return housesMapper.selectAll();
    }

    @Override
    public List<Houses> select(Houses houses) {
        return housesMapper.select(houses);
    }



}
