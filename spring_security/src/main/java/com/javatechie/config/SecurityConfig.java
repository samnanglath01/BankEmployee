package com.javatechie.config;

import com.javatechie.filter.JwtAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

	private final JwtAuthFilter jwtAuthFilter;
	
	@Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
	

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserInfoDetailsService();
		
//--------------------------------------------------------------------------		
		
//		UserDetails admin = User.withUsername("Seyha")
//				.password(encoder.encode("pw1"))
//				.roles("ADMIN")
//				.build();
//		UserDetails user = User.withUsername("John")
//				.password(encoder.encode("pw2"))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin, user);

//--------------------------------------------------------------------------		
		
		}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
						.authorizeHttpRequests()
						.requestMatchers("/products/welcome", "/products/new", "/products/authenticate").permitAll()
						.and()
						.authorizeHttpRequests()
						.requestMatchers("/products/**").authenticated()
						.and()
						.sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and()
						.authenticationProvider(authenticationProvider())
						.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
						.build();
		
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider();
	}
	
	
}
