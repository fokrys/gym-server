package com.fokrys.gym.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fokrys.gym.entity.Role;
import com.fokrys.gym.entity.User;
import com.fokrys.gym.repository.RoleRepository;
import com.fokrys.gym.repository.UserRepository;


@Component
public class CustomUserDetailsService implements UserDetailsService{

	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User domainUser = userRepository.findByUsername(username);
		
		if(domainUser == null)
			throw new UsernameNotFoundException(username);
		List<GrantedAuthority> grantedAuthorities =  AuthorityUtils.createAuthorityList("ROLE_"+domainUser.getRole().getName());
		
		
		return new org.springframework.security.core.userdetails.User(domainUser.getUsername(), domainUser.getPassword(), true, true, true, true, grantedAuthorities);

	}
	
	
	
}
