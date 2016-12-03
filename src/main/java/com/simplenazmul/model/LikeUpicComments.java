package com.simplenazmul.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "like_upic_comments")
public class LikeUpicComments {
	
	@Column(name = "like_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeId;

	@Column(name = "upic_comments_id", nullable = false)
	private int upicCommentsId;

	@Column(name = "ednet_user_id", nullable = false)
	private int userId;

	@Column(name = "like_value")
	private int likeValue;

	@Column(name = "date_liked", nullable = false)
	private Timestamp dateLiked;

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public int getUpicCommentsId() {
		return upicCommentsId;
	}

	public void setUpicCommentsId(int upicCommentsId) {
		this.upicCommentsId = upicCommentsId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLikeValue() {
		return likeValue;
	}

	public void setLikeValue(int likeValue) {
		this.likeValue = likeValue;
	}

	public Timestamp getDateLiked() {
		return dateLiked;
	}

	public void setDateLiked(Timestamp dateLiked) {
		this.dateLiked = dateLiked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + likeId;
		result = prime * result + upicCommentsId;
		result = prime * result + userId;
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
		LikeUpicComments other = (LikeUpicComments) obj;
		if (likeId != other.likeId)
			return false;
		if (upicCommentsId != other.upicCommentsId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LikeUpicComments [likeId=" + likeId + ", upicCommentsId=" + upicCommentsId + ", userId=" + userId
				+ ", likeValue=" + likeValue + ", dateLiked=" + dateLiked + "]";
	}
	
	
}
