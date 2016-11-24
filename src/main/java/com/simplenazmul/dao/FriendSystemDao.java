package com.simplenazmul.dao;

import java.util.List;

import com.simplenazmul.model.FriendSystem;

public interface FriendSystemDao {

	void saveFriendRelation(FriendSystem friendSystem);

	void updateFriendRelation(FriendSystem friendSystem);

	void deleteFriendRelation(int userId1, int userId2);

	List<FriendSystem> findAllFriendsById(int userId);

	boolean friendOrNot(int userId1, int userId2);

	boolean haveAnyRelation(int userId1, int userId2);

	FriendSystem findFriendRelation(int userId1, int userId2);

	List<FriendSystem> findAllFriendRequests(int loginUserId);
}
