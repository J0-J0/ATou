package com.jojo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jojo.model.AtouCourse;
import com.jojo.model.AtouUser;
import com.jojo.pojo.Constant;
import com.jojo.pojo.Response;
import com.jojo.service.AtouCourseService;
import com.jojo.service.AtouUserService;
import com.jojo.util.SnowFlakerUtil;

@Controller
@RequestMapping("/atouUser")
public class AtouUserController {

	/**
	 * 用户管理页面
	 */
	private static final String MANAGE = "user/userManage";

	/**
	 * 用户课程页面
	 */
	private static final String SAVE_OR_UPDATE_USER = "user/saveOrUpdateUser";

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
		return MANAGE;
	}

	/**
	 * 显示更新课程页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
	public String updateUser(@PathVariable("id") Long id, Model model) {
		if (id == 0) {
			model.addAttribute("operationType", Constant.OPERATION_TYPE_SAVE);
			return SAVE_OR_UPDATE_USER;
		}
		AtouUser user = atouUserService.selectOneByPrimaryKey(id);
		model.addAttribute("user", user);
		model.addAttribute("operationType", Constant.OPERATION_TYPE_UPDATE);
		return SAVE_OR_UPDATE_USER;
	}

	/**
	 * 保存或新增
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateUser", method = RequestMethod.POST)
	public String saveOrUpdateUser(AtouUser user, @RequestParam String operationType, Model model) {
		String message = "";
		int row = 0;
		if (StringUtils.equals(operationType, Constant.OPERATION_TYPE_UPDATE)) {
			model.addAttribute("user", user);
			Long id = user.getId();
			if (id == null || id == 0l) {
				message = "更新失败，user id为空";
				model.addAttribute("message", message);
				return SAVE_OR_UPDATE_USER;
			}
			row = atouUserService.updateByPrimaryKeySelective(user);
			if (row == 1) {
				message = "更新成功";
			} else {
				message = "更新失败";
			}
			model.addAttribute("message", message);
			model.addAttribute("operationType", Constant.OPERATION_TYPE_UPDATE);
			return SAVE_OR_UPDATE_USER;
		}

		user.setId(SnowFlakerUtil.getSnowflakeId());
		row = atouUserService.insertSelective(user);
		if (row == 1) {
			message = "新增成功";
		} else {
			message = "新增失败";
		}
		model.addAttribute("user", user);
		model.addAttribute("message", message);
		model.addAttribute("operationType", Constant.OPERATION_TYPE_UPDATE);
		return SAVE_OR_UPDATE_USER;
	}

	/**
	 * 
	 * @param id
	 * @param parentId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public Response deleteUser(@RequestParam Long id, HttpSession session) {
		Response response = new Response();
		String message = "";
		int row = atouUserService.deleteByPrimaryKey(id);
		if (row > 0) {
			message = "删除成功";
			response.setSuccessMessage(message);
		} else {
			message = "删除失败";
			response.setFailMessage(message);
		}
		return response;
	}

	/**
	 * 获取所有课程信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/allUserInfo")
	@ResponseBody
	public Response allUserInfo() {
		Response response = new Response(Response.FAIL, ""); // code必须设成0，这是为layui的table服务的
		List<AtouUser> userList = atouUserService.selectAll();
		response.setData(userList);
		return response;
	}

	/**********************************************
	 * 这里往下是接口，都是接口
	 *******************************************/

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
