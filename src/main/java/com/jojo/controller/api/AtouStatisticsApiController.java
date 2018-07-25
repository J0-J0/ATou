package com.jojo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jojo.service.AtouStatisticsService;

@Controller
@RequestMapping("/api/atouStatistics")
public class AtouStatisticsApiController {

    @Autowired
    private AtouStatisticsService atouStatisticsService;
    
}
