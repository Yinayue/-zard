package com.example.demo.mapper;

import com.example.demo.entity.Houses;
import com.example.demo.entity.Users;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-04
 */

public interface UsersMapper extends BaseMapper<Users> {
    public List<Users> selectAll();
    public List<Users> selectUsers(Users users);
    public List<Users> login(@Param("name") String name, @Param("password") String password);
}