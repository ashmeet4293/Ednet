package com.simplenazmul.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.simplenazmul.model.Privacy;

@Repository
public class PrivacyDaoImpl extends AbstractDao<Integer, Privacy> implements PrivacyDao {

	@Override
	public void save(Privacy privacy) {
		persist(privacy);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Privacy> allPrivacyList() {
		Criteria crit = createEntityCriteria();

		List<Privacy> allPrivacy = crit.list();

		return allPrivacy;
	}

	@Override
	public void deleteById(int privacyId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("privacyId", privacyId));
		Privacy privacy = (Privacy) crit.uniqueResult();
		delete(privacy);

	}

	@Override
	public Privacy findById(int privacyId) {
		return getByKey(privacyId);

	}

	@Override
	public Privacy findByName(String privacyTypeName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("privacyTypeName", privacyTypeName));
		Privacy privacy = (Privacy) crit.uniqueResult();
		return privacy;
	}

}
