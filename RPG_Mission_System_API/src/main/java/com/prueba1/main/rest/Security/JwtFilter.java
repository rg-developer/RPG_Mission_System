package com.prueba1.main.rest.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.prueba1.main.rest.Services.CustomUserDetailsService;

@Component
public class JwtFilter  extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain)
	        throws ServletException, IOException {	
		String path = request.getRequestURI();
		
		if (path.startsWith("/auth/") ){
	        filterChain.doFilter(request, response);
	        return;
	    }
		

	    final String authHeader = request.getHeader("Authorization");

	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        filterChain.doFilter(request, response);
	        return;
	    }
	    
	    if (authHeader != null && authHeader.startsWith("Bearer ")) {

	        String token = authHeader.substring(7);
	        String username = jwtUtil.getUsername(token);

	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	            if (jwtUtil.validToken(token)) {

	                UsernamePasswordAuthenticationToken auth =
	                        new UsernamePasswordAuthenticationToken(
	                                userDetails, null, userDetails.getAuthorities());

	                SecurityContextHolder.getContext().setAuthentication(auth);
	            }
	        }
	    }

	    filterChain.doFilter(request, response);
	}

}
