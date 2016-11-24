package com.simplenazmul.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());

	}

	@Autowired
	DataSource dataSource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**", "/resources/**", "/webjars/**")
				.permitAll();

		http.authorizeRequests().antMatchers("/registraion").permitAll().antMatchers("/login").permitAll()
				.antMatchers("/", "home").access("hasRole('ROLE_USER')").and().csrf().disable().formLogin()
				.loginPage("/login").loginProcessingUrl("/login-process").failureUrl("/login?error")
				.usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/", true).and()
				.rememberMe().rememberMeServices(rememberMeServices()).key("ednetRememberMe").and().logout()
				.logoutUrl("/logout").logoutSuccessUrl("/login?logout");

		http.exceptionHandling().accessDeniedPage("/error");
		// http.sessionManagement().invalidSessionUrl("/login");
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}

	@Bean
	public AbstractRememberMeServices rememberMeServices() {
		PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(
				"ednetRememberMe", userDetailsService, persistentTokenRepository());
		rememberMeServices.setAlwaysRemember(true);
		rememberMeServices.setCookieName("ednet-remember-me");
		rememberMeServices.setTokenValiditySeconds(604800);
		return rememberMeServices;
	}

	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
		SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("targetUrl");
		return auth;
	}

}
