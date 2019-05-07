package com.fokrys.gym.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fokrys.gym.dto.PersonalDataDto;
import com.fokrys.gym.dto.RoleDto;
import com.fokrys.gym.dto.UserDto;
import com.fokrys.gym.entity.EventInformations;
import com.fokrys.gym.entity.PersonalData;
import com.fokrys.gym.entity.Role;
import com.fokrys.gym.entity.User;
import com.fokrys.gym.enums.Roles;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.LoginException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.repository.EventInformationsRepository;
import com.fokrys.gym.repository.RoleRepository;
import com.fokrys.gym.repository.UserRepository;
import com.fokrys.gym.service.PersonalDataService;
import com.fokrys.gym.service.UserService;

@Service
public class UserServiceImpl implements UserService {


	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PersonalDataService personalDataService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PersonalDataService personalDataService) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.personalDataService = personalDataService;
	}
	
	
	@Autowired
	public void createDefaultAccounts() {
		PersonalData personalData = new PersonalData("Patryk", "Jakis", "Opis", "dzka 55");
		personalData = personalDataService.save(personalData);
		PersonalData personalData2 = new PersonalData();
		personalData2 = personalDataService.save();
		User userAccount = new User("patryko101", "kontouzytkownika123", "patryko1001@wp.pl", roleRepository.findByName("USER"), personalData);
		User adminAccount = new User("patryko103", "kontoadministratora123", "patryko1003@wp.pl", roleRepository.findByName("ADMIN"), personalData2);
		
		userRepository.save(userAccount);
		userRepository.save(adminAccount);
	}

	@Override
	public User save(UserDto userDto) throws AlreadyExistException {
		if (exist(userDto)) {
			throw new AlreadyExistException("There is an account with that username: " + userDto.getUsername());
		}

		PersonalData personalData = new PersonalData();
		personalData = personalDataService.save();
		
		Role role  = roleRepository.findById(userDto.getRoleId()).get();
		
		User userAccount = new User(userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), role, personalData);
		

		return userRepository.save(userAccount);

	}


	@Override
	public Iterable<UserDto> findAll() throws NotExistException {
		Iterable<User> users = userRepository.findAll();
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		if (users == null) {
			throw new NotExistException();
		}
		
		for (User user: users) {
			System.out.println(user.toString());
			Long d;
			UserDto userDto;
			if (user.getPersonalData().getId() == null) {
				d = new Long(0);
				userDto = new UserDto(user.getId(), user.getUsername(), "", user.getEmail(), user.getRole().getId(), d);
			} else {
				userDto = new UserDto(user.getId(), user.getUsername(), "", user.getEmail(), user.getRole().getId(), user.getPersonalData().getId());
			}
			userDtoList.add(userDto);
		}
		System.out.println("User Dto List: ");
		System.out.println(userDtoList);
		return userDtoList;
	}
	
	@Override
	public User findOne(UserDto userDto) throws NotExistException {
		return userRepository.findByUsername(userDto.getUsername());
	}
	
	@Override
	public User findOneById(Long userId) throws NotExistException {
		return userRepository.findById(userId).get();
	}
	
	@Override
	public String findOneByUsername(String username) {
		User user = userRepository.findByUsername(username);
		String usernameS = "";
		if (user == null) {
			usernameS = null;
		}
		usernameS = user.getUsername();
		return usernameS;
	}
	
	@Override
	public User delete(UserDto userDto) throws NotExistException {
		if (!exist(userDto)) {
			throw new NotExistException();
		} 

		User user = userRepository.findByUsername(userDto.getUsername());
		userRepository.delete(user);
		return user;
	}
	
	@Override
	public User edit(UserDto userDto) throws NotExistException, AlreadyExistException, LoginException {
		System.out.println("IDIDIDIDIIDIDIIDIDIIDIIDIID: "+userDto.getId());
		Optional<User> user = userRepository.findById(userDto.getId());
		User userNew = userRepository.findByUsername(userDto.getUsername());
		User actualUser = user.get();
		if (!user.isPresent()) {
			throw new NotExistException();
		}
		
		
		if (userDto.getUsername() != null) {
			actualUser.setUsername(userDto.getUsername());
		}
		
		if (userDto.getPassword() != null) {
			actualUser.setPassword(userDto.getPassword());
		}
		
		if (userDto.getEmail() != null) {
			actualUser.setEmail(userDto.getEmail());
		}
		
		if (userDto.getRoleId() != null) {
			Role role = roleRepository.findById(userDto.getRoleId()).get();
			actualUser.setRole(role);
		}
		
		return userRepository.save(actualUser);
	}
	
	private boolean diffrenetThanNull(Object object) {
		if(object != null) {
			return true;
		}
		return false;
	}
	
	private boolean exist(UserDto userDto) {
		User domainUser = userRepository.findByUsername(userDto.getUsername());
		
		if (domainUser == null) {
			return false;
		} else {
			return true;
		}
	}


	@Override
	public Boolean exist(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}


	@Override
	public String findUsernameById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.get().getUsername();
	}


	@Override
	public Long findIdByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user.getId();
	}


	@Override
	public Iterable<UserDto> findAllU() {
		Iterable<User> users = userRepository.findAll();
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		
		for (User user: users) {
			System.out.println(user.toString());
			Long d;
			UserDto userDto;
			if (user.getPersonalData().getId() == null) {
				d = new Long(0);
				userDto = new UserDto(user.getId(), user.getUsername(), "", user.getEmail(), user.getRole().getId(), d);
			} else {
				userDto = new UserDto(user.getId(), user.getUsername(), "", user.getEmail(), user.getRole().getId(), user.getPersonalData().getId());
			}
			userDtoList.add(userDto);
		}
		System.out.println("User Dto List: ");
		System.out.println(userDtoList);
		return userDtoList;
	}


	@Override
	public UserDto findUserByUsername(String username){
		User user = userRepository.findByUsername(username);
		UserDto userDto = new UserDto(user.getId(), user.getUsername(),user.getPassword(), user.getEmail(), user.getRole().getId(), user.getPersonalData().getId());
		return userDto;
	}

	

}
