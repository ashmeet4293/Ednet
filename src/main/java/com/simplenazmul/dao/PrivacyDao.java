package com.simplenazmul.dao;

import java.util.List;

import com.simplenazmul.model.Privacy;

public interface PrivacyDao {

	void save(Privacy privacy);

	List<Privacy> allPrivacyList();

	void deleteById(int privacyId);

	Privacy findById(int privacyId);

	Privacy findByName(String privacyTypeName);
}
