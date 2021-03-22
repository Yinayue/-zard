package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Houses;
import com.example.demo.entity.Users;
import com.example.demo.service.IUsersService;
import com.example.demo.util.JsonResult;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(String name, String password){
        List<Users> login = iUsersService.login(name,password);
        //获取到JSONObject
        JSONObject jsonParam = new JSONObject();

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        if(login.size()!=0){
            result.put("msg","success");
            result.put("method","post");
            result.put("data",login.get(0).getName());
            return JsonResult.success(login.get(0).getName());
        }else{
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

}
