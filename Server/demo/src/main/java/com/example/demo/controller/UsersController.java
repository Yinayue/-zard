package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.EnSjz;
import com.example.demo.entity.Houses;
import com.example.demo.entity.Preference;
import com.example.demo.entity.Users;
import com.example.demo.service.IEnSjzService;
import com.example.demo.service.IHousesService;
import com.example.demo.service.IPreferenceService;
import com.example.demo.service.IUsersService;
import com.example.demo.serviceImpl.TokenService;
import com.example.demo.util.basic.JsonResult;
import com.example.demo.util.basic.TokenUtil;
import com.example.demo.util.basic.UserLoginToken;
import com.example.demo.util.recommend.BuyerSet;
import com.example.demo.util.recommend.Start;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    @RequestMapping(value="/register", method= RequestMethod.POST)
	public String insertUser(Users user){
        try{
            user.setDeleteFlag(1);
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

//    @RequestMapping(value="/login",method = RequestMethod.POST)
//    public String login(String name, String password){
//        List<Users> login = iUsersService.login(name,password);
//        try {
//            if (login.size() > 0) {
//                return JsonResult.success(login.get(0).getName());
//            } else {
//                return JsonResult.error();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return JsonResult.error();
//        }
//    }

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

    @RequestMapping(value = "reco",method = RequestMethod.POST)
    public String recommend(String username){
        try{
            //获取到用户信息
            Users temp = new Users();
            temp.setName(username);
            List<Users> users = iUsersService.selectUsers(temp);
            if(users.size()==1){
                Users user = users.get(0);
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
                                Houses t = new Houses();
                                t.setId(preference.getHouseId());
                                List<Houses> houses = iHousesService.select(t);
                                buyerSet.getUser(users1.getName()).set(houses.get(0).getAddress(),preference.getScore());
                            }
                        }else{
                            continue;
                        }
                    }
                }
                Start start = new Start();
                List<String> recoResult = start.start2(buyerSet,username);
                List<Houses> result = new ArrayList<>();
                for(String s : recoResult){
                    Houses tempHouse = new Houses();
                    tempHouse.setAddress(s);
                    result.add(iHousesService.select(tempHouse).get(0));
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

                return jsonObject;

            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error();
        }

    }

    //@UserLoginToken
    @RequestMapping(value = "/getUserDetail" ,method = RequestMethod.GET)
    public String getMessage() {
        int uid = -1;
//        try{
//            uid = Integer.parseInt(TokenUtil.getTokenUserId());
//        }catch (Exception e){
//            e.printStackTrace();
//            return JsonResult.error("验证失败");
//        }

        //卖过的房子列表
        EnSjz enSjz = new EnSjz();
        enSjz.setSellerId(1);
        List<EnSjz> houses = iEnSjzService.select(enSjz);
        return JsonResult.success(houses.size());
    }


}
