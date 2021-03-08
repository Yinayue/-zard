package com.example.demo.controller;

import com.example.demo.entity.Houses;
import com.example.demo.service.IHousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-04
 */
@RestController
@RequestMapping("/houses")
public class HousesController {

    @Autowired
    IHousesService iHousesService;

    @RequestMapping(value="/selectAll", method= RequestMethod.GET)
    public List<Houses> select(){
        List<Houses> temp = iHousesService.selectAll();
        return iHousesService.selectAll();
    }
}
