package com.jojo.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jojo.model.AtouCourse;
import com.jojo.model.AtouCourseIndex;
import com.jojo.service.AtouCourseIndexService;
import com.jojo.service.AtouCourseService;

@Service
@Transactional
public class AtouCourseIndexServiceImpl extends BaseServiceImpl<AtouCourseIndex> implements AtouCourseIndexService {

	@Autowired
	private AtouCourseService atouCourseService;

	@Override
	public List<JSONObject> selectAllTreeInfo() {
		List<AtouCourse> atouCourseList = atouCourseService.selectAll();
		if (CollectionUtils.isEmpty(atouCourseList)) {
			return null;
		}
		List<AtouCourseIndex> atouCourseIndexList = this.selectAll();
		if (CollectionUtils.isEmpty(atouCourseIndexList)) {
			return null;
		}

		return null;
	}

	@Override
	public JSONArray selectOneTreeInfo(Long parentId, List<AtouCourseIndex> atouCourseIndexList) {
		JSONArray treeNode = new JSONArray();
		if (CollectionUtils.isEmpty(atouCourseIndexList)) {
			return treeNode;
		}
		for (Iterator<AtouCourseIndex> iterator = atouCourseIndexList.iterator(); iterator.hasNext();) {
			AtouCourseIndex courseIndex = iterator.next();
			if (courseIndex.getParentId() == parentId) {
				iterator.remove();
				JSONObject courseIndexJson = (JSONObject) JSON.toJSON(courseIndex);
				// 递归
				JSONArray child = selectOneTreeInfo(courseIndex.getId(), atouCourseIndexList);
				if (CollectionUtils.isNotEmpty(child)) {
					courseIndexJson.put("child", child);
				}
				treeNode.add(courseIndexJson);
			}
		}
		return treeNode;
	}

}
