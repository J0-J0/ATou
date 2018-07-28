package com.jojo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jojo.service.AtouUserCourseService;

@Controller
@RequestMapping("/api/atouUserCourse")
public class AtouUserCourseApiController {

    @Autowired
    private AtouUserCourseService atouUserCourseService;
    
}
