package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.Order;

import java.util.List;

public interface IOrderService extends IService<Order> {

    public List<Order> select(Order order);

}
