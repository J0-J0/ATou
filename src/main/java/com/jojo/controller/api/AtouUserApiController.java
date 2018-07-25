package com.jojo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.model.AtouStatistics;
import com.jojo.model.AtouUser;
import com.jojo.pojo.Response;
import com.jojo.service.AtouStatisticsService;
import com.jojo.service.AtouUserService;

@Controller
@RequestMapping("/api/atouUser")
public class AtouUserApiController {

	@Autowired
	private AtouUserService atouUserService;

	@Autowired
	private AtouStatisticsService atouStatisticsService;

	/**
	 * 用户初始化<br>
	 * 初始化的话，肯定要先往用户表里插一条数据，然后再往统计表里插一条数据
	 * 
	 * @param userId
	 *            我也不知道会有什么参数，先写一个
	 * @return
	 */
	@ResponseBody
	public Response initialize(@RequestParam String wxid) {
		Response response = new Response();
		AtouUser user = atouUserService.selectOneByWxid(wxid);
		AtouStatistics statistics = null;
		if (user != null) {
			response.setFailMessage("已经存在该用户了");
			statistics = atouStatisticsService.selectOneByUserId(user.getId());
			if (statistics == null) {
				statistics = new AtouStatistics();
				statistics.setUserId(user.getId());
				atouStatisticsService.insertSelective(statistics);
			}
			return response;
		}

		user = new AtouUser();
		user.setWxid(wxid);
		int userRow = atouUserService.insertSelective(user);

		statistics = atouStatisticsService.selectOneByUserId(user.getId());
		if (statistics == null) {
			statistics = new AtouStatistics();
			statistics.setUserId(user.getId());
			atouStatisticsService.insertSelective(statistics);
		}

		if (userRow == 1) {
			response.setSuccessMessage("初始化成功");
		}
		return response;
	}

}
