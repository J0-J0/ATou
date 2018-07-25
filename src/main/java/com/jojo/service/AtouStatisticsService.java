package com.jojo.service;

import com.jojo.model.AtouStatistics;

public interface AtouStatisticsService extends BaseService<AtouStatistics> {

	AtouStatistics selectOneById(Long id);

	AtouStatistics selectOneByUserId(Long userId);

}
