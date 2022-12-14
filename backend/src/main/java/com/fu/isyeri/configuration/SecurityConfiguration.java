package com.fu.isyeri.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
				.antMatchers(HttpMethod.POST,"/api/1.0/auth/logout").authenticated()
				.antMatchers(HttpMethod.POST,"/api/1.0/user/add").authenticated()
				.antMatchers(HttpMethod.DELETE,"/api/1.0/user/delete/{username}").authenticated()
				.antMatchers(HttpMethod.PUT,"/api/1.0/user/update/{username}").authenticated()
				.antMatchers(HttpMethod.POST,"/api/1.0/role/add").authenticated()
				.antMatchers(HttpMethod.DELETE,"/api/1.0/role/delete").authenticated()
				.antMatchers(HttpMethod.PUT,"/api/1.0/role/update/{roleId}").authenticated()
				.antMatchers(HttpMethod.POST,"/api/1.0/comment/add").authenticated()
				.antMatchers(HttpMethod.POST,"/api/1.0/company/add").authenticated()
				.antMatchers(HttpMethod.POST,"/api/1.0/company/delete/{id}").authenticated()
				.antMatchers(HttpMethod.POST,"/api/1.0/company/update/{id}").authenticated()
			.and()
			.authorizeRequests().anyRequest().permitAll();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
        
		
		http.addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class);
	
		
		return http.build();
    }
	
	@Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	TokenFilter tokenFilter() {
		return new TokenFilter();
	}
	
}
