package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.Housesen;
import com.example.demo.entity.Range;

import java.util.List;

public interface IHousesEnService extends IService<Housesen> {

    public List<Housesen> select(Housesen housesEn);

    public List<Housesen> year(Range range);

    public List<Housesen> price(Range range);

}