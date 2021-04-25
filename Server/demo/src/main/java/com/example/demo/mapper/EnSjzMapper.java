package com.example.demo.mapper;

import com.example.demo.entity.EnSjz;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-04-25
 */
public interface EnSjzMapper extends BaseMapper<EnSjz> {
    public List<EnSjz> select(EnSjz enSjz);
}