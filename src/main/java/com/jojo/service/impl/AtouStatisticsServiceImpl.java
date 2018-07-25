package com.jojo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jojo.model.AtouStatistics;
import com.jojo.service.AtouStatisticsService;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class AtouStatisticsServiceImpl extends BaseServiceImpl<AtouStatistics> implements AtouStatisticsService {

	@Override
	public AtouStatistics selectOneById(Long id) {
		Example example = buildExample();
		example.createCriteria().andEqualTo(AtouStatistics.PROP_ID, id);
		return selectOneByExample(example);
	}

	@Override
	public AtouStatistics selectOneByUserId(Long userId) {
		Example example = buildExample();
		example.createCriteria().andEqualTo(AtouStatistics.PROP_USER_ID, userId);
		return selectOneByExample(example);
	}

}
