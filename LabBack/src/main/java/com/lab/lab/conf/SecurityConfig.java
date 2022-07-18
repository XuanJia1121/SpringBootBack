package com.lab.lab.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.lab.lab.Interceptor.AuthorizationCheckFilter;
import com.lab.lab.encode.UserPasswordEncoder;
import com.lab.lab.service.AccessDeniedService;
import com.lab.lab.service.AuthFailService;
import com.lab.lab.service.AuthSuccessService;
import com.lab.lab.service.SecurityAuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	 * normal login 
	 */
	@Autowired
	private AuthSuccessService authSuccessService;
	@Autowired
	private AuthFailService authFailService;
	@Autowired
	private SecurityAuthService authService;

	/*
	 * Exception Handle
	 */
	@Autowired
	private AccessDeniedService accessDeniedService;
	
	@Autowired
	private AuthorizationCheckFilter authorizationCheckFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
	         .authorizeRequests()
	         .antMatchers("/test/**").permitAll()
	         .anyRequest().authenticated()
	         .and().cors()
	         .and().csrf().disable();
		 
		 http
		 	.sessionManagement()
		 	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	     http
	     	.addFilterBefore(authorizationCheckFilter, BasicAuthenticationFilter.class);
		 
	     http
	         .formLogin()
         	 .loginProcessingUrl("/login")
	         .usernameParameter("username")
	         .passwordParameter("password")
	         .successHandler(authSuccessService)
	         .failureHandler(authFailService);
	     
	     http
         	.exceptionHandling().accessDeniedHandler(accessDeniedService);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(authService)
			.passwordEncoder(new UserPasswordEncoder());
	}
	
}
