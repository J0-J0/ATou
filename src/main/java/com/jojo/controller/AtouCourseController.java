package com.jojo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.jojo.model.AtouCourse;
import com.jojo.model.AtouCourseIndex;
import com.jojo.pojo.Constant;
import com.jojo.pojo.Response;
import com.jojo.service.AtouCourseIndexService;
import com.jojo.service.AtouCourseService;
import com.jojo.util.SnowFlakerUtil;

@Controller
@RequestMapping("/atouCourse")
public class AtouCourseController {

	/**
	 * 课程内容编辑页面
	 */
	private static final String INDEX = "course/courseIndex";

	private static final String REDIRECT_INDEX = "redirect:/atouCourse/index";

	/**
	 * 课程管理页面
	 */
	private static final String MANAGE = "course/courseManage";

//	private static final String REDIRECT_MANAGE = "redirect:/atouCourse/manage";

	/**
	 * 更新课程页面
	 */
	private static final String SAVE_OR_UPDATE_COURSE = "course/saveOrUpdateCourse";

	private Function<AtouCourse, JSONObject> courseToJSONObjectFunction = new Function<AtouCourse, JSONObject>() {
		@Override
		public JSONObject apply(AtouCourse input) {
			JSONObject index = new JSONObject();
			index.put("parentId", "0");
			index.put("title", input.getCourseTitle());
			index.put("id", input.getId().toString());
			return index;
		}
	};

	private Function<AtouCourseIndex, JSONObject> indexToJSONObjectFunction = new Function<AtouCourseIndex, JSONObject>() {
		@Override
		public JSONObject apply(AtouCourseIndex input) {
			JSONObject index = new JSONObject();
			index.put("parentId", input.getParentId().toString());
			index.put("title", input.getIndexTitle());
			index.put("id", input.getId().toString());
			return index;
		}
	};

	@Autowired
	private AtouCourseService atouCourseService;

	@Autowired
	private AtouCourseIndexService atouCourseIndexService;

	/**
	 * 课程内容编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return INDEX;
	}

	/**
	 * 课程管理页面
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
	@RequestMapping(value = "/updateCourse/{id}", method = RequestMethod.GET)
	public String updateCourse(@PathVariable("id") Long id, Model model) {
		if (id == 0) {
			model.addAttribute("operationType", Constant.OPERATION_TYPE_SAVE);
			return SAVE_OR_UPDATE_COURSE;
		}
		AtouCourse course = atouCourseService.selectOneByPrimaryKey(id);
		model.addAttribute("course", course);
		model.addAttribute("operationType", Constant.OPERATION_TYPE_UPDATE);
		return SAVE_OR_UPDATE_COURSE;
	}

	/**
	 * 保存或新增
	 * 
	 * @param course
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateCourse", method = RequestMethod.POST)
	public String saveOrUpdateCourse(AtouCourse course, @RequestParam String operationType, Model model) {
		String message = "";
		int row = 0;
		if (StringUtils.equals(operationType, Constant.OPERATION_TYPE_UPDATE)) {
			model.addAttribute("course", course);
			Long id = course.getId();
			if (id == null || id == 0l) {
				message = "更新失败，课程id为空";
				model.addAttribute("message", message);
				return SAVE_OR_UPDATE_COURSE;
			}
			row = atouCourseService.updateByPrimaryKeySelective(course);
			if (row == 1) {
				message = "更新成功";
			} else {
				message = "更新失败";
			}
			model.addAttribute("message", message);
			model.addAttribute("operationType", Constant.OPERATION_TYPE_UPDATE);
			return SAVE_OR_UPDATE_COURSE;
		}

		course.setId(SnowFlakerUtil.getSnowflakeId());
		row = atouCourseService.insertSelective(course);
		if (row == 1) {
			message = "新增成功";
		} else {
			message = "新增失败";
		}
		model.addAttribute("course", course);
		model.addAttribute("message", message);
		model.addAttribute("operationType", Constant.OPERATION_TYPE_UPDATE);
		return SAVE_OR_UPDATE_COURSE;
	}

	/**
	 * 
	 * @param id
	 * @param parentId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)
	@ResponseBody
	public Response deleteCourse(@RequestParam Long id, HttpSession session) {
		Response response = new Response();
		String message = "";
		int row = atouCourseService.deleteByPrimaryKey(id);
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
	@RequestMapping(value = "/allCourseInfo")
	@ResponseBody
	public Response allCourseInfo() {
		Response response = new Response(Response.FAIL, ""); // code必须设成0，这是为layui的table服务的
		List<AtouCourse> courseList = atouCourseService.selectAll();
		List<JSONObject> courseList2 = Lists.transform(courseList, courseToJSONObjectFunction);
		response.setData(courseList2);
		return response;
	}

	/**
	 * 获取所有目录信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/allIndexInfo")
	@ResponseBody
	public Response allIndexInfo() {
		Response response = new Response();

		List<AtouCourse> courseList = atouCourseService.selectAll();
		if (CollectionUtils.isEmpty(courseList)) {
			response.setFailMessage("还没有课程信息");
			return response;
		}

		List<JSONObject> resultList = Lists.newArrayList();
		response.setData(resultList);
		List<JSONObject> courseList2 = Lists.transform(courseList, courseToJSONObjectFunction);
		resultList.addAll(courseList2);
		List<AtouCourseIndex> courseIndexList = atouCourseIndexService.selectAll();
		if (CollectionUtils.isEmpty(courseIndexList)) {
			return response;
		}
		List<JSONObject> courseIndexList2 = Lists.transform(courseIndexList, indexToJSONObjectFunction);
		resultList.addAll(courseIndexList2);
		return response;
	}

	/**
	 * 更新课程
	 * 
	 * @param id
	 * @param parentId
	 * @param newTitle
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateCourseIndex", method = RequestMethod.POST)
	public String saveOrUpdateCourseIndex(@RequestParam(name = "treeId") Long id,
			@RequestParam(name = "treeParentId") Long parentId, @RequestParam(name = "newTitle") String newTitle,
			@RequestParam String operationType, HttpSession session) {

		String message = "";
		// 暂时只加目录，不加课程
		if (StringUtils.equals(operationType, Constant.OPERATION_TYPE_SAVE)) {
			if (id == null || id == 0L) {
				message = "必须标明新增目录的父目录";
				session.setAttribute("message", message);
				return REDIRECT_INDEX;
			}
			AtouCourseIndex courseIndex = new AtouCourseIndex();
			courseIndex.setId(SnowFlakerUtil.getSnowflakeId());
			courseIndex.setParentId(id);
			courseIndex.setIndexTitle(newTitle);
			atouCourseIndexService.insertSelective(courseIndex);
			return REDIRECT_INDEX;
		}

		// 下面就是修改的操作
		if (parentId == 0) {
			AtouCourse course = new AtouCourse();
			course.setId(id);
			course.setCourseTitle(newTitle);
			atouCourseService.updateByPrimaryKeySelective(course);
		} else {
			AtouCourseIndex courseIndex = new AtouCourseIndex();
			courseIndex.setId(id);
			courseIndex.setIndexTitle(newTitle);
			atouCourseIndexService.updateByPrimaryKeySelective(courseIndex);
		}
		return REDIRECT_INDEX;
	}

	/**
	 * 
	 * @param id
	 * @param parentId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteCourseIndex", method = RequestMethod.POST)
	public String deleteCourseIndex(@RequestParam(name = "treeId") Long id,
			@RequestParam(name = "treeParentId") Long parentId, HttpSession session) {
		String message = "删除失败";
		if (parentId == 0) {
			message = "此页面不支持删除课程";
		} else {
			int row = atouCourseIndexService.deleteByPrimaryKey(id);
			if (row > 0) {
				message = "删除成功";
			}
		}
		session.setAttribute("message", message);
		return REDIRECT_INDEX;
	}
}
