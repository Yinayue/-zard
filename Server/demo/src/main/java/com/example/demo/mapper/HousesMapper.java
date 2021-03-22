package com.example.demo.mapper;

import com.example.demo.entity.Houses;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-04
 */

public interface HousesMapper extends BaseMapper<Houses> {
    public List<Houses> selectAll();
    public List<Houses> select(Houses houses);
}