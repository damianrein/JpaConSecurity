package com.JpaConSecurity.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private AuthenticationProvider authProvider;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public SecurityConfig(@Autowired AuthenticationProvider authProvider,@Autowired JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.authProvider = authProvider;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return	http.csrf(c->c.disable())
		.authorizeHttpRequests(auth->{
			auth.requestMatchers("users","/swagger-ui/**","/v3/api-docs/**","/auth/**").permitAll();
			auth.anyRequest().authenticated();
		})
		.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authProvider)
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfig() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:8080"));
		config.setAllowedMethods(List.of("POST","PUT","GET","DELETE"));
		config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
	}
}
