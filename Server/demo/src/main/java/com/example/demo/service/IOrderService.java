package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.Orders;

import java.util.List;

public interface IOrderService extends IService<Orders> {

    public List<Orders> select(Orders order);

}
