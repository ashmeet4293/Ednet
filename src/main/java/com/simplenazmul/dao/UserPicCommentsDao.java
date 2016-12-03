package com.simplenazmul.dao;

import com.simplenazmul.model.UserPicComments;

public interface UserPicCommentsDao {

	void save(UserPicComments userPicComments);

	void updateUserPicComments(UserPicComments userPicComments);

	void delete(int upicCommentId);

}
