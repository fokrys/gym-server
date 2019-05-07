package com.fokrys.gym.security;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fokrys.gym.dto.TokenDto;
import com.fokrys.gym.model.ApplicationUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private ApplicationUser user;

	@Autowired
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			ApplicationUser user = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);
			this.user = user;
			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		List<String> rolesList = new LinkedList<String>();
		Iterator<? extends GrantedAuthority> iterator = authResult.getAuthorities().iterator();
		while(iterator.hasNext()) {
			rolesList.add(iterator.next().toString());
		}	
		
		ObjectMapper mapper = new ObjectMapper();
		String token = Jwts.builder().setIssuer(rolesList.get(0))
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
				.setSubject(((User) authResult.getPrincipal()).getUsername()).compact();
		response.getWriter().write(mapper.writeValueAsString(new TokenDto(token, "cos", "clos")));
		
		// TOKEN DTO !!!
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		response.addHeader("username", "siemaneczko");
		System.out.println("response");
		System.out.println(response.getHeader("username"));
		System.out.println(response.getHeader("token"));
	}

}
