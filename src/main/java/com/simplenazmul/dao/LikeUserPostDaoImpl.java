package com.simplenazmul.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.simplenazmul.model.LikeUserPost;

@Repository
public class LikeUserPostDaoImpl extends AbstractDao<Integer, LikeUserPost> implements LikeUserPostDao {

	@Override
	public void save(LikeUserPost likeUserPost) {
		persist(likeUserPost);

	}

	@Override
	public void updateUserPostLike(LikeUserPost likeUserPost) {
		update(likeUserPost);

	}

	@Override
	public void deleteByLikeId(int likeId) {

		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("likeId", likeId));
		LikeUserPost luPost = (LikeUserPost) crit.uniqueResult();
		delete(luPost);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LikeUserPost> listLikeCountByPostId(int userPostId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userPostId", userPostId));
		crit.add(Restrictions.eq("likeValue", 1));

		List<LikeUserPost> likeCountList = crit.list();

		return likeCountList;
	}

	@Override
	public boolean likeOrNotByPost(int userPostId, int userId) {
		Criteria crit = createEntityCriteria();

		Criterion up = Restrictions.eq("userPostId", userPostId);
		Criterion user2 = Restrictions.eq("userId", userId);
		Criterion rate = Restrictions.eq("likeValue", 1);

		LogicalExpression andLogic1 = Restrictions.and(up, user2);
		LogicalExpression andLogic2 = Restrictions.and(andLogic1, rate);
		crit.add(andLogic2);

		Object ob = crit.uniqueResult();

		if (ob != null) {
			return true;
		}

		return false;
	}

	@Override
	public int likeCountByPost(int userPostId) {
		Criteria crit = createEntityCriteria();

		Criterion up = Restrictions.eq("userPostId", userPostId);
		Criterion rate = Restrictions.eq("likeValue", 1);

		LogicalExpression andLogic1 = Restrictions.and(up, rate);
		crit.add(andLogic1);

		int count = crit.list().size();

		return count;
	}

	@Override
	public void deleteByUserIdAndPostId(int userId, int userPostId) {
		Criteria crit = createEntityCriteria();

		Criterion user = Restrictions.eq("userId", userId);
		Criterion up = Restrictions.eq("userPostId", userPostId);
		Criterion rate = Restrictions.eq("likeValue", 1);

		LogicalExpression andLogic1 = Restrictions.and(up, user);
		LogicalExpression andLogic2 = Restrictions.and(andLogic1, rate);
		crit.add(andLogic2);

		LikeUserPost luPost = (LikeUserPost) crit.uniqueResult();

		delete(luPost);

	}

}
