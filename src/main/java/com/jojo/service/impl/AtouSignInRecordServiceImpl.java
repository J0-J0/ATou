package com.jojo.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jojo.mapper.AtouSignInRecordMapper;
import com.jojo.model.AtouSignInRecord;
import com.jojo.service.AtouSignInRecordService;

@Service
@Transactional
public class AtouSignInRecordServiceImpl extends BaseServiceImpl<AtouSignInRecord> implements AtouSignInRecordService {

	@Autowired
	private AtouSignInRecordMapper atouSignInRecordMapper;

	@Override
	public AtouSignInRecord selectNowDayRecordByUser(long userId) {
		return atouSignInRecordMapper.selectNowDayRecordByUser(userId);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(StringUtils.containsAny("wewfeg", ""));
	}

}
