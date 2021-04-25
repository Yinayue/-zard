package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.entity.Order;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {

    public List<Order> select(Order order);

}
