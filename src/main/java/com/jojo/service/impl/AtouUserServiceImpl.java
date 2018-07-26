package com.jojo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jojo.model.AtouUser;
import com.jojo.service.AtouUserService;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class AtouUserServiceImpl extends BaseServiceImpl<AtouUser> implements AtouUserService {

	@Override
	public AtouUser selectOneById(Long id) {
		Example example = buildExample();
		example.createCriteria().andEqualTo(AtouUser.PROP_ID, id);
		return selectOneByExample(example);
	}

	@Override
	public AtouUser selectOneByWxid(String wxid) {
		Example example = buildExample();
		example.createCriteria().andEqualTo(AtouUser.PROP_WXID, wxid);
		return selectOneByExample(example);
	}

	@Override
	public List<AtouUser> getRankingList() {
		Example example = buildExample();
		example.orderBy(AtouUser.PROP_TOTAL_CLICK).desc();
		return selectByExample(example);
	}

}
