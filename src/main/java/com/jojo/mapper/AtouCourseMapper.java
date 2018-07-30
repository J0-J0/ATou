package com.jojo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jojo.model.AtouCourse;
import tk.mybatis.mapper.common.Mapper;

public interface AtouCourseMapper extends Mapper<AtouCourse> {

	List<AtouCourse> selectByUserId(@Param(value = "userId") Long userId);
}