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
}
