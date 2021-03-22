package com.example.demo.service;

import com.example.demo.entity.Users;
import com.baomidou.mybatisplus.service.IService;
import com.example.demo.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-04
 */
public interface IUsersService extends IService<Users> {
	public boolean insertUser(Users user);
	public List<Users> selectAll();
	public List<Users> selectUsers(Users users);
	public List<Users> login(String name, String password);
}
