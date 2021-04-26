package com.example.demo.controller;

import com.example.demo.entity.EnSjz;
import com.example.demo.entity.Houses;
import com.example.demo.entity.Users;
import com.example.demo.service.IEnSjzService;
import com.example.demo.service.IHousesService;
import com.example.demo.service.IUsersService;
import com.example.demo.util.basic.JsonResult;
import com.example.demo.util.search.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    IEnSjzService iEnSjzService;

    @Autowired
    IUsersService iUsersService;

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
    public String insert(EnSjz houses){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
//            houses.setLaunchDate(formatter.format(date));
            houses.setDeleteFlag(0+"");

            //get seller
            Users temp = new Users();
            temp.setId((long)houses.getSellerId());
            Users seller = iUsersService.selectUsers(temp).get(0);
            //check score
            if(seller.getScore()>=5){//have enough score
                seller.setScore(seller.getScore()-5);
            }else{
                return JsonResult.error("积分不足");
            }
            iEnSjzService.insert(houses);
            iUsersService.updateById(seller);
            return JsonResult.success("success");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value = "select",method = RequestMethod.POST)
    public String select(EnSjz houses){
        try{
            return JsonResult.success(iEnSjzService.select(houses));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(EnSjz houses){
        try{
            return JsonResult.success(iEnSjzService.updateById(houses));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(String queryStr){
        try{
            Operation operation = new Operation();
            operation.buildIndex();
            String[] query=queryStr.split(",");
            List<String> addresses = operation.getAddresses(query);
            List<List<EnSjz>> result = new ArrayList<>();
            for(String address : addresses){
                EnSjz temp = new EnSjz();
                temp.setAddress(address);
                result.add(iEnSjzService.select(temp));
            }
            //在这里定义一下要search的query，数组形式
            return JsonResult.success(result);

        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }
}
