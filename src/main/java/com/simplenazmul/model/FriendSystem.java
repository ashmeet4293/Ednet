package com.simplenazmul.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="friends_table")
public class FriendSystem {

	@Column(name="friends_relation_id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int friendsRelationId;
	
	@Column(name="user_id_1")
	private int userIdOne;
	
	@Column(name="user_id_2")
	private int userIdTwo;
	
	
	// pending = 0, accept=1, declined=2, blocked =3
	@Column(name="status")
	private int status;
	
	@Column(name="last_action_user")
	private int lastActionUser;

	public int getFriendsRelationId() {
		return friendsRelationId;
	}

	public void setFriendsRelationId(int friendsRelationId) {
		this.friendsRelationId = friendsRelationId;
	}

	public int getUserIdOne() {
		return userIdOne;
	}

	public void setUserIdOne(int userIdOne) {
		this.userIdOne = userIdOne;
	}

	public int getUserIdTwo() {
		return userIdTwo;
	}

	public void setUserIdTwo(int userIdTwo) {
		this.userIdTwo = userIdTwo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLastActionUser() {
		return lastActionUser;
	}

	public void setLastActionUser(int lastActionUser) {
		this.lastActionUser = lastActionUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + friendsRelationId;
		result = prime * result + lastActionUser;
		result = prime * result + status;
		result = prime * result + userIdOne;
		result = prime * result + userIdTwo;
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
		FriendSystem other = (FriendSystem) obj;
		if (friendsRelationId != other.friendsRelationId)
			return false;
		if (lastActionUser != other.lastActionUser)
			return false;
		if (status != other.status)
			return false;
		if (userIdOne != other.userIdOne)
			return false;
		if (userIdTwo != other.userIdTwo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FriendSystem [friendsRelationId=" + friendsRelationId + ", userIdOne=" + userIdOne + ", userIdTwo="
				+ userIdTwo + ", status=" + status + ", lastActionUser=" + lastActionUser + "]";
	}
	
	
	
}
