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

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<UserPost> findNewsFeedPostByUser(User loginUser, List<User> userObject) {
		Criteria crit = createEntityCriteria();

		List<UserPost> up = crit.list();

		return up;

		// Disjunction ds = null;
		//
		// Criterion loginUserPost = Restrictions.eq("user", loginUser);
		// Criterion friendUser[] = null;
		// Criterion friendUser2[] = null;
		// Criterion c2[] = null;
		// Criterion c3[] = null;
		// Criterion privacy[]= null;
		// LogicalExpression orLogicFriend[] = null;
		// LogicalExpression andLogic = null;
		//
		// int friendCount = userObject.size();
		//
		// for(int i=0 ; i<friendCount; i++){
		//
		// friendUser[i] = Restrictions.eq("user",
		// userObject.iterator().next());
		// c2[i] = Restrictions.eq("privacy", "PUBLIC");
		// c3[i] = Restrictions.eq("privacy", "FRIENDS");
		//// orLogic[i] = Restrictions.or(c2[i], c3[i]);
		//// andLogic[i] = Restrictions.and(orLogic[i], friendUser[i]);
		//
		//
		//// crit.add(andLogic[i]);
		//// up.addAll(crit.list());
		//
		//
		// System.out.println(userObject.iterator().next());
		//
		// }
		//
		// if(friendCount >1){
		// for(int i=1; i<friendCount; i++){
		// logger.info("inside for Loop 455566");
		// //friendUser2[i-1] = Restrictions.eq("user", friendUser[i]);
		// try {
		// orLogicFriend[i-1] = Restrictions.or(friendUser[i-1],friendUser[i]);
		// privacy[i-1] = Restrictions.or(c2[i-1], c3[i-1]);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		//
		// andLogic = Restrictions.and(orLogicFriend[friendCount],
		// privacy[friendCount]);
		// crit.add(andLogic);
		// }
		//
		//
		//
		// List<UserPost> up2 = crit.list();
		//
		// return up2;
		//
		//

		// if(userObject.iterator().hasNext()){
		// try {
		// Criterion friendUser = Restrictions.eq("user",
		// userObject.iterator().next());
		// Criterion c2 = Restrictions.eq("privacy", "PUBLIC");
		// Criterion c3 = Restrictions.eq("privacy", "FRIENDS");
		// LogicalExpression orLogic = Restrictions.or(c2, c3);
		// LogicalExpression andLogic1 = Restrictions.and(orLogic, friendUser);
		// LogicalExpression orLogic2 = Restrictions.or(andLogic1, c1);
		//
		// crit.add(orLogic2);
		//
		// System.out.println(userObject.iterator().next());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		// for(User user : userObject){
		// Criterion friendUser = Restrictions.eq("user", user);
		// Criterion c2 = Restrictions.eq("privacy", "PUBLIC");
		// Criterion c3 = Restrictions.eq("privacy", "FRIENDS");
		// LogicalExpression orLogic = Restrictions.or(c2, c3);
		// LogicalExpression andLogic2 = Restrictions.and(orLogic, friendUser);
		//
		// System.out.println(user);
		// }

		//
		// List<UserPost> up2 = crit.list();
		//
		// //up.addAll(up2);
		//
		// return up2;

		// Disjunction or = Restrictions.disjunction();
		//
		// List<UserPost> listUPost = new ArrayList<UserPost>();
		//
		// //crit.add(Restrictions.eq("user", loginUser));
		//
		// //listUPost.addAll(crit.list());
		//
		// User user = userObject.iterator().next();
		//
		// if(user != null){
		// or.add(Restrictions.disjunction()
		// .add(Restrictions.eq("user", user))
		// .add(Restrictions.eq("privacy", "PUBLIC"))
		// .add(Restrictions.eq("privacy", "FRIENDS"))
		//
		// );
		//
		// }
		//
		//
		// crit.add(or);
		//
		//
		//
		//
		// crit.addOrder(Order.desc("userPostId"));
		//
		// //listUPost.addAll(crit.list());
		//
		// List<UserPost> up = crit.list();

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
