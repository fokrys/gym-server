package com.fokrys.gym.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fokrys.gym.dto.RoleDto;
import com.fokrys.gym.entity.Role;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.repository.RoleRepository;
import com.fokrys.gym.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	private final RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}
	
	@Autowired
	public void createDefaultRoles() {
		Role userRole = new Role(Long.valueOf(1), "USER");
		Role adminRole = new Role(Long.valueOf(2), "ADMIN");
		
		roleRepository.save(userRole);
		roleRepository.save(adminRole);
	}

	@Override
	public Role save(RoleDto roleDto) throws AlreadyExistException {
		if (exist(roleDto)) {
			throw new AlreadyExistException
			("There is an role with that name: "+roleDto.getName());
		}
		
		Role actualRole = new Role(roleDto.getName());
		return roleRepository.save(actualRole);
	}
	
	@Override
	public Iterable<Role> findAll() throws NotExistException {
		Iterable<Role> roles = roleRepository.findAll();
		if (roles == null) {
			throw new NotExistException();
		}
		return roles;
	}
	
	@Override
	public Role findOne(RoleDto roleDto) throws NotExistException {
		return roleRepository.findByName(roleDto.getName());
	}

	@Override
	public Role delete(RoleDto roleDto) throws NotExistException {
		if (!exist(roleDto)) {
			throw new NotExistException();
		} 

		Role role = roleRepository.findByName(roleDto.getName());
		roleRepository.delete(role);
		return role;
	}
	
	@Override
	public Role edit(RoleDto roleDto) throws NotExistException {
		Optional<Role> role = roleRepository.findById(roleDto.getId());
		if (role == null) {
			throw new NotExistException();
		} 
		
		
		role.get().setName(roleDto.getName());
		return roleRepository.save(role.get());
	}

	private boolean exist(RoleDto role) {
		Role domainRole = roleRepository.findByName(role.getName());
		if(domainRole == null) {
			return false;
		} else {
			return true;
		}
	}

}
