package com.simplenazmul.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "USER_PICTURE_COMMENTS")
public class UserPicComments {

	@Column(name = "upic_comment_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int upicCommentId;
	
	
	@Column(name="upic_comment_user_id" , nullable = false)
	private String upicCommentUserId;
	
	@Column(name="upic_comment_discription" , nullable = false)
	private String upicCommentDiscription;
	
	@Column(name="upic_comment_created_timestamp", nullable = false)
	private Timestamp commentCreatedTimestamp;
	
	@Transient
	private int likeCount;
	
	@Transient
	private int disLikeCount;
	
	

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getDisLikeCount() {
		return disLikeCount;
	}

	public void setDisLikeCount(int disLikeCount) {
		this.disLikeCount = disLikeCount;
	}

	public int getUpicCommentId() {
		return upicCommentId;
	}

	public void setUpicCommentId(int upicCommentId) {
		this.upicCommentId = upicCommentId;
	}

	public String getUpicCommentUserId() {
		return upicCommentUserId;
	}

	public void setUpicCommentUserId(String upicCommentUserId) {
		this.upicCommentUserId = upicCommentUserId;
	}

	public String getUpicCommentDiscription() {
		return upicCommentDiscription;
	}

	public void setUpicCommentDiscription(String upicCommentDiscription) {
		this.upicCommentDiscription = upicCommentDiscription;
	}

	public Timestamp getCommentCreatedTimestamp() {
		return commentCreatedTimestamp;
	}

	public void setCommentCreatedTimestamp(Timestamp commentCreatedTimestamp) {
		this.commentCreatedTimestamp = commentCreatedTimestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentCreatedTimestamp == null) ? 0 : commentCreatedTimestamp.hashCode());
		result = prime * result + upicCommentId;
		result = prime * result + ((upicCommentUserId == null) ? 0 : upicCommentUserId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPicComments other = (UserPicComments) obj;
		if (commentCreatedTimestamp == null) {
			if (other.commentCreatedTimestamp != null)
				return false;
		} else if (!commentCreatedTimestamp.equals(other.commentCreatedTimestamp))
			return false;
		if (upicCommentId != other.upicCommentId)
			return false;
		if (upicCommentUserId == null) {
			if (other.upicCommentUserId != null)
				return false;
		} else if (!upicCommentUserId.equals(other.upicCommentUserId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserPicComments [upicCommentId=" + upicCommentId + ", upicCommentUserId=" + upicCommentUserId
				+ ", upicCommentDiscription=" + upicCommentDiscription + ", commentCreatedTimestamp="
				+ commentCreatedTimestamp + ", likeCount=" + likeCount + ", disLikeCount=" + disLikeCount + "]";
	}


	
	
}
