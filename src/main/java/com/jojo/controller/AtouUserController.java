package com.jojo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jojo.model.AtouCourse;
import com.jojo.model.AtouUser;
import com.jojo.pojo.Response;
import com.jojo.service.AtouCourseService;
import com.jojo.service.AtouUserService;

@Controller
@RequestMapping("/atouUser")
public class AtouUserController {

	@Autowired
	private AtouUserService atouUserService;
	
	@Autowired
	private AtouCourseService atouCourseService;

	/**
	 * 学员管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manage")
	public String showManage() {
		return "user/manage";
	}
	
	
	/*********************************************这里往下是接口，都是接口*******************************************/
	
	/**
	 * 用户初始化<br>
	 * 初始化的话，肯定要先往用户表里插一条数据，然后再往统计表里插一条数据
	 * 
	 * @param userId 我也不知道会有什么参数，先写一个
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/api/initialize")
	public Response initialize(@RequestParam String wxid) {
		Response response = new Response();
		AtouUser user = atouUserService.selectOneByWxid(wxid);
		if (user != null) {
			response.setFailMessage("已经存在该用户了");
			response.setData(user);
			return response;
		}

		user = new AtouUser();
		user.setWxid(wxid);
		int userRow = atouUserService.insertSelective(user);

		if (userRow == 1) {
			response.setSuccessMessage("初始化成功");
			response.setData(user);
		} else {
			response.setFailMessage("用户初始化失败，请稍后再试");
		}
		return response;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/api/getUserInfo")
	public String getUserInfo(@RequestParam Long id) {
		Response response = new Response();
		AtouUser user = atouUserService.selectOneById(id);
		if (user == null) {
			response.setFailMessage("没有获取到用户信息");
			return JSON.toJSONString(response);
		}
		
		List<AtouCourse> courseList = atouCourseService.selectByUserId(id);
		
		response.setSuccessMessage("");
		JSONObject jsonResponse = JSON.parseObject(JSON.toJSONString(response));
		jsonResponse.put("user", user);
		jsonResponse.put("courseList", courseList);
		
		return jsonResponse.toJSONString();
	}

	/**
	 * 获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/api/getRankingList")
	public Response getRankingList() {
		Response response = new Response();
		List<AtouUser> userList = atouUserService.getRankingList();
		response.setData(userList);
		return response;
	}
}
