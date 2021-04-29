package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.entity.Housesen;
import com.example.demo.entity.Range;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HousesEnMapper extends BaseMapper<Housesen> {

    public List<Housesen> select(Housesen housesEn);

    public List<Housesen> year(Range range);

    public List<Housesen> price(Range range);

}
