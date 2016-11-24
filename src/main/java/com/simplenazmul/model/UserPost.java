package com.simplenazmul.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "USER_POST")
public class UserPost {

	@Column(name = "user_post_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userPostId;

	@Column(name = "user_post_discription")
	private String userPostDiscription;

	@Column(name = "user_post_created_timestamp", nullable = false)
	private Timestamp userPostCreatedTime;

	// Unidirectional
	@ManyToOne(optional = false)
	@JoinColumn(name = "ednet_user_id")
	private User user;

	@Column(name = "user_post_privacy", nullable = false)
	private String privacy;

	@Transient
	private int likeCount;

	@Transient
	private int disLikeCount;

	@OneToMany
	@JoinColumn(name = "user_post_id")
	List<LikeUserPost> likeUserPosts = new ArrayList<>();

	@Transient
	private boolean loginUserLikeOrNot;

	@Transient
	private String timeAgo;

	public String getTimeAgo() {
		return timeAgo;
	}

	public void setTimeAgo(String timeAgo) {
		this.timeAgo = timeAgo;
	}

	public boolean isLoginUserLikeOrNot() {
		return loginUserLikeOrNot;
	}

	public void setLoginUserLikeOrNot(boolean loginUserLikeOrNot) {
		this.loginUserLikeOrNot = loginUserLikeOrNot;
	}

	public int getUserPostId() {
		return userPostId;
	}

	public void setUserPostId(int userPostId) {
		this.userPostId = userPostId;
	}

	public String getUserPostDiscription() {
		return userPostDiscription;
	}

	public void setUserPostDiscription(String userPostDiscription) {
		this.userPostDiscription = userPostDiscription;
	}

	public Timestamp getUserPostCreatedTime() {
		return userPostCreatedTime;
	}

	public void setUserPostCreatedTime(Timestamp userPostCreatedTime) {
		this.userPostCreatedTime = userPostCreatedTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
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

	public List<LikeUserPost> getLikeUserPosts() {
		return likeUserPosts;
	}

	public void setLikeUserPosts(List<LikeUserPost> likeUserPosts) {
		this.likeUserPosts = likeUserPosts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userPostCreatedTime == null) ? 0 : userPostCreatedTime.hashCode());
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
		UserPost other = (UserPost) obj;
		if (userPostCreatedTime == null) {
			if (other.userPostCreatedTime != null)
				return false;
		} else if (!userPostCreatedTime.equals(other.userPostCreatedTime))
			return false;
		if (userPostId != other.userPostId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserPost [userPostId=" + userPostId + ", userPostDiscription=" + userPostDiscription
				+ ", userPostCreatedTime=" + userPostCreatedTime + ", user=" + user + ", privacy=" + privacy
				+ ", likeCount=" + likeCount + ", disLikeCount=" + disLikeCount + ", likeUserPosts=" + likeUserPosts
				+ ", loginUserLikeOrNot=" + loginUserLikeOrNot + ", timeAgo=" + timeAgo + "]";
	}

}
