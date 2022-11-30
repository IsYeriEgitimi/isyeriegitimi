package com.fu.isyeri.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.fu.isyeri.entryPoint.AuthEntryPoint;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
	
	@Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
			
			
		http.exceptionHandling().authenticationEntryPoint(new AuthEntryPoint());
		
		http
			.authorizeRequests()
				.antMatchers(HttpMethod.POST,"/api/1.0/user/delete").authenticated()
			.and()
			.authorizeRequests().anyRequest().permitAll();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
        
		
	
		
		return http.build();
    }
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
