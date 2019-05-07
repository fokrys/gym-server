package com.fokrys.gym.service;

import com.fokrys.gym.dto.RoleDto;
import com.fokrys.gym.entity.Role;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;

public interface RoleService {
	Role save(RoleDto role) throws AlreadyExistException;
	Iterable<Role> findAll() throws NotExistException;
	Role findOne(RoleDto roleDto) throws NotExistException;
	Role delete(RoleDto roleDto) throws NotExistException;
	Role edit(RoleDto roleDto) throws NotExistException;
}
