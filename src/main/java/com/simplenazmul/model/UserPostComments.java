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
@Table(name = "USER_POST_COMMENTS")
public class UserPostComments {

	@Column(name = "upost_comment_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int upostCommentId;
	
	
	@Column(name="upost_comment_user_id" , nullable = false)
	private String upostCommentUserId;
	
	@Column(name="upost_comment_discription" , nullable = false)
	private String upostCommentDiscription;
	
	@Column(name="upost_comment_created_timestamp", nullable = false)
	private Timestamp commentCreatedTimestamp;
	
	@Transient
	private int likeCount;
	
	@Transient
	private int disLikeCount;

	public int getUpostCommentId() {
		return upostCommentId;
	}

	public void setUpostCommentId(int upostCommentId) {
		this.upostCommentId = upostCommentId;
	}

	public String getUpostCommentUserId() {
		return upostCommentUserId;
	}

	public void setUpostCommentUserId(String upostCommentUserId) {
		this.upostCommentUserId = upostCommentUserId;
	}

	public String getUpostCommentDiscription() {
		return upostCommentDiscription;
	}

	public void setUpostCommentDiscription(String upostCommentDiscription) {
		this.upostCommentDiscription = upostCommentDiscription;
	}

	public Timestamp getCommentCreatedTimestamp() {
		return commentCreatedTimestamp;
	}

	public void setCommentCreatedTimestamp(Timestamp commentCreatedTimestamp) {
		this.commentCreatedTimestamp = commentCreatedTimestamp;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentCreatedTimestamp == null) ? 0 : commentCreatedTimestamp.hashCode());
		result = prime * result + upostCommentId;
		result = prime * result + ((upostCommentUserId == null) ? 0 : upostCommentUserId.hashCode());
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
		UserPostComments other = (UserPostComments) obj;
		if (commentCreatedTimestamp == null) {
			if (other.commentCreatedTimestamp != null)
				return false;
		} else if (!commentCreatedTimestamp.equals(other.commentCreatedTimestamp))
			return false;
		if (upostCommentId != other.upostCommentId)
			return false;
		if (upostCommentUserId == null) {
			if (other.upostCommentUserId != null)
				return false;
		} else if (!upostCommentUserId.equals(other.upostCommentUserId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserPostComments [upostCommentId=" + upostCommentId + ", upostCommentUserId=" + upostCommentUserId
				+ ", upostCommentDiscription=" + upostCommentDiscription + ", commentCreatedTimestamp="
				+ commentCreatedTimestamp + ", likeCount=" + likeCount + ", disLikeCount=" + disLikeCount + "]";
	}
	
	
}
