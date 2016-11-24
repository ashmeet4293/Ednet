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



@Entity
@Table(name="USER_PICTURE_TABLE")
public class UserPic {

	@Column(name="picture_id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pictureId;
	
	@Column(name="picture_caption")
	private String pictureCaption;
	
	@Column(name="picture_created_timestamp", nullable=false)
	private Timestamp pictureCreatedTimestamp;
	
	@Column(name="picture_large_link")
	private String pictureLargeLink;
	
	@Column(name="picture_medium_link")
	private String pictureMediumLink;
	
	@Column(name="picture_small_link")
	private String pictureSmallLink;
	
	//Unidirectional
	@ManyToOne(optional=false)
	@JoinColumn(name="ednet_user_id")
	private User user;
	
	//Unidirectional
	@ManyToOne(optional=false)
	@JoinColumn(name="album_id")
	private UserPicAlbum album;
	
	@Column(name="privacy_type")
	private String privacy;

	public int getPictureId() {
		return pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	public String getPictureCaption() {
		return pictureCaption;
	}

	public void setPictureCaption(String pictureCaption) {
		this.pictureCaption = pictureCaption;
	}

	public Timestamp getPictureCreatedTimestamp() {
		return pictureCreatedTimestamp;
	}

	public void setPictureCreatedTimestamp(Timestamp pictureCreatedTimestamp) {
		this.pictureCreatedTimestamp = pictureCreatedTimestamp;
	}

	public String getPictureLargeLink() {
		return pictureLargeLink;
	}

	public void setPictureLargeLink(String pictureLargeLink) {
		this.pictureLargeLink = pictureLargeLink;
	}

	public String getPictureMediumLink() {
		return pictureMediumLink;
	}

	public void setPictureMediumLink(String pictureMediumLink) {
		this.pictureMediumLink = pictureMediumLink;
	}

	public String getPictureSmallLink() {
		return pictureSmallLink;
	}

	public void setPictureSmallLink(String pictureSmallLink) {
		this.pictureSmallLink = pictureSmallLink;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserPicAlbum getAlbum() {
		return album;
	}

	public void setAlbum(UserPicAlbum album) {
		this.album = album;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pictureCreatedTimestamp == null) ? 0 : pictureCreatedTimestamp.hashCode());
		result = prime * result + pictureId;
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
		UserPic other = (UserPic) obj;
		if (pictureCreatedTimestamp == null) {
			if (other.pictureCreatedTimestamp != null)
				return false;
		} else if (!pictureCreatedTimestamp.equals(other.pictureCreatedTimestamp))
			return false;
		if (pictureId != other.pictureId)
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
		return "UserPic [pictureId=" + pictureId + ", pictureCaption=" + pictureCaption + ", pictureCreatedTimestamp="
				+ pictureCreatedTimestamp + ", pictureLargeLink=" + pictureLargeLink + ", pictureMediumLink="
				+ pictureMediumLink + ", pictureSmallLink=" + pictureSmallLink + ", user=" + user + ", album=" + album
				+ ", privacy=" + privacy + "]";
	}

	

	
	
	
}
