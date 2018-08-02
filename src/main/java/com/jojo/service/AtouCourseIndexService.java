package com.jojo.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jojo.model.AtouCourseIndex;

public interface AtouCourseIndexService extends BaseService<AtouCourseIndex> {

	/**
	 * 
	 * @return
	 */
	List<JSONObject> selectAllTreeInfo();

	/**
	 * 一门课的树信息
	 * 
	 * @param parentId
	 * @param atouCourseIndexList
	 * @return
	 */
	JSONArray selectOneTreeInfo(Long parentId, List<AtouCourseIndex> atouCourseIndexList);
}
