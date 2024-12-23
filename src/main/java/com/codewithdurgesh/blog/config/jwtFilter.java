package com.codewithdurgesh.blog.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.codewithdurgesh.blog.Security.CustomUserDetailService;
import com.codewithdurgesh.blog.Security.JwtTokenHelper;

@Component
public class jwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenHelper JwtTokenHelper;
	
	@Autowired
	private CustomUserDetailService detailsService;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    
	    String authHeader = request.getHeader("Authorization");
	    String token = null;
	    String username = null;
	    UserDetails userDetails = null; // Declare outside of the if statement

	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        token = authHeader.substring(7);
	        username = JwtTokenHelper.extractUserName(token);
	    }

	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        userDetails = detailsService.loadUserByUsername(username); // Load UserDetails here
	    }

	    Boolean validateToken = JwtTokenHelper.validateToken(token, userDetails); // Use userDetails instead
	    if (validateToken) {
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	            userDetails, null, userDetails.getAuthorities()); // Use userDetails here
	        
	        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	        SecurityContextHolder.getContext().setAuthentication(authToken);
	    }

	    filterChain.doFilter(request, response);
	}

	
	
	
	
	
}