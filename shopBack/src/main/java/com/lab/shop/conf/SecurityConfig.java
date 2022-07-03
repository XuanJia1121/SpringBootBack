package com.lab.shop.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.lab.shop.encode.UserPasswordEncoder;
import com.lab.shop.service.AccessDeniedService;
import com.lab.shop.service.AuthFailService;
import com.lab.shop.service.AuthService;
import com.lab.shop.service.AuthSuccessService;
import com.lab.shop.service.GoogleLoginFailService;
import com.lab.shop.service.GoogleLoginSuccessService;
import com.lab.shop.service.LogoutService;
import com.lab.shop.service.LogoutSucService;

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
	private LogoutSucService logoutSucService;
	@Autowired
	private AuthService authService;
	@Autowired
	private LogoutService logoutService;
	/*
	 * Exception Handle
	 */
	@Autowired
	private AccessDeniedService accessDeniedService;
	
	/*
	 * OAuth2 
	 */
	@Autowired
	private GoogleLoginFailService googleLoginFailService;
	@Autowired
	private GoogleLoginSuccessService googleLoginSuccessService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
	         .authorizeRequests()
	         .antMatchers(HttpMethod.POST,"/noAuth/**").permitAll()
	         .antMatchers(HttpMethod.GET,"/auth/googinLogin").permitAll()
	         .antMatchers(HttpMethod.GET,"/test/**").permitAll()
	         .anyRequest().authenticated()
	         .and().cors()
	         .and().csrf().disable();
		 
	     http
	         .formLogin()
         	 .loginProcessingUrl("/auth/login.action")
	         .usernameParameter("username")
	         .passwordParameter("password")
	         .successHandler(authSuccessService)
	         .failureHandler(authFailService);
	     
	     http
	     	 .logout()
	         .logoutUrl("/auth/logout.action")
	         .addLogoutHandler(logoutService)
	         .logoutSuccessHandler(logoutSucService);
	     
	     http
	         .oauth2Login()
	         .successHandler(googleLoginSuccessService)
	         .failureHandler(googleLoginFailService)
	         .defaultSuccessUrl("http://localhost:8080/");
	     
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
