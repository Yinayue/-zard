package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.*;
import com.example.demo.model.User;
import com.example.demo.service.*;
import com.example.demo.serviceImpl.TokenService;
import com.example.demo.util.basic.JsonResult;
import com.example.demo.util.basic.TokenUtil;
import com.example.demo.util.basic.UserLoginToken;
import com.example.demo.util.recommend.BuyerSet;
import com.example.demo.util.recommend.Start;
import org.python.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-04
 */
@RestController
@ResponseBody
@RequestMapping("/users")
public class UsersController {

    @Autowired
    IUsersService iUsersService;

    @Autowired
    IPreferenceService iPreferenceService;

    @Autowired
    IHousesService iHousesService;

    @Autowired
    TokenService tokenService;

    @Autowired
    IEnSjzService iEnSjzService;

    @Autowired
    IMarkService iMarkService;

    @Autowired
    IOrderService iOrderService;

    @Autowired
    IHousesEnService iHousesEnService;

    @Autowired
    IChatMessageService iChatMessageService;

    @Autowired
    IFriendsService iFriendsService;

    @RequestMapping(value="/register", method= RequestMethod.POST)
	public String insertUser(Users user){
        try{
            user.setDeleteFlag(1);
            user.setLogin(0);
            return JsonResult.success(iUsersService.insert(user));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value="/selectAll",method = RequestMethod.GET)
    public String selectAll(){
        try{
            return JsonResult.success(iUsersService.selectAll());
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value="/selectUsers",method = RequestMethod.POST)
    public String selectUsers(Users users){
        try{
            return JsonResult.success(iUsersService.selectUsers(users));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }


    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(Users users){
        try{
            return JsonResult.success(iUsersService.updateById(users));
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @RequestMapping(value = "score",method = RequestMethod.POST)
    public String score(String username,int score){
        try{
            Users temp = new Users();
            temp.setName(username);
            List<Users> users = iUsersService.selectUsers(temp);
            if(users.size()==1){
                Users user = users.get(0);
                user.setScore(user.getScore()+score);
                return JsonResult.success(iUsersService.updateById(user));
            }else {
                return JsonResult.error("未查询到用户");
            }

        }catch (Exception e ){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

    @UserLoginToken
    @RequestMapping(value = "reco",method = RequestMethod.POST)
    public String recommend(){

        int uid = -1;
        try{
            uid = Integer.parseInt(TokenUtil.getTokenUserId());
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("验证失败");
        }


        try{
            //获取到用户信息
            Users temp = new Users();
            temp.setId((long)uid);
            List<Users> users = iUsersService.selectUsers(temp);
            if(users.size()==1){
                Users user = users.get(0);
                String username = user.getName();
                List<Users> allUser = iUsersService.selectUsers(new Users());
                BuyerSet buyerSet = new BuyerSet();
                //获取每个user的评分
                for(Users users1 : allUser){
                    if(users1.getStatus()==1){
                        Preference preferenceTemp = new Preference();
                        preferenceTemp.setBuyerId(users1.getId());
                        List<Preference> preferenceList = iPreferenceService.select(preferenceTemp);
                        //进行过评分
                        if(preferenceList.size()>0){
                            buyerSet.put(users1.getName()).create();
                            for(Preference preference: preferenceList){
                                Housesen t = new Housesen();
                                t.setId((long)preference.getHouseId());
                                List<Housesen> houses = iHousesEnService.select(t);
                                buyerSet.getBuyer(users1.getName()).set(houses.get(0).getAddress(),preference.getScore());
                            }
                        }else{
                            continue;
                        }
                    }
                }
                Start start = new Start();
                List<String> recoResult = start.start2(buyerSet,username);
                List<Housesen> result = new ArrayList<>();
                for(String s : recoResult){
                    Housesen tempHouse = new Housesen();
                    tempHouse.setAddress(s);
                    result.add(iHousesEnService.select(tempHouse).get(0));
                }
                return JsonResult.success(result);
                //return JsonResult.success(buyerSet.getUser("b").list);

            }else {
                return JsonResult.error("未查询到用户");
            }

        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }


    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public Object login(Users user, HttpServletResponse response) {
        try{
            JSONObject jsonObject = new JSONObject();
            //获取uid
            Users idUser = new Users();
            idUser.setName(user.getName());

            //验证用
            Users userForBase = new Users();
            Users temp = iUsersService.selectUsers(idUser).get(0);
            userForBase.setId(temp.getId());
            userForBase.setName(temp.getName());
            userForBase.setPassword(temp.getPassword());
            if (!userForBase.getPassword().equals(user.getPassword())) {
                return JsonResult.error("登陆失败,用户名或密码错误");
                //jsonObject.put("message", "登录失败,密码错误");
                //return jsonObject;
            } else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("userId",temp.getId());
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                response.addCookie(cookie);
                if(temp.getLogin()==0){
                    jsonObject.put("status",0);
                    temp.setLogin(1);
                    iUsersService.updateById(temp);
                }
                return jsonObject;

            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }

    }

    @UserLoginToken
    @RequestMapping(value = "/sellDetail" ,method = RequestMethod.GET)
    public String getSell() {
        int uid = -1;
        try{
            uid = Integer.parseInt(TokenUtil.getTokenUserId());
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("验证失败");
        }
        Map<String,Object> data = new HashMap<>();
        //卖过的房子列表
        Housesen enSjz = new Housesen();
        enSjz.setSid(uid);
        List<Housesen> houses = iHousesEnService.select(enSjz);
        //data.put("sell",houses);



        return JsonResult.success(houses);
    }

    @UserLoginToken
    @RequestMapping(value = "/markDetail", method = RequestMethod.GET)
    public String getMark(){
        int uid = -1;
        try{
            uid = Integer.parseInt(TokenUtil.getTokenUserId());
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("验证失败");
        }
        Map<String,Object> data = new HashMap<>();
        //收藏列表
        Mark mark = new Mark();
        mark.setUid((long)uid);
        List<Mark> marks = iMarkService.select(mark);
        data.put("mark",marks);
        //获取房子id
        List<Long> hid = new ArrayList<>();
        for(Mark m : marks){
            hid.add(m.getHid());
        }
        //获取房子信息
        List<Housesen> houseList = new ArrayList<>();
        for(long i: hid){
            Housesen temp = new Housesen();
            temp.setId(i);
            houseList.add(iHousesEnService.select(temp).get(0));
        }
        return JsonResult.success(houseList);
    }

    //@UserLoginToken
    @RequestMapping(value = "/userCount",method = RequestMethod.GET)
    public String getUserCount(){
        //管理员认证
//        int uid = -1;
//        try{
//            uid = Integer.parseInt(TokenUtil.getTokenUserId());
//            Users temp = new Users();
//            temp.setId((long)uid);
//            temp = iUsersService.selectUsers(temp).get(0);
//            if(temp.getStatus()!=2){
//                return JsonResult.error("非管理员");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return JsonResult.error("验证失败");
//        }



        try {
            Users user = new Users();
            user.setDeleteFlag(0);
            List<Users> users = iUsersService.selectUsers(user);
            return JsonResult.success(users.size());
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

   // @UserLoginToken
    @RequestMapping(value = "/houseCount",method = RequestMethod.GET)
    public String getHouseCount(){
//        int uid = -1;
//        try{
//            uid = Integer.parseInt(TokenUtil.getTokenUserId());
//            Users temp = new Users();
//            temp.setId((long)uid);
//            temp = iUsersService.selectUsers(temp).get(0);
//            if(temp.getStatus()!=2){
//                return JsonResult.error("非管理员");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return JsonResult.error("验证失败");
//        }

        try{
            Housesen house = new Housesen();
            house.setDeleteFlag(0);
            List<Housesen> list = iHousesEnService.select(house);
            return JsonResult.success(list.size());
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }



    @RequestMapping(value = "chatMessage",method = RequestMethod.POST)
    public String chatMessage(Users user){
        return JsonResult.success(iChatMessageService.selectByUser(user));
    }

    @RequestMapping(value = "addFriend",method = RequestMethod.POST)
    public String addFriend(String user1, String user2){
        try {
            //查询用户是否存在
            Users query1 = new Users();
            Users query2 = new Users();
            query1.setName(user1);
            query2.setName(user2);
            if (iUsersService.selectUsers(query1).size() == 0 || iUsersService.selectUsers(query2).size() == 0) {
                return JsonResult.error("用户不存在");
            } else {
                Friends friends = new Friends();
                friends.setUser1(user1);
                friends.setUser2(user2);
                return JsonResult.success(iFriendsService.insert(friends));
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }
    }

}
