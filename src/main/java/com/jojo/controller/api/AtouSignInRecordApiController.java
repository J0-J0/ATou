package com.jojo.controller.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.model.AtouSignInRecord;
import com.jojo.pojo.Response;
import com.jojo.service.AtouSignInRecordService;

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
	public Response signIn(@RequestParam long userId, @RequestParam long courseId, @RequestParam String opType) {
		Response response = new Response();
		response.setSuccessMessage("");

		boolean startFlag = StringUtils.equals(opType, AtouSignInRecord.START);
		boolean endFlag = StringUtils.equals(opType, AtouSignInRecord.END);

		if (startFlag) {
			return atouSignInRecordService.startSigningIn(userId, courseId);
		}
		
		if (endFlag) {
			return atouSignInRecordService.endSigningIn(userId, courseId);
		}
		
		return response;
	}

}
