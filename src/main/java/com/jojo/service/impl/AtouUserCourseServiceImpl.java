package com.jojo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jojo.model.AtouUserCourse;
import com.jojo.service.AtouUserCourseService;

@Service
@Transactional
public class AtouUserCourseServiceImpl extends BaseServiceImpl<AtouUserCourse> implements AtouUserCourseService {

}
