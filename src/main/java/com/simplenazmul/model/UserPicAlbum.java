package com.simplenazmul.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="USER_PIC_ALBUM")
public class UserPicAlbum {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="album_id")
	private int albumId;
	
	@NotNull
	@Column(name="album_caption")
	private String albumCaption;
	
	@Column(name="album_cover_pic_id")
	private int albumCoverPhotoId;
	
	@Column(name="album_created_timestamp")
	private Timestamp albumCreatedTime;
	
	//Unidirectional
	@ManyToOne(optional=false)
	@JoinColumn(name="ednet_user_id")
	@NotNull
	private User user;

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getAlbumCaption() {
		return albumCaption;
	}

	public void setAlbumCaption(String albumCaption) {
		this.albumCaption = albumCaption;
	}

	public int getAlbumCoverPhotoId() {
		return albumCoverPhotoId;
	}

	public void setAlbumCoverPhotoId(int albumCoverPhotoId) {
		this.albumCoverPhotoId = albumCoverPhotoId;
	}

	public Timestamp getAlbumCreatedTime() {
		return albumCreatedTime;
	}

	public void setAlbumCreatedTime(Timestamp albumCreatedTime) {
		this.albumCreatedTime = albumCreatedTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumCaption == null) ? 0 : albumCaption.hashCode());
		result = prime * result + albumCoverPhotoId;
		result = prime * result + ((albumCreatedTime == null) ? 0 : albumCreatedTime.hashCode());
		result = prime * result + albumId;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserPicAlbum other = (UserPicAlbum) obj;
		if (albumCaption == null) {
			if (other.albumCaption != null)
				return false;
		} else if (!albumCaption.equals(other.albumCaption))
			return false;
		if (albumCoverPhotoId != other.albumCoverPhotoId)
			return false;
		if (albumCreatedTime == null) {
			if (other.albumCreatedTime != null)
				return false;
		} else if (!albumCreatedTime.equals(other.albumCreatedTime))
			return false;
		if (albumId != other.albumId)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserPictureAlbum [albumId=" + albumId + ", albumCaption=" + albumCaption + ", albumCoverPhotoId="
				+ albumCoverPhotoId + ", albumCreatedTime=" + albumCreatedTime + ", user=" + user + "]";
	}
	
	
	
	
}
