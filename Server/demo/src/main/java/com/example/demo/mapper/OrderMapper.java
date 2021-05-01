package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.entity.Orders;

import java.util.List;

public interface OrderMapper extends BaseMapper<Orders> {

    public List<Orders> select(Orders order);

}
