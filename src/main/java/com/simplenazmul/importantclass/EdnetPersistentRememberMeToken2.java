package com.simplenazmul.importantclass;

import java.sql.Timestamp;

public class EdnetPersistentRememberMeToken2 {
	private final String email;
	private final String series;
	private final String tokenValue;
	private final Timestamp lastUsed;
	
	


	public EdnetPersistentRememberMeToken2( String email, String series, String tokenValue,
			Timestamp lastUsed) {
		//this.persistentId = persistentId;
		this.email = email;
		this.series = series;
		this.tokenValue = tokenValue;
		this.lastUsed = lastUsed;
	}


	public String getEmail() {
		return email;
	}




	public String getSeries() {
		return series;
	}




	public String getTokenValue() {
		return tokenValue;
	}




	public Timestamp getLastUsed() {
		return lastUsed;
	}

	
	

	
}
