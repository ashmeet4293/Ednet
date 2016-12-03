package com.simplenazmul.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.simplenazmul.customannotation.EmailExistsCheck;
import com.simplenazmul.customannotation.UserNameExistCheck;
import com.simplenazmul.customannotation.ValidEmail;

@Entity
@Table(name = "EDNET_USER")
public class User {

	public interface WithoutPasswordView {
	};

	public interface WithPasswordView extends WithoutPasswordView {
	};

	@Column(name = "ednet_user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ednetUserId;

	@Column(name = "ednet_username", unique = true, nullable = false)
	@UserNameExistCheck
	@Size(min = 3, max = 50)
	private String userName;

	@Column(name = "ednet_user_password", nullable = false)
	@Size(min = 6, max = 100)
	private String password;

	@Column(name = "ednet_user_first_name", nullable = false)

	@Size(min = 2, max = 30)
	private String firstName;

	@Column(name = "ednet_user_last_name", nullable = false)
	@JsonView(WithoutPasswordView.class)
	@Size(min = 2, max = 30)
	private String lastName;

	@Column(name = "ednet_user_email", unique = true, nullable = false)
	@ValidEmail
	@EmailExistsCheck
	@Size(max = 100)
	private String email;

	@Column(name = "ednet_user_state", nullable = false)
	private String state = State.ACTIVE.getState();

	@Column(name = "ednet_user_gender", nullable = false)
	private String gender;

	@Column(name = "ednet_user_point", nullable = false)
	private int userPoint;

	@Column(name = "ednet_user_account_created_time", nullable = false)
	private Timestamp accountCreatedTime;

	@Column(name = "ednet_user_short_discription")
	@Size(max = 200)
	private String shortDiscription;

	@Column(name = "ednet_user_total_profile_view", nullable = false)
	private int ednetUserTotalProfileView;

	@Column(name = "ednet_user_favourite_quote")
	@Size(max = 200)
	private String favQuote;

	@Column(name = "ednet_user_level")
	private int userLevel;

	@Column(name = "ednet_user_profile_pic_link")
	private String profilePicLink;

	// @Column(name="ednet_user_dob", nullable=false)
	// private java.time.LocalDate birthdayDate;



	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_AND_PROFILE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	public String getProfilePicLink() {
		return profilePicLink;
	}

	public void setProfilePicLink(String profilePicLink) {
		this.profilePicLink = profilePicLink;
	}

	@JsonView(WithoutPasswordView.class)
	public int getEdnetUserId() {
		return ednetUserId;
	}

	public void setEdnetUserId(int ednetUserId) {
		this.ednetUserId = ednetUserId;
	}

	@JsonView(WithoutPasswordView.class)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonView(WithPasswordView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonView(WithoutPasswordView.class)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonView(WithoutPasswordView.class)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonView(WithoutPasswordView.class)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonView(WithoutPasswordView.class)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@JsonView(WithoutPasswordView.class)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonView(WithoutPasswordView.class)
	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	@JsonView(WithoutPasswordView.class)
	public Timestamp getAccountCreatedTime() {
		return accountCreatedTime;
	}

	public void setAccountCreatedTime(Timestamp accountCreatedTime) {
		this.accountCreatedTime = accountCreatedTime;
	}

	@JsonView(WithoutPasswordView.class)
	public String getShortDiscription() {
		return shortDiscription;
	}

	public void setShortDiscription(String shortDiscription) {
		this.shortDiscription = shortDiscription;
	}

	@JsonView(WithoutPasswordView.class)
	public int getEdnetUserTotalProfileView() {
		return ednetUserTotalProfileView;
	}

	public void setEdnetUserTotalProfileView(int ednetUserTotalProfileView) {
		this.ednetUserTotalProfileView = ednetUserTotalProfileView;
	}

	@JsonView(WithoutPasswordView.class)
	public String getFavQuote() {
		return favQuote;
	}

	public void setFavQuote(String favQuote) {
		this.favQuote = favQuote;
	}

	@JsonView(WithoutPasswordView.class)
	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	// public LocalDate getBirthdayDate() {
	// return birthdayDate;
	// }
	//
	// public void setBirthdayDate(LocalDate birthdayDate) {
	// this.birthdayDate = birthdayDate;
	// }

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ednetUserId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User other = (User) obj;
		if (ednetUserId != other.ednetUserId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [ednetUserId=" + ednetUserId + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", state=" + state + ", gender=" + gender
				+ ", userPoint=" + userPoint + ", accountCreatedTime=" + accountCreatedTime + ", shortDiscription="
				+ shortDiscription + ", ednetUserTotalProfileView=" + ednetUserTotalProfileView + ", favQuote="
				+ favQuote + ", userLevel=" + userLevel + ", profilePicLink=" + profilePicLink + ", userProfiles="
				+ userProfiles + "]";
	}



}
