package com.webAPI.training.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 @Autowired
	    private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) 
	  throws Exception { 
	    auth.parentAuthenticationManager(authenticationManager).inMemoryAuthentication()
	      .withUser("baont").password("baonguyen").roles("USER");
	    	
		}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.requestMatchers()
        .antMatchers("/login", "/oauth/authorize")
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .permitAll();
	}
	
}
	