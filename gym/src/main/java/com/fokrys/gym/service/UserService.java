package com.fokrys.gym.service;

import com.fokrys.gym.dto.UserDto;
import com.fokrys.gym.entity.User;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.LoginException;
import com.fokrys.gym.exceptions.NotExistException;

public interface UserService {
	
	User save(UserDto userDto) throws AlreadyExistException;
	Iterable<UserDto> findAll() throws NotExistException;
	Iterable<UserDto> findAllU();
	User findOne(UserDto userDto) throws NotExistException;
	User findOneById(Long id) throws NotExistException;
	String findOneByUsername(String username);
	UserDto findUserByUsername(String username);
	User delete(UserDto userDto) throws NotExistException;
	User edit(UserDto userDto) throws NotExistException, AlreadyExistException, LoginException;
	Boolean exist(String username);
	String findUsernameById(Long id);
	Long findIdByUsername(String username);
	
}
