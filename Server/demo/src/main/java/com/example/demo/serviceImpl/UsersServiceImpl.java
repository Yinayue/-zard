package com.example.demo.serviceImpl;

import com.example.demo.entity.Users;
import com.example.demo.mapper.UsersMapper;
import com.example.demo.service.IUsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-04
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public boolean insertUser(Users user) {
        try {
            usersMapper.insert(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Users> selectAll() {
        return usersMapper.selectAll();
    }

    @Override
    public List<Users> selectUsers(Users users) {
        return usersMapper.selectUsers(users);
    }

    @Override
    public List<Users> login(String name, String password) {
        return usersMapper.login(name,password);
    }


}
