package com.jojo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jojo.model.AtouCourse;
import com.jojo.service.AtouCourseService;

@Service
@Transactional
public class AtouCourseServiceImpl extends BaseServiceImpl<AtouCourse> implements AtouCourseService {

}
