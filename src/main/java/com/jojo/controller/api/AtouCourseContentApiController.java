package com.jojo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jojo.service.AtouCourseContentService;

@Controller
@RequestMapping("/api/atouCourseContent")
public class AtouCourseContentApiController {

    @Autowired
    private AtouCourseContentService atouCourseContentService;
    
}
