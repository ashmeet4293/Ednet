package com.simplenazmul.importantclass;

import java.sql.Timestamp;

public interface EdnetPersistentTokenRepository2 {
	
	void createNewToken(EdnetPersistentRememberMeToken2 token);

	void updateToken(String series, String tokenValue, Timestamp lastUsed);

	EdnetPersistentRememberMeToken2 getTokenForSeries(String seriesId);

	void removeUserTokens(String email);
	
	
}
