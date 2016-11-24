package com.simplenazmul.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simplenazmul.dao.PrivacyDao;
import com.simplenazmul.model.Privacy;

@Service
@Transactional
public class PrivacyServiceImpl implements PrivacyService {

	@Autowired
	private PrivacyDao dao;

	@Override
	public void save(Privacy privacy) {
		dao.save(privacy);

	}

	@Override
	public List<Privacy> allPrivacyList() {
		return dao.allPrivacyList();
	}

	@Override
	public void deleteById(int privacyId) {
		dao.deleteById(privacyId);

	}

	@Override
	public Privacy findById(int privacyId) {
		return dao.findById(privacyId);
	}

	@Override
	public Privacy findByName(String privacyTypeName) {
		return dao.findByName(privacyTypeName);
	}

}
