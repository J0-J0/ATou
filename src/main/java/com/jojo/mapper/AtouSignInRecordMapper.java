package com.jojo.mapper;

import org.apache.ibatis.annotations.Param;

import com.jojo.model.AtouSignInRecord;

import tk.mybatis.mapper.common.Mapper;

public interface AtouSignInRecordMapper extends Mapper<AtouSignInRecord> {

	AtouSignInRecord selectNowDayRecordByUser(@Param("userId") long userId);
}