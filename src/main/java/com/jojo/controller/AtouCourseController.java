package com.jojo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	private static final String INDEX = "course/index";

	private static final String REDIRECT_INDEX = "redirect:/atouCourse/index";

	/**
	 * 课程管理页面
	 */
	private static final String MANAGE = "course/manage";

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
	 * 获取所有节点信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/allNodeInfo")
	@ResponseBody
	public Response allNodeInfo() {
		Response response = new Response();

		List<AtouCourse> courseList = atouCourseService.selectAll();
		if (CollectionUtils.isEmpty(courseList)) {
			response.setFailMessage("还没有课程信息");
			return response;
		}

		List<JSONObject> resultList = Lists.newArrayList();
		response.setData(resultList);
		List<JSONObject> courseList2 = Lists.transform(courseList, new Function<AtouCourse, JSONObject>() {
			@Override
			public JSONObject apply(AtouCourse input) {
				JSONObject index = new JSONObject();
				index.put("parentId", "0");
				index.put("title", input.getName());
				index.put("id", input.getId().toString());
				return index;
			}
		});
		resultList.addAll(courseList2);
		List<AtouCourseIndex> courseIndexList = atouCourseIndexService.selectAll();
		if (CollectionUtils.isEmpty(courseIndexList)) {
			return response;
		}
		List<JSONObject> courseIndexList2 = Lists.transform(courseIndexList,
				new Function<AtouCourseIndex, JSONObject>() {
					@Override
					public JSONObject apply(AtouCourseIndex input) {
						JSONObject index = new JSONObject();
						index.put("parentId", input.getParentId().toString());
						index.put("title", input.getTitle());
						index.put("id", input.getId().toString());
						return index;
					}
				});
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
	@RequestMapping(value = "/saveOrUpdateCourse", method = RequestMethod.POST)
	public String saveOrUpdateCourse(@RequestParam(name = "treeId") Long id,
			@RequestParam(name = "treeParentId") Long parentId, @RequestParam(name = "newTitle") String newTitle,
			@RequestParam(name = "operationType") String operationType, HttpSession session) {

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
			courseIndex.setTitle(newTitle);
			atouCourseIndexService.insertSelective(courseIndex);
			return REDIRECT_INDEX;
		}

		// 下面就是修改的操作
		if (parentId == 0) {
			AtouCourse course = new AtouCourse();
			course.setId(id);
			course.setName(newTitle);
			atouCourseService.updateByPrimaryKeySelective(course);
		} else {
			AtouCourseIndex courseIndex = new AtouCourseIndex();
			courseIndex.setId(id);
			courseIndex.setTitle(newTitle);
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
		return "redirect:/atouCourse/index";
	}
}
