package com.simplenazmul.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.simplenazmul.model.LikeUserPic;



@Repository
public class LikeUserPicDaoImpl extends AbstractDao<Integer, LikeUserPic> implements LikeUserPicDao {

	@Override
	public void save(LikeUserPic likeUserPic) {
		persist(likeUserPic);
		
	}

	@Override
	public void updateUserPicLike(LikeUserPic likeUserPic) {
		update(likeUserPic);
		
	}

	@Override
	public void deleteByLikeId(int likeId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("likeId", likeId));
		LikeUserPic luPic = (LikeUserPic) crit.uniqueResult();
		delete(luPic);
		
	}

	@Override
	public void deleteByUserIdAndPicId(int userId, int userPicId) {
		Criteria crit = createEntityCriteria();

		Criterion user = Restrictions.eq("userId", userId);
		Criterion up = Restrictions.eq("userPicId", userPicId);
		Criterion rate = Restrictions.eq("likeValue", 1);

		LogicalExpression andLogic1 = Restrictions.and(up, user);
		LogicalExpression andLogic2 = Restrictions.and(andLogic1, rate);
		crit.add(andLogic2);

		LikeUserPic luPic = (LikeUserPic) crit.uniqueResult();

		delete(luPic);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LikeUserPic> listLikeCountByPicId(int userPicId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userPicId", userPicId));
		crit.add(Restrictions.eq("likeValue", 1));

		List<LikeUserPic> likeCountList = crit.list();

		return likeCountList;
	}

	@Override
	public boolean likeOrNotByPic(int userPicId, int userId) {
		Criteria crit = createEntityCriteria();

		Criterion up = Restrictions.eq("userPicId", userPicId);
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
	public int likeCountByPic(int userPicId) {
		Criteria crit = createEntityCriteria();

		Criterion up = Restrictions.eq("userPicId", userPicId);
		Criterion rate = Restrictions.eq("likeValue", 1);

		LogicalExpression andLogic1 = Restrictions.and(up, rate);
		crit.add(andLogic1);

		int count = crit.list().size();

		return count;
	}

}
