package com.simplenazmul.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "like_userpost")
public class LikeUserPost {

	@Column(name = "like_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeId;

	@Column(name = "user_post_id", nullable = false)
	private int userPostId;

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

	public int getUserPostId() {
		return userPostId;
	}

	public void setUserPostId(int userPostId) {
		this.userPostId = userPostId;
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
		result = prime * result + ((dateLiked == null) ? 0 : dateLiked.hashCode());
		result = prime * result + likeId;
		result = prime * result + likeValue;
		result = prime * result + userId;
		result = prime * result + userPostId;
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
		LikeUserPost other = (LikeUserPost) obj;
		if (dateLiked == null) {
			if (other.dateLiked != null)
				return false;
		} else if (!dateLiked.equals(other.dateLiked))
			return false;
		if (likeId != other.likeId)
			return false;
		if (likeValue != other.likeValue)
			return false;
		if (userId != other.userId)
			return false;
		if (userPostId != other.userPostId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LikeUserPost [likeId=" + likeId + ", userPostId=" + userPostId + ", userId=" + userId + ", likeValue="
				+ likeValue + ", dateLiked=" + dateLiked + "]";
	}

}
