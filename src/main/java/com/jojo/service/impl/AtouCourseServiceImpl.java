package com.jojo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jojo.mapper.AtouCourseMapper;
import com.jojo.model.AtouCourse;
import com.jojo.service.AtouCourseIndexService;
import com.jojo.service.AtouCourseService;

@Service
@Transactional
public class AtouCourseServiceImpl extends BaseServiceImpl<AtouCourse> implements AtouCourseService {

	@Autowired
	private AtouCourseMapper atouCourseMapper;
	
	@Autowired
	private AtouCourseIndexService atouCourseIndexService;

	@Override
	public List<AtouCourse> selectByUserId(Long id) {
		return atouCourseMapper.selectByUserId(id);
	}

}
