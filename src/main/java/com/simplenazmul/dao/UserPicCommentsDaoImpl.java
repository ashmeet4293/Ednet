package com.simplenazmul.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.simplenazmul.model.UserPicComments;

@Repository
public class UserPicCommentsDaoImpl extends AbstractDao<Integer, UserPicComments> implements UserPicCommentsDao {

	@Override
	public void save(UserPicComments userPicComments) {
		persist(userPicComments);
	}

	@Override
	public void updateUserPicComments(UserPicComments userPicComments) {
		update(userPicComments);

	}

	@Override
	public void delete(int upicCommentId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("upicCommentId", upicCommentId));
		
		UserPicComments upicComments = (UserPicComments)  crit.uniqueResult();
		
		delete(upicComments);

	}



}
