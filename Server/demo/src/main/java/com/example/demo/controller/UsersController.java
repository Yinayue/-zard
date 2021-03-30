package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.service.IUsersService;
import com.example.demo.util.basic.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            if (login.size() > 0) {
                return JsonResult.success(login.get(0).getName());
            } else {
                return JsonResult.error();
            }
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

}
