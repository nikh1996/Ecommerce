package com.portal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.
	configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.
	configuration.WebSecurityConfigurerAdapter;

	@Configuration
	@EnableWebSecurity
	public class SecurityConfig extends WebSecurityConfigurerAdapter {
		
		protected void configure(HttpSecurity http) throws Exception {
			http
	            .authorizeRequests()
		            .antMatchers("/user*").hasRole("USER")
		            .antMatchers("/admin*").hasRole("ADMIN")
		            .antMatchers("/all.*").permitAll()
	                .and()
	            .formLogin()
	                .loginPage("/all_login")
	                .loginProcessingUrl("/j_spring_security_check")
	                .defaultSuccessUrl("/all_landing")
	                .failureUrl("/all_login")
	                .and()
	            .logout()
	                .logoutUrl("/j_spring_security_logout")
	                .logoutSuccessUrl("/all_login")
	                .and()
	            .csrf()
	            	.disable();
			}
	
	
		@Override
		protected void configure(AuthenticationManagerBuilder auth)
		throws Exception {
		System.out.println("In configure AuthenticationManagerBuilder of Security Config");
		auth.parentAuthenticationManager(authenticator());
	    super.configure(auth);
	
		}
		
		 @Bean
		  public AuthenticationManager authenticator() {
		    return new CustomAuthenticationManager();
		  }
		
	}
