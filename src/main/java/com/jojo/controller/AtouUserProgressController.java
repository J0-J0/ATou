package com.jojo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jojo.service.AtouUserProgressService;

@Controller
@RequestMapping("/api/atouUserProgress")
public class AtouUserProgressController {

    @Autowired
    private AtouUserProgressService atouUserProgressService;
    
}