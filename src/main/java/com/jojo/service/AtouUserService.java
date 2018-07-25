package com.jojo.service;

import com.jojo.model.AtouUser;

public interface AtouUserService extends BaseService<AtouUser> {

	AtouUser selectOneById(Long id);

	AtouUser selectOneByWxid(String wxid);

}
