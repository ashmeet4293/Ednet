package com.simplenazmul.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPic;
import com.simplenazmul.model.UserPicAlbum;

@Repository
public class UserPicDaoImpl extends AbstractDao<Integer, UserPic> implements UserPicDao {

	@Override
	public void save(UserPic userPicture) {
		persist(userPicture);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPic> findByAlbumId(UserPicAlbum albumId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("albumId", albumId));

		List<UserPic> userPic = crit.list();
		return userPic;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPic> findByUser(User user) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("user", user));

		List<UserPic> userPic = crit.list();
		return userPic;
	}

	@Override
	public UserPic findById(int userPictureId) {
		return getByKey(userPictureId);
	}

	@Override
	public void deleteById(int userPictureId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("pictureId", userPictureId));
		UserPic user = (UserPic) crit.uniqueResult();
		delete(user);

	}

}
