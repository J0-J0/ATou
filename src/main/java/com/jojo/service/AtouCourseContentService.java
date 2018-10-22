package com.jojo.service;

import com.jojo.model.AtouCourseContent;

public interface AtouCourseContentService extends BaseService<AtouCourseContent> {

	AtouCourseContent selectOneByCourseIdAndIndexId(Long courseId, Long indexId);
	
	int updateByIndexIdSelective(AtouCourseContent content);
}
