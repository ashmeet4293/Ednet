package com.simplenazmul.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "like_upost_comments")
public class LikeUpostComments {
	
	@Column(name = "like_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeId;

	@Column(name = "upost_comments_id", nullable = false)
	private int upostCommentsId;

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

	public int getUpostCommentsId() {
		return upostCommentsId;
	}

	public void setUpostCommentsId(int upostCommentsId) {
		this.upostCommentsId = upostCommentsId;
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
		result = prime * result + upostCommentsId;
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
		LikeUpostComments other = (LikeUpostComments) obj;
		if (dateLiked == null) {
			if (other.dateLiked != null)
				return false;
		} else if (!dateLiked.equals(other.dateLiked))
			return false;
		if (upostCommentsId != other.upostCommentsId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}


	
	
}
