package com.example.demo.mapper;

import com.example.demo.entity.Preference;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-17
 */

public interface PreferenceMapper extends BaseMapper<Preference> {
    public List<Preference> select(Preference preference);
}