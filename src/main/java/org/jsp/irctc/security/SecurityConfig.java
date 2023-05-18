package org.jsp.irctc.security;

import java.util.ArrayList;
import java.util.List;

import org.jsp.irctc.jwt.JwtRequestFilter;
import org.jsp.irctc.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements SecurityFilterChain {

	private final MyUserDetailsService myUserDetailsService;
	private final JwtRequestFilter jwtRequestFilter;

	@Autowired
	public SecurityConfig(MyUserDetailsService myUserDetailsService, JwtRequestFilter jwtRequestFilter) {
		this.myUserDetailsService = myUserDetailsService;
		this.jwtRequestFilter = jwtRequestFilter;
	}


	public FilterChainProxy getFilterChain(ServletRequest request) throws ServletException {
		List<Filter> filters = new ArrayList<>();
		filters.add(jwtRequestFilter);
		return new FilterChainProxy((SecurityFilterChain) filters);
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		return true; // Matches all requests
	}

	// Other security-related methods and configurations

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		List<AuthenticationProvider> providers = new ArrayList<>();
		// Add your authentication providers if needed
		return new ProviderManager(providers);
	}

	@Override
	public List<Filter> getFilters() {
		// TODO Auto-generated method stub
		return null;
	}
}
