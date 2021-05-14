package com.example.demo.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.entity.Housesen;
import com.example.demo.entity.Pager;
import com.example.demo.entity.Range;
import com.example.demo.mapper.HousesEnMapper;
import com.example.demo.service.IHousesEnService;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class HousesEnServiceImpl extends ServiceImpl<HousesEnMapper, Housesen> implements IHousesEnService{

    @Autowired
    HousesEnMapper housesEnMapper;

    public List<Housesen> select(Housesen housesEn){
        return housesEnMapper.select(housesEn);
    }

    public List<Housesen> selectTest(Housesen housesen){
        List<Housesen> result = new ArrayList<>();
        housesEnMapper.selectTest(housesen, new ResultHandler<Housesen>() {
            @Override
            public void handleResult(ResultContext<? extends Housesen> resultContext) {
                Housesen row = resultContext.getResultObject();
                result.add(row);
            }
        });
        return result;
    }

    public List<Housesen> year(Range range){
        return housesEnMapper.year(range);
    }

    public List<Housesen> price(Range range){
        return housesEnMapper.price(range);
    }


    public Pager<Housesen> findByPager(int page, int size){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", (page-1)*size);
        params.put("size", size);
        Pager<Housesen> pager = new Pager<Housesen>();
        List<Housesen> list = housesEnMapper.findByPager(params);
        pager.setRows(list);
        pager.setTotal(housesEnMapper.count());
        return pager;
    }

    @Override
    public void insertNew(Housesen housesen) {
        housesEnMapper.insertNew(housesen);
    }
}
