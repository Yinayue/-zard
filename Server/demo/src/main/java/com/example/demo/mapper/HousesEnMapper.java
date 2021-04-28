package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.entity.Housesen;

import java.util.List;

public interface HousesEnMapper extends BaseMapper<Housesen> {

    public List<Housesen> select(Housesen housesEn);

}
