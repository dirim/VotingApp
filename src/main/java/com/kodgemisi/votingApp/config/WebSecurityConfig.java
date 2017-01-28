package com.kodgemisi.votingApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by ozge on 18.08.2016.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/css/**", "/fonts/**", "/js/**", "/templates/**").permitAll()
				.antMatchers("/users/register").permitAll()
				.antMatchers("/users/login").permitAll()
				.antMatchers("/console/**").permitAll()
				.anyRequest()
				.fullyAuthenticated()
				.and()
				.formLogin()
				.loginPage("/users/login")
				.failureUrl("/users/login?error")
				.defaultSuccessUrl("/questions")
				.usernameParameter("email")
				.permitAll()
				.and()
				.logout()
				.deleteCookies("JSESSIONID")
				.logoutUrl("/users/logout")
				.logoutSuccessUrl("/")
				.permitAll();
		http.headers().frameOptions().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
}
