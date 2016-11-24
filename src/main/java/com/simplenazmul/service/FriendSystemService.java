package com.simplenazmul.service;

import java.util.List;

import com.simplenazmul.model.FriendSystem;
import com.simplenazmul.model.User;

public interface FriendSystemService {

	void saveFriendRelation(FriendSystem friendSystem);

	void updateFriendRelation(FriendSystem friendSystem);

	void deleteFriendRelation(int userId1, int userId2);

	List<FriendSystem> findAllFriendsById(int userId);

	boolean friendOrNot(int userId1, int userId2);

	boolean haveAnyRelation(int userId1, int userId2);

	FriendSystem findFriendRelation(int userId1, int userId2);

	List<User> findAllFriendListById(int loginUserId);

	List<User> findAllFriendRequestUserListById(int loginUserId);

	int friendCount(int loginUserId);

	List<FriendSystem> findAllFriendRequests(int loginUserId);

	int totalFriendRequest(int loginUserId);
}
