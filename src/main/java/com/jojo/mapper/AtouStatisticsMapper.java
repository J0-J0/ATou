package com.jojo.mapper;

import org.apache.ibatis.annotations.Param;

import com.jojo.model.AtouStatistics;
import tk.mybatis.mapper.common.Mapper;

public interface AtouStatisticsMapper extends Mapper<AtouStatistics> {

	int addOnePoint(@Param(value = "userId") long userId, @Param(value = "courseId") long courseId);

	int addPracticeTime(@Param(value = "practiceTime") long practiceTime, @Param(value = "userId") long userId,
			@Param(value = "courseId") long courseId);
}