package com.simplenazmul.service;

import com.simplenazmul.model.UserPicComments;

public interface UserPicCommentsService {
	
	void save(UserPicComments userPicComments);

	void updateUserPicComments(UserPicComments userPicComments);

	void delete(int upicCommentId);
}
