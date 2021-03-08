package com.example.demo.mapper;

import com.example.demo.entity.Houses;
import com.example.demo.entity.Users;
import com.baomidou.mybatisplus.mapper.BaseMapper;

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
}