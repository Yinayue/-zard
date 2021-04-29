package com.example.demo.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.entity.Housesen;
import com.example.demo.entity.Range;
import com.example.demo.mapper.HousesEnMapper;
import com.example.demo.service.IHousesEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HousesEnServiceImpl extends ServiceImpl<HousesEnMapper, Housesen> implements IHousesEnService{

    @Autowired
    HousesEnMapper housesEnMapper;

    public List<Housesen> select(Housesen housesEn){
        return housesEnMapper.select(housesEn);
    }

    public List<Housesen> year(Range range){
        return housesEnMapper.year(range);
    }

    public List<Housesen> price(Range range){
        return housesEnMapper.price(range);
    }

}
