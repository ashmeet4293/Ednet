package com.simplenazmul.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.simplenazmul.model.FriendSystem;

@Repository
public class FriendSystemDaoImpl extends AbstractDao<Integer, FriendSystem> implements FriendSystemDao {

	@Override
	public void saveFriendRelation(FriendSystem friendSystem) {
		persist(friendSystem);

	}

	@Override
	public void updateFriendRelation(FriendSystem friendSystem) {
		update(friendSystem);

	}

	@Override
	public void deleteFriendRelation(int userId1, int userId2) {
		Criteria crit = createEntityCriteria();

		Criterion user11 = Restrictions.eq("userIdOne", userId1);
		Criterion user12 = Restrictions.eq("userIdTwo", userId1);

		Criterion user21 = Restrictions.eq("userIdOne", userId2);
		Criterion user22 = Restrictions.eq("userIdTwo", userId2);

		LogicalExpression orLogic1 = Restrictions.or(user11, user12);

		LogicalExpression orLogic2 = Restrictions.or(user21, user22);

		LogicalExpression andLogic1 = Restrictions.and(orLogic1, orLogic2);
		crit.add(andLogic1);

		FriendSystem friendRelation = (FriendSystem) crit.uniqueResult();
		delete(friendRelation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FriendSystem> findAllFriendsById(int userId) {
		Criteria crit = createEntityCriteria();

		Criterion column1 = Restrictions.eq("userIdOne", userId);
		Criterion column2 = Restrictions.eq("userIdTwo", userId);

		Criterion status = Restrictions.eq("status", 1);

		LogicalExpression orLogic = Restrictions.or(column1, column2);

		LogicalExpression andLogic = Restrictions.and(orLogic, status);
		crit.add(andLogic);

		List<FriendSystem> friendList = crit.list();

		return friendList;

	}

	@Override
	public boolean friendOrNot(int userId1, int userId2) {
		Criteria crit = createEntityCriteria();

		Criterion user11 = Restrictions.eq("userIdOne", userId1);
		Criterion user12 = Restrictions.eq("userIdTwo", userId1);

		Criterion user21 = Restrictions.eq("userIdOne", userId2);
		Criterion user22 = Restrictions.eq("userIdTwo", userId2);

		Criterion status = Restrictions.eq("status", 1);

		LogicalExpression orLogic1 = Restrictions.or(user11, user12);

		LogicalExpression orLogic2 = Restrictions.or(user21, user22);

		LogicalExpression andLogic1 = Restrictions.and(orLogic1, orLogic2);
		LogicalExpression andLogic2 = Restrictions.and(andLogic1, status);

		crit.add(andLogic2);

		Object ab = crit.uniqueResult();

		if (ab != null) {
			return true;
		}

		return false;
	}

	@Override
	public FriendSystem findFriendRelation(int userId1, int userId2) {
		Criteria crit = createEntityCriteria();
		Criterion user11 = Restrictions.eq("userIdOne", userId1);
		Criterion user12 = Restrictions.eq("userIdTwo", userId1);

		Criterion user21 = Restrictions.eq("userIdOne", userId2);
		Criterion user22 = Restrictions.eq("userIdTwo", userId2);

		LogicalExpression orLogic1 = Restrictions.or(user11, user12);

		LogicalExpression orLogic2 = Restrictions.or(user21, user22);

		LogicalExpression andLogic1 = Restrictions.and(orLogic1, orLogic2);
		crit.add(andLogic1);

		// ProjectionList projList = Projections.projectionList();
		// projList.add(Projections.property("status"));
		// crit.setProjection(projList);
		//
		// // Criteria count = crit.setProjection(Projections.rowCount());

		return (FriendSystem) crit.uniqueResult();

	}

	@Override
	public boolean haveAnyRelation(int userId1, int userId2) {
		Criteria crit = createEntityCriteria();
		Criterion user11 = Restrictions.eq("userIdOne", userId1);
		Criterion user12 = Restrictions.eq("userIdTwo", userId1);

		Criterion user21 = Restrictions.eq("userIdOne", userId2);
		Criterion user22 = Restrictions.eq("userIdTwo", userId2);

		LogicalExpression orLogic1 = Restrictions.or(user11, user12);

		LogicalExpression orLogic2 = Restrictions.or(user21, user22);

		LogicalExpression andLogic1 = Restrictions.and(orLogic1, orLogic2);
		crit.add(andLogic1);

		Object ab = crit.uniqueResult();

		if (ab != null) {
			return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FriendSystem> findAllFriendRequests(int loginUserId) {
		Criteria crit = createEntityCriteria();

		Criterion user11 = Restrictions.eq("userIdOne", loginUserId);
		Criterion user12 = Restrictions.eq("userIdTwo", loginUserId);

		Criterion status = Restrictions.eq("status", 0);
		Criterion lastActionUser = Restrictions.ne("lastActionUser", loginUserId);

		LogicalExpression loginUserId1 = Restrictions.or(user11, user12);
		LogicalExpression andLogic1 = Restrictions.and(status, lastActionUser);
		LogicalExpression andLogic2 = Restrictions.and(loginUserId1, andLogic1);
		crit.add(andLogic2);

		List<FriendSystem> list1 = crit.list();

		return list1;
	}

}
