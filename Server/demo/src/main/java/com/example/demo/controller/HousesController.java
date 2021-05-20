package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.service.*;
import com.example.demo.util.basic.FileUtil;
import com.example.demo.util.basic.TokenUtil;
import com.example.demo.util.basic.UserLoginToken;
import com.example.demo.util.predict.PMMLDemo;
import com.example.demo.util.basic.JsonResult;
import com.example.demo.util.search.Operation;
import com.google.gson.JsonArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
            Housesen housesen = new Housesen();
            return JsonResult.success(iHousesEnService.selectTest(housesen));
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
            renovationMap.put("Simple",0);
            renovationMap.put("High-Grade",1);
            renovationMap.put("Medium",2);


            HashMap<String, Integer> elevatorMap = new HashMap<>();
            elevatorMap.put("Yes",1);
            elevatorMap.put("No",0);

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
    public String insert(Housesen houses,@RequestParam("image") MultipartFile image){
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
            if(seller.getScore()>=20){//have enough score
                seller.setScore(seller.getScore()-20);
            }else{
                return JsonResult.error("积分不足");
            }
            String fileName = image.getOriginalFilename();
            //设置文件上传路径
            String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\image\\";
            houses.setPath(filePath+fileName);
            uploadImg(image);
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
            List<Housesen> temp = iHousesEnService.select(houses);
            return JsonResult.success(iHousesEnService.select(houses));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }


    /**
     * 更新房子信息
     * @return
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(Housesen houses){
        try{
            return JsonResult.success(iHousesEnService.updateById(houses));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }


    /**
     * 搜索引擎
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(String queryStr){
        int uid = -1;
        try{
            uid = Integer.parseInt(TokenUtil.getTokenUserId());
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("验证失败");
        }

        try{
            //get seller
            Users t = new Users();
            t.setId((long)uid);
            if (iUsersService.selectUsers(t).size()>0) {
                Users seller = iUsersService.selectUsers(t).get(0);
                //check score
                if (seller.getScore() >= 10) {//have enough score
                    seller.setScore(seller.getScore() - 10);
                } else {
                    return JsonResult.error("积分不足");
                }


                Operation operation = new Operation();
                operation.buildIndex();
                String[] query = queryStr.split(",");
                List<String> addresses = operation.getAddresses(query);
                List<List<Housesen>> result = new ArrayList<>();
                for (String address : addresses) {
                    Housesen temp = new Housesen();
                    temp.setAddress(address);
                    result.add(iHousesEnService.select(temp));
                }
                //在这里定义一下要search的query，数组形式
                return JsonResult.success(result);
            }else {
                return JsonResult.error("未查询到用户");
            }

        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    /**
     * 新增收藏
     * @return
     */
    @RequestMapping(value = "mark", method = RequestMethod.POST)
    public String mark(Mark mark){
        try {
            //查重
            if(iMarkService.select(mark).size()>0){
                return JsonResult.error("已存在");
            }
            return JsonResult.success(iMarkService.insert(mark));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    /**
     * 新增购买
     * @return
     */
    @RequestMapping(value = "order",method = RequestMethod.POST)
    public String order(Orders orders){
        try {
            if(iOrderService.select(orders).size()>0){
                return JsonResult.error("已存在");
            }
            return JsonResult.success(iOrderService.insert(orders));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    /**
     * 弃用
     * @param range
     * @return
     */
    @RequestMapping(value = "year",method = RequestMethod.POST)
    public String year(Range range){
        try{
            return JsonResult.success(iHousesEnService.year(range));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    /**
     * 弃用
     * @param range
     * @return
     */
    @RequestMapping(value = "price",method = RequestMethod.POST)
    public String price(Range range){
        try {
            return JsonResult.success(iHousesEnService.price(range));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }

    }

    /**
     * 取消收藏
     */
    @RequestMapping(value = "deleteMark",method = RequestMethod.POST)
    public String deleteMark(long userId,long houseId){
        try{
            Mark mark = new Mark();
            mark.setUid(userId);
            mark.setHid(houseId);
            mark = iMarkService.select(mark).get(0);
            return JsonResult.success(iMarkService.deleteById(mark));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }


    @RequestMapping(value = "test",method = RequestMethod.POST)
    public String test(Housesen housesen){
        return JsonResult.success(iHousesEnService.findByPager(1,1000));
    }


    @RequestMapping(value="/testuploadimg", method = RequestMethod.POST)
    public @ResponseBody String uploadImg(@RequestParam("file") MultipartFile file)  {
        String fileName = file.getOriginalFilename();
        //设置文件上传路径
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\image\\";
        //String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            return "上传成功";
        } catch (Exception e) {
            return "上传失败";
        }
    }



    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(long houseId){
        try{
            Housesen housesen = new Housesen();
            housesen.setId(houseId);
            return JsonResult.success(iHousesEnService.deleteById(housesen));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("操作失败");
        }
    }
}
