package com.example.demo.controller;

import com.example.demo.entity.Houses;
import com.example.demo.service.IHousesService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String select(){
        try {
            return JsonResult.success(iHousesService.selectAll());
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }

    }

    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public String insert(Houses houses){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            houses.setLaunchDate(formatter.format(date));
            houses.setDeleteFlag(0);
            iHousesService.insert(houses);
            return JsonResult.success("success");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value = "select",method = RequestMethod.POST)
    public String select(Houses houses){
        try{
            return JsonResult.success(iHousesService.select(houses));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(Houses houses){
        try{
            return JsonResult.success(iHousesService.updateById(houses));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }
}
