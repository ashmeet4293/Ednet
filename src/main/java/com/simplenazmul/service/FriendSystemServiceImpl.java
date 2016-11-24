package com.simplenazmul.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simplenazmul.dao.FriendSystemDao;
import com.simplenazmul.model.FriendSystem;
import com.simplenazmul.model.User;

@Service
@Transactional
public class FriendSystemServiceImpl implements FriendSystemService {

	@Autowired
	FriendSystemDao dao;

	@Autowired
	UserService userService;

	@Override
	public void saveFriendRelation(FriendSystem friendSystem) {
		dao.saveFriendRelation(friendSystem);

	}

	@Override
	public void updateFriendRelation(FriendSystem friendSystem) {
		dao.updateFriendRelation(friendSystem);

	}

	@Override
	public void deleteFriendRelation(int userId1, int userId2) {
		dao.deleteFriendRelation(userId1, userId2);

	}

	@Override
	public List<FriendSystem> findAllFriendsById(int userId) {
		return dao.findAllFriendsById(userId);
	}

	@Override
	public boolean friendOrNot(int userId1, int userId2) {
		return dao.friendOrNot(userId1, userId2);
	}

	@Override
	public FriendSystem findFriendRelation(int userId1, int userId2) {
		return dao.findFriendRelation(userId1, userId2);
	}

	@Override
	public List<User> findAllFriendListById(int loginUserId) {
		List<FriendSystem> friendList = this.findAllFriendsById(loginUserId);

		int count = 0;
		int id1 = 0;
		int id2 = 0;
		List<User> userFriendList = new ArrayList<User>();

		for (FriendSystem friend : friendList) {
			count = count + 1;
			id1 = friend.getUserIdOne();
			id2 = friend.getUserIdTwo();

			try {
				if (id1 != loginUserId) {
					userFriendList.add(userService.findByEdnetId(id1));
				}

				if (id2 != loginUserId) {
					userFriendList.add(userService.findByEdnetId(id2));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return userFriendList;
	}

	@SuppressWarnings("unused")
	@Override
	public int friendCount(int loginUserId) {
		List<FriendSystem> friendList = this.findAllFriendsById(loginUserId);

		int count = 0;

		for (FriendSystem friend : friendList) {
			count = count + 1;

		}

		return count;
	}

	@Override
	public boolean haveAnyRelation(int userId1, int userId2) {
		return dao.haveAnyRelation(userId1, userId2);
	}

	@Override
	public List<FriendSystem> findAllFriendRequests(int loginUserId) {
		return dao.findAllFriendRequests(loginUserId);
	}

	@SuppressWarnings("unused")
	@Override
	public int totalFriendRequest(int loginUserId) {
		List<FriendSystem> fs = this.findAllFriendRequests(loginUserId);
		int count = 0;

		for (FriendSystem fs1 : fs) {
			count = count + 1;
		}

		return count;
	}

	@Override
	public List<User> findAllFriendRequestUserListById(int loginUserId) {
		List<FriendSystem> friendRequest = findAllFriendRequests(loginUserId);
		List<User> friendRequestUser = new ArrayList<User>();

		for (FriendSystem friend : friendRequest) {
			int userIdOne = friend.getUserIdOne();
			int userIdTwo = friend.getUserIdTwo();

			if (userIdOne == loginUserId) {
				friendRequestUser.add(userService.findByEdnetId(userIdTwo));
			} else if (userIdTwo == loginUserId) {
				friendRequestUser.add(userService.findByEdnetId(userIdOne));
			}
		}

		return friendRequestUser;
	}
}
