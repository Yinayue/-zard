package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.service.*;
import com.example.demo.util.predict.PMMLDemo;
import com.example.demo.util.basic.JsonResult;
import com.example.demo.util.search.Operation;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    IHousesEnService iHousesEnService;

    @Autowired
    IMarkService iMarkService;

    @Autowired
    IOrderService iOrderService;

    @RequestMapping(value="/selectAll", method= RequestMethod.GET)
    public String select(){
        try {
            return JsonResult.success(iHousesService.selectAll());
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }

    }


    /**
     * 房价预测
     *
     *Region, Layout, Floor, Elevator, Renovation, Year, Size
     *
     */
    @RequestMapping(value = "/predict",method = RequestMethod.POST)
    public String predict(Housesen houses){
        try{
            String region = houses.getRegion();
            int layout = houses.getLayout();
            int floor = houses.getFloor();
            String elevator = houses.getElevator();
            String renovation = houses.getRenovation();
            int year = houses.getYear();
            if(year>2021){
                return JsonResult.error("错误数据");
            }
            int size = houses.getSize();



            HashMap<String, Integer> regionMap = new HashMap<>();
            regionMap.put("Dongcheng",1);
            regionMap.put("Xicheng",2);
            regionMap.put("Chaoyang",3);
            regionMap.put("Haidian",4);
            regionMap.put("Fengtai",5);
            regionMap.put("Shijingshan",6);
            regionMap.put("Tongzhou",7);
            regionMap.put("Changping",8);
            regionMap.put("Daxing",9);
            regionMap.put("Yizhuang",10);
            regionMap.put("Shunyi",11);
            regionMap.put("Fangshan",12);
            regionMap.put("Mentougou",13);
            regionMap.put("Pinggu",14);
            regionMap.put("Huairou",15);
            regionMap.put("Miyun",16);
            regionMap.put("Yanqing",17);
            regionMap.put("Yanjiao",18);
            regionMap.put("Xianghe",19);


            HashMap<String, Integer> renovationMap = new HashMap<>();
            regionMap.put("Simple",0);
            regionMap.put("High-Grade",1);
            regionMap.put("Medium",2);


            HashMap<String, Integer> elevatorMap = new HashMap<>();
            regionMap.put("Yes",1);
            regionMap.put("No",0);

            try {


                int regionNum = regionMap.get(region);
                int renovationNum = renovationMap.get(renovation);
                int elevatorNum = elevatorMap.get(elevator);


                Map<String, Integer> predictMap = new HashMap<>();
                predictMap.put("x1", regionNum);
                predictMap.put("x2", layout);
                predictMap.put("x3", floor);
                predictMap.put("x4", elevatorNum);
                predictMap.put("x5", renovationNum);
                predictMap.put("x6", year);
                predictMap.put("x7", size);

                String path = System.getProperty("user.dir") + "\\src\\main\\resources\\Tpot_Model.pmml";

                Double result = PMMLDemo.predict(predictMap, path);
                return JsonResult.success(result);
            }catch (Exception e){
                e.printStackTrace();
                return JsonResult.error("错误数据");
            }

        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("操作失败");
        }
    }



    /**
     * 上传房子
     * @param houses
     * @return
     */
    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public String insert(Housesen houses){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
//            houses.setLaunchDate(formatter.format(date));
            houses.setDeleteFlag(0);

            //get seller
            Users temp = new Users();
            temp.setId((long)houses.getSid());
            Users seller = iUsersService.selectUsers(temp).get(0);
            //check score
            if(seller.getScore()>=5){//have enough score
                seller.setScore(seller.getScore()-5);
            }else{
                return JsonResult.error("积分不足");
            }
            iHousesEnService.insert(houses);
            iUsersService.updateById(seller);
            return JsonResult.success("success");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value = "select",method = RequestMethod.POST)
    public String select(Housesen houses){
        try{
            return JsonResult.success(iHousesEnService.select(houses));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(Housesen houses){
        try{
            return JsonResult.success(iHousesEnService.updateById(houses));
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
            List<List<Housesen>> result = new ArrayList<>();
            for(String address : addresses){
                Housesen temp = new Housesen();
                temp.setAddress(address);
                result.add(iHousesEnService.select(temp));
            }
            //在这里定义一下要search的query，数组形式
            return JsonResult.success(result);

        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value = "mark", method = RequestMethod.POST)
    public String mark(Mark mark){
        try {
            return JsonResult.success(iMarkService.insert(mark));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value = "order",method = RequestMethod.POST)
    public String order(Orders orders){
        try {
            return JsonResult.success(iOrderService.insert(orders));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value = "year",method = RequestMethod.POST)
    public String year(Range range){
        try{
            return JsonResult.success(iHousesEnService.year(range));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value = "price",method = RequestMethod.POST)
    public String price(Range range){
        try {
            return JsonResult.success(iHousesEnService.price(range));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }

    }
}
