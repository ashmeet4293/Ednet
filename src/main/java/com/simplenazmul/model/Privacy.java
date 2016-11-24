package com.simplenazmul.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="PRIVACY_TYPE")
public class Privacy {
	
	@Column(name="privacy_type_id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int privacyId;
	
	@Column(name="privacy_type_name")
	private String privacyTypeName;

	public int getPrivacyId() {
		return privacyId;
	}

	public void setPrivacyId(int privacyId) {
		this.privacyId = privacyId;
	}

	public String getPrivacyTypeName() {
		return privacyTypeName;
	}

	public void setPrivacyTypeName(String privacyTypeName) {
		this.privacyTypeName = privacyTypeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + privacyId;
		result = prime * result + ((privacyTypeName == null) ? 0 : privacyTypeName.hashCode());
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
		Privacy other = (Privacy) obj;
		if (privacyId != other.privacyId)
			return false;
		if (privacyTypeName == null) {
			if (other.privacyTypeName != null)
				return false;
		} else if (!privacyTypeName.equals(other.privacyTypeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Privacy [privacyId=" + privacyId + ", privacyTypeName=" + privacyTypeName + "]";
	}
	
	
}
