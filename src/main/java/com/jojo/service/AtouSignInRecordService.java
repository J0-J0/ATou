package com.jojo.service;

import com.jojo.model.AtouSignInRecord;

public interface AtouSignInRecordService extends BaseService<AtouSignInRecord> {

	AtouSignInRecord selectNowDayRecordByUser(long userId);

}
