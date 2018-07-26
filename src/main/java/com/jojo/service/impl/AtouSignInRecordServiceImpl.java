package com.jojo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jojo.mapper.AtouSignInRecordMapper;
import com.jojo.mapper.AtouStatisticsMapper;
import com.jojo.mapper.AtouUserMapper;
import com.jojo.model.AtouSignInRecord;
import com.jojo.pojo.Response;
import com.jojo.service.AtouSignInRecordService;

@Service
@Transactional
public class AtouSignInRecordServiceImpl extends BaseServiceImpl<AtouSignInRecord> implements AtouSignInRecordService {

	@Autowired
	private AtouSignInRecordMapper atouSignInRecordMapper;

	@Autowired
	private AtouStatisticsMapper atouStatisticsMapper;

	@Autowired
	private AtouUserMapper atouUserMapper;

	@Override
	public Response startSigningIn(long userId, long courseId) {
		Response response = new Response();
		AtouSignInRecord record = atouSignInRecordMapper.selectNowDayRecordByUser(userId, courseId);

		if (record != null) {
			response.setFailMessage("已存在签到记录");
			return response;
		}

		record = new AtouSignInRecord();
		Date date = new Date();
		record.setGmtCreate(date);
		record.setUserId(userId);

		int row = insertSelective(record);
		if (row < 1) {
			response.setFailMessage("开始签到失败，请稍后再试");
			return response;
		}
		// 数据库很可靠的，不会出错的，不要检查了
		atouStatisticsMapper.addOnePoint(userId, courseId);
		atouUserMapper.addOnePoint(userId);

		response.setSuccessMessage("签到成功");
		return response;
	}

	@Override
	public Response endSigningIn(long userId, long courseId) {
		Response response = new Response();
		AtouSignInRecord record = atouSignInRecordMapper.selectNowDayRecordByUser(userId, courseId);

		if (record == null) {
			response.setFailMessage("没有找到当天的打卡记录");
			return response;
		}

		Date endDate = record.getGmtEnd();
		if (endDate != null) {
			response.setFailMessage("已经结束签到过了");
			return response;
		}

		endDate = new Date();
		record.setGmtEnd(endDate);
		int row = updateByPrimaryKeySelective(record);
		if (row < 1) {
			response.setFailMessage("更新失败，请稍后再试");
			return response;
		}
		response.setSuccessMessage("更新成功");
		Date startDate = record.getGmtCreate();
		long practiceTime = startDate.getTime() - endDate.getTime();
		atouUserMapper.addPracticeTime(practiceTime, userId, courseId);
		atouStatisticsMapper.addPracticeTime(practiceTime, userId, courseId);

		return response;
	}

}
