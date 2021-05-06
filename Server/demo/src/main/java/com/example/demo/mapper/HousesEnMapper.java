package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.entity.Housesen;
import com.example.demo.entity.Range;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;
import java.util.Map;

public interface HousesEnMapper extends BaseMapper<Housesen> {

    public List<Housesen> select(Housesen housesEn);

    public void selectTest(Housesen housesen, ResultHandler<Housesen> handler);

    public List<Housesen> year(Range range);

    public List<Housesen> price(Range range);



    public List<Housesen> findByPager(Map<String, Object> params);
    public long count();

}
