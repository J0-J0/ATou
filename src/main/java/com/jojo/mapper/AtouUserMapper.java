package com.jojo.mapper;

import org.apache.ibatis.annotations.Param;

import com.jojo.model.AtouUser;

import tk.mybatis.mapper.common.Mapper;

public interface AtouUserMapper extends Mapper<AtouUser> {

	int addOnePoint(@Param(value = "userId") long userId);

	int addPracticeTime(@Param(value = "practiceTime") long practiceTime, @Param(value = "userId") long userId,
			@Param(value = "courseId") long courseId);
}