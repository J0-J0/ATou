package com.jojo.service;

import java.util.List;

import com.jojo.model.AtouCourse;

public interface AtouCourseService extends BaseService<AtouCourse> {

	List<AtouCourse> selectByUserId(Long id);

}
