package com.simplenazmul.service;

import java.util.List;

import com.simplenazmul.model.Privacy;

public interface PrivacyService {

	void save(Privacy privacy);

	List<Privacy> allPrivacyList();

	void deleteById(int privacyId);

	Privacy findById(int privacyId);

	Privacy findByName(String privacyTypeName);

}
