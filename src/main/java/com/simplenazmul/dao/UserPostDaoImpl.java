package com.simplenazmul.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPost;

@Repository("userPostDao")
public class UserPostDaoImpl extends AbstractDao<Integer, UserPost> implements UserPostDao {

	Logger logger = LoggerFactory.getLogger(UserPostDaoImpl.class);

	@Override
	public void save(UserPost userPost) {
		persist(userPost);

	}

	@Override
	public UserPost findById(int id) {
		return getByKey(id);
	}

	@Override
	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userPostId", id));
		UserPost userPost = (UserPost) crit.uniqueResult();
		delete(userPost);

	}

	@Override
	public List<UserPost> findAllPost() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.desc("userPostId"));
		@SuppressWarnings("unchecked")
		List<UserPost> userPostList = crit.list();

		return userPostList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPost> findLatest30Post() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.desc("userPostId"));
		// crit.setFirstResult(1);
		// crit.setMaxResults(10);

		crit.setMaxResults(30);
		List<UserPost> userPostList = crit.list();

		return userPostList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPost> findPostByUserId(User user) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("user", user));
		crit.addOrder(Order.desc("userPostId"));
		crit.setMaxResults(30);
		List<UserPost> userPostList = crit.list();

		return userPostList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPost> findByPrivacy(String privacy) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("privacy", privacy));

		List<UserPost> userPostList = crit.list();

		return userPostList;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<UserPost> friendPost(User user) {

		Criteria crit = createEntityCriteria();

		Criterion friendUser = Restrictions.eq("user", user);
		Criterion c2 = Restrictions.eq("privacy", "PUBLIC");
		Criterion c3 = Restrictions.eq("privacy", "FRIENDS");
		LogicalExpression orLogicPrivacy = Restrictions.or(c2, c3);
		LogicalExpression andLogic1 = Restrictions.and(orLogicPrivacy, friendUser);
		crit.add(andLogic1);

		crit.addOrder(Order.desc("userPostId"));
		List<UserPost> up = crit.list();

		return up;

	}

	@Override
	public List<UserPost> orderUserPost(List<UserPost> userPosts) {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPost> publicPost(User user) {
		Criteria crit = createEntityCriteria();

		Criterion friendUser = Restrictions.eq("user", user);
		Criterion c2 = Restrictions.eq("privacy", "PUBLIC");
		LogicalExpression andLogic1 = Restrictions.and(c2, friendUser);
		crit.add(andLogic1);

		crit.addOrder(Order.desc("userPostId"));
		List<UserPost> up = crit.list();

		return up;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPost> findLoginUserLatestPostByUserId(User user) {
		Criteria crit = createEntityCriteria();

		Criterion postUser = Restrictions.eq("user", user);
		crit.add(postUser);

		crit.addOrder(Order.desc("userPostId"));
		crit.setMaxResults(1);

		List<UserPost> ab = crit.list();

		return ab;
	}

}
