package com.jojo.controller.api;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.model.AtouSignInRecord;
import com.jojo.pojo.Response;
import com.jojo.service.AtouSignInRecordService;
import com.jojo.util.SnowFlakerUtil;

/**
 * 签到接口<br>
 * 老子就喜欢把业务写在controller里，怎的
 * 
 * @author jgy
 *
 */
@Controller
@RequestMapping("/api/atouSignInRecord")
public class AtouSignInRecordApiController {

	@Autowired
	private AtouSignInRecordService atouSignInRecordService;

	@RequestMapping("/signIn")
	@ResponseBody
	public Response signIn(@RequestParam long userId, @RequestParam String opType) {
		Response response = new Response();
		response.setSuccessMessage("");

		boolean startFlag = StringUtils.equals(opType, AtouSignInRecord.START);
		boolean endFlag = StringUtils.equals(opType, AtouSignInRecord.END);

		AtouSignInRecord record = atouSignInRecordService.selectNowDayRecordByUser(userId);
		// 不存在打卡记录
		if (record == null) {
			record = new AtouSignInRecord();
			record.setUserId(userId);
			record.setId(SnowFlakerUtil.getSnowflakeId());
			if (startFlag) {
				record.setGmtCreate(new Date());
			} else if (endFlag) {
				record.setGmtEnd(new Date());
			}
			int row = atouSignInRecordService.insertSelective(record);
			if (row == 1) {
				return response;
			}
		}

		if (startFlag && record.getGmtCreate() != null) {
			response.setFailMessage("已经点过开始了，不要重复点击");

		} else if (startFlag && record.getGmtCreate() == null) {
			record.setGmtCreate(new Date());
			atouSignInRecordService.updateByPrimaryKeySelective(record);

		} else if (endFlag && record.getGmtEnd() != null) {
			response.setFailMessage("已经点过结束了，不要重复点击");

		} else {
			record.setGmtEnd(new Date());
			atouSignInRecordService.updateByPrimaryKeySelective(record);
		}
		return response;
	}

}
