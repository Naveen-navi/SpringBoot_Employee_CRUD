package com.Employee_CRUD_Spring_Data_JPA.security;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

	@Bean
	public UserDetailsManager userDetails(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests( configurer ->
					configurer
					.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
					.requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
					.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
					.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
					.requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
				);
		//use http basic authentication
		http.httpBasic(Customizer.withDefaults());
		
		//Disable csrf
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}

	/*   This is hard Coded lets make use of JDBC
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails john = User.builder()
				.username("manju")
				.password("{noop}test123")
				.roles("EMPLOYEE")
				.build();
		
		UserDetails mary = User.builder()
				.username("yuva")
				.password("{noop}test123")
				.roles("EMPLOYEE", "MANAGER")
				.build();
		
		UserDetails naveen = User.builder()
				.username("naveen")
				.password("{noop}test123")
				.roles("EMPLOYEE", "MANAGER", "ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(john, mary, naveen);
				
	}
*/
}

