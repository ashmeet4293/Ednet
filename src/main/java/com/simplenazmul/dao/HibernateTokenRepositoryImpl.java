//package com.simplenazmul.dao;
//import java.sql.Timestamp;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Restrictions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.simplenazmul.importantclass.EdnetPersistentRememberMeToken;
//import com.simplenazmul.importantclass.EdnetPersistentTokenRepository;
//import com.simplenazmul.model.UserPersistentLogin;
//
//@Repository("tokenRepositoryDao")
//@Transactional
//public class HibernateTokenRepositoryImpl extends AbstractDao<String, UserPersistentLogin>
//        implements EdnetPersistentTokenRepository {
// 
//    static final Logger logger = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);
// 
//    @Override
//    public void createNewToken(EdnetPersistentRememberMeToken token) {
//        logger.info("Creating Token for user :" );
//        UserPersistentLogin persistentLogin = new UserPersistentLogin();
//        persistentLogin.setEmail(token.getEmail());
//        persistentLogin.setSeries(token.getSeries());
//        persistentLogin.setToken(token.getTokenValue());
//        persistentLogin.setLastUsed(token.getLastUsed());
//        persist(persistentLogin);
// 
//    }
// 
//    @Override
//    public EdnetPersistentRememberMeToken getTokenForSeries(String seriesId) {
//        logger.info("Fetch Token if any for seriesId : {}", seriesId);
//        try {
//            Criteria crit = createEntityCriteria();
//            crit.add(Restrictions.eq("series", seriesId));
//            UserPersistentLogin persistentLogin = (UserPersistentLogin) crit.uniqueResult();
// 
//            return new EdnetPersistentRememberMeToken(persistentLogin.getEmail(), persistentLogin.getSeries(),
//                    persistentLogin.getToken(), persistentLogin.getLastUsed());
//        } catch (Exception e) {
//            logger.info("Token not found...");
//            return null;
//        }
//    }
// 
//    @Override
//    public void removeUserTokens(String username) {
//        logger.info("Removing Token if any for user : {}", username);
//        Criteria crit = createEntityCriteria();
//        crit.add(Restrictions.eq("username", username));
//        UserPersistentLogin persistentLogin = (UserPersistentLogin) crit.uniqueResult();
//        if (persistentLogin != null) {
//            logger.info("rememberMe was selected");
//            delete(persistentLogin);
//        }
// 
//    }
// 
//    @Override
//    public void updateToken(String seriesId, String tokenValue, Timestamp lastUsed) {
//        logger.info("Updating Token for seriesId : {}", seriesId);
//        UserPersistentLogin persistentLogin = getByKey(seriesId);
//        persistentLogin.setToken(tokenValue);
//        persistentLogin.setLastUsed(lastUsed);
//        update(persistentLogin);
//    }
//
//
// 
//}