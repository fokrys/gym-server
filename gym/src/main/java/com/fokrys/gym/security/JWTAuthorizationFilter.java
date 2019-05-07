package com.fokrys.gym.security;

import java.io.IOException;
import java.security.SignatureException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fokrys.gym.model.ApplicationUser;
import com.fokrys.gym.service.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private final CustomUserDetailsService customUserDetailsService;

	@Autowired
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
			CustomUserDetailsService customUserDetailsService) {
		super(authenticationManager);
		this.customUserDetailsService = customUserDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken usernamePasswordAuthentication = null;
		usernamePasswordAuthentication = getAuthenticationToken(request);
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if (token == null)
			return null;
		Jws<Claims> parseClaimsJws = null;
		try {
			parseClaimsJws = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
		} catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | ExpiredJwtException e) {

		}
		if (parseClaimsJws == null)
			return null;
		String username = parseClaimsJws.getBody().getSubject();
		
		if (username == null)
			return null;
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
		ApplicationUser accountCredentials = new ApplicationUser(userDetails.getUsername(), userDetails.getPassword());
		return username != null
				? new UsernamePasswordAuthenticationToken(accountCredentials, null, userDetails.getAuthorities())
				: null;
	}
	
	
}
