package com.simplenazmul.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.simplenazmul.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public void save(User user) {
		persist(user);
	}

	public User findById(int id) {
		return getByKey(id);
	}

	public User findByUserName(String userName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userName", userName));
		return (User) crit.uniqueResult();
	}

	@Override
	public User findByEmail(String email) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		return (User) crit.uniqueResult();
	}

	public void deleteByUserName(String userName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userName", userName));
		User user = (User) crit.uniqueResult();
		delete(user);
	}

	@Override
	public User userInformationFromUsername(String userName) {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		Criteria crit = createEntityCriteria();

		crit.addOrder(Order.desc("ednetUserId"));

		// ProjectionList projList = Projections.projectionList();
		// projList.add(Projections.property("ednetUserId"));
		// projList.add(Projections.property("firstName"));
		////
		// crit.setProjection(projList);

		List<User> ab = crit.list();

		return ab;
	}

	@Override
	public void updateUser(User user) {
		update(user);

	}

	@Override
	public void deleteByUserId(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ednetUserId", id));
		User user = (User) crit.uniqueResult();
		delete(user);

	}

	@Override
	public boolean login(String email, String password) {
		Criteria crit = createEntityCriteria();
		Criterion email2 = Restrictions.eq("email", email);
		Criterion password2 = Restrictions.eq("password", password);

		LogicalExpression andlogic = Restrictions.and(email2, password2);
		crit.add(andlogic);

		Object ob = crit.uniqueResult();

		if (ob != null) {
			return true;
		}
		return false;

	}

	@Override
	public User findByEdnetId(int ednetUserId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ednetUserId", ednetUserId));

		return (User) crit.uniqueResult();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<User> findAllUsersBySearchTerm(String searchTerm) {
		Criteria crit = createEntityCriteria();

		String ab = searchTerm;

		if (ab == "") {
			List<User> userList = new ArrayList();
			return userList;
		} else {

			ab = ab.replaceAll("\"", "");

			StringBuilder search1 = new StringBuilder();
			search1.append(ab);
			search1.append("%");

			String mainSearchTerm = search1.toString();

			Criterion like1 = Restrictions.like("firstName", mainSearchTerm);
			Criterion like2 = Restrictions.like("lastName", mainSearchTerm);

			LogicalExpression like = Restrictions.or(like1, like2);
			crit.add(like);

			crit.setMaxResults(5);

			List<User> userList = crit.list();

			return userList;
		}

	}

}
