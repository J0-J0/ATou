package com.jojo.service;

import com.jojo.model.AtouSignInRecord;
import com.jojo.pojo.Response;

public interface AtouSignInRecordService extends BaseService<AtouSignInRecord> {

	Response startSigningIn(long userId, long courseId);

	Response endSigningIn(long userId, long courseId);

}
