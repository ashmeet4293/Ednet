//package com.simplenazmul.model;
//
//import java.sql.Timestamp;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
// 
//@Entity
//@Table(name="users_persistent_logins")
//public class UserPersistentLogin{
// 
//
//	
//	@Id @Column(name="series", nullable=false)
//    private String series;
// 
//    @Column(name="ednet_user_email", unique=true, nullable=false)
//    private String email;
//     
//    @Column(name="TOKEN", unique=true, nullable=false)
//    private String token;
//     
//    @Column(name="last_used")
//    private Timestamp lastUsed;
//
//
//
//	public String getSeries() {
//		return series;
//	}
//
//	public void setSeries(String series) {
//		this.series = series;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}
//
//	public Timestamp getLastUsed() {
//		return lastUsed;
//	}
//
//	public void setLastUsed(Timestamp lastUsed) {
//		this.lastUsed = lastUsed;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		result = prime * result + ((lastUsed == null) ? 0 : lastUsed.hashCode());
//		result = prime * result + ((series == null) ? 0 : series.hashCode());
//		result = prime * result + ((token == null) ? 0 : token.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		UserPersistentLogin other = (UserPersistentLogin) obj;
//		if (email == null) {
//			if (other.email != null)
//				return false;
//		} else if (!email.equals(other.email))
//			return false;
//		if (lastUsed == null) {
//			if (other.lastUsed != null)
//				return false;
//		} else if (!lastUsed.equals(other.lastUsed))
//			return false;
//		if (series == null) {
//			if (other.series != null)
//				return false;
//		} else if (!series.equals(other.series))
//			return false;
//		if (token == null) {
//			if (other.token != null)
//				return false;
//		} else if (!token.equals(other.token))
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "UserPersistentLogin [series=" + series + ", email=" + email + ", token=" + token + ", lastUsed="
//				+ lastUsed + "]";
//	}
//
//	
//
//    
//}
