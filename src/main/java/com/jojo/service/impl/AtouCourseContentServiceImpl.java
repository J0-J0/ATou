package com.jojo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jojo.model.AtouCourseContent;
import com.jojo.service.AtouCourseContentService;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class AtouCourseContentServiceImpl extends BaseServiceImpl<AtouCourseContent>
		implements AtouCourseContentService {

	@Override
	public AtouCourseContent selectOneByCourseIdAndIndexId(Long courseId, Long indexId) {
		Example example = buildExample();
		example.createCriteria().andEqualTo(AtouCourseContent.PROP_COURSE_ID, courseId)
				.andEqualTo(AtouCourseContent.PROP_INDEX_ID, indexId);
		return selectOneByExample(example);
	}

	@Override
	public int updateByIndexIdSelective(AtouCourseContent content) {
		Example example = buildExample();
		example.createCriteria().andEqualTo(AtouCourseContent.PROP_COURSE_ID, content.getCourseId())
				.andEqualTo(AtouCourseContent.PROP_INDEX_ID, content.getIndexId());
		return updateByExampleSelective(content, example);
	}

}
