package com.jojo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.model.AtouCourseIndex;
import com.jojo.service.AtouCourseIndexService;

@Controller
@RequestMapping("/api/atouCourseIndex")
public class AtouCourseIndexApiController {

	@Autowired
	private AtouCourseIndexService atouCourseIndexService;

	@RequestMapping("/list")
	@ResponseBody
	public List<AtouCourseIndex> list() {
		return atouCourseIndexService.selectAll();
	}

}
