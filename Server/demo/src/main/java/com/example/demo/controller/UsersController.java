package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.service.IUsersService;
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
@RequestMapping("/users")
public class UsersController {

    @Autowired
    IUsersService iUsersService;

    @RequestMapping(value="/register", method= RequestMethod.POST)
	public boolean insertUser(Users user){
        user.setDeleteFlag(1);
        return iUsersService.insertUser(user);
    }

    @RequestMapping(value="/selectAll",method = RequestMethod.GET)
    public List<Users> selectAll(){
        return iUsersService.selectAll();
    }
}
