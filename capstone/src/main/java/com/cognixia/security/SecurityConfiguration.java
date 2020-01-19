package com.cognixia.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;

	// FETCH AUTHENTICATION DETAILS FROM DATABASE
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {				
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT user_username, user_password, user_enabled FROM users WHERE user_username = ?;")
			.authoritiesByUsernameQuery("SELECT user_username, authority FROM authorities WHERE user_username = ?;");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// RESOURCE CONTROL
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// AUTHORIZATION FOR URLS/URIS
		http.authorizeRequests()
		.antMatchers("/","/login", "/webjars/**")
			.permitAll()
		.antMatchers("/*todo*", "/*movie*")		
			.hasAnyRole("ADMIN", "USER")
		.antMatchers("/**")
			.hasAnyRole("ADMIN")
		.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/", true) 			
			.failureUrl("/login?error=true") 		
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/login?logout=true")
			.invalidateHttpSession(true)
			.permitAll()
		.and()
			.exceptionHandling()
			.accessDeniedPage("/forbidden")
		.and()
			.csrf()
			.disable();	
	}	
}


