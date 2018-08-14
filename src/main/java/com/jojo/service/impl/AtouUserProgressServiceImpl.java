package com.jojo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jojo.model.AtouUserProgress;
import com.jojo.service.AtouUserProgressService;

@Service
@Transactional
public class AtouUserProgressServiceImpl extends BaseServiceImpl<AtouUserProgress> implements AtouUserProgressService {

}
