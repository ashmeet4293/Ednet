package com.simplenazmul.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPicAlbum;

@Repository
public class UserPicAlbumDaoImpl extends AbstractDao<Integer, UserPicAlbum> implements UserPicAlbumDao {

	@Override
	public void save(UserPicAlbum userPicAlbum) {
		persist(userPicAlbum);

	}

	@Override
	public UserPicAlbum findById(int albumId) {
		return getByKey(albumId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPicAlbum> findAllAlbumByUserId(User user) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("user", user));
		crit.addOrder(Order.desc("albumId"));

		List<UserPicAlbum> userAlbumList = crit.list();

		return userAlbumList;
	}

	@Override
	public String getAlbumCaptionById(UserPicAlbum albumId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("albumCaption", albumId));
		return (String) crit.uniqueResult();
	}

	@Override
	public void deleteAlbumById(UserPicAlbum albumId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("albumId", albumId));
		UserPicAlbum userAlbum = (UserPicAlbum) crit.uniqueResult();
		delete(userAlbum);

	}

	@Override
	public int findUserPicAlbumByAlbumCaptionAndUser(String albumCaption, User user) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("albumCaption", albumCaption));
		crit.add(Restrictions.eq("user", user));

		UserPicAlbum userAlbum = (UserPicAlbum) crit.uniqueResult();

		int albumId = userAlbum.getAlbumId();

		return albumId;
	}

}
