package com.jojo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jojo.service.AtouCourseService;

@Controller
@RequestMapping("/api/atouCourse")
public class AtouCourseApiController {

    @Autowired
    private AtouCourseService atouCourseService;
    
}
