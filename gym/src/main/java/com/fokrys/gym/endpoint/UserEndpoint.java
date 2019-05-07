package com.fokrys.gym.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fokrys.gym.dto.PersonalDataDto;
import com.fokrys.gym.dto.UserDto;
import com.fokrys.gym.entity.PersonalData;
import com.fokrys.gym.entity.User;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.LoginException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.model.ApplicationUser;
import com.fokrys.gym.service.PersonalDataService;
import com.fokrys.gym.service.UserService;

@RestController
@RequestMapping("user")
public class UserEndpoint {
	
	private final UserService userService;
	private final PersonalDataService personalDataService;
	
	@Autowired
	public UserEndpoint(UserService userService, PersonalDataService personalDataService) {
		super();
		this.userService = userService;
		this.personalDataService = personalDataService;
	}
	

	@PostMapping(value="/register", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) throws AlreadyExistException{
		User user = userService.save(userDto);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@PostMapping(value="/changePersonalData", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> changePersonalData(@RequestBody PersonalDataDto personalDataDto) throws NotExistException{
		PersonalData personalData = personalDataService.save(personalDataDto);
		
		return new ResponseEntity<>(personalData, HttpStatus.OK);
	}
	
	@GetMapping(value="/findAll", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll() throws NotExistException {
		Iterable<UserDto> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping(value="/findAllU", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAllU() throws NotExistException {
		Iterable<UserDto> users = userService.findAllU();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping(value="/findOne", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findOne(@RequestBody UserDto userDto) throws NotExistException{
		User user = userService.findOne(userDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(value="/findUsernameById/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findUsernameById(@PathVariable Long id) throws NotExistException{
		String user = userService.findUsernameById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(value="/findIdByUsername/{username}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findIdByUsername(@PathVariable String username) throws NotExistException{
		Long user = userService.findIdByUsername(username);
		System.out.println("USER ID: " + user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(value="/findUserByUsername/{username}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findUserByUsername(@PathVariable String username){
		UserDto user = userService.findUserByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(value="/findOneByUsername/{username}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findOneByUsername(@PathVariable String username) throws NotExistException{
		String user = userService.findOneByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping(value="/delete", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@RequestBody UserDto userDto) throws NotExistException{
		User user = userService.delete(userDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping(value="/edit", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> edit(@RequestBody UserDto userDto) throws NotExistException, AlreadyExistException, LoginException {
		System.out.println("EDIT!! EDIT!! EDIT!!");
		System.out.println(userDto.toString());
		User user = userService.edit(userDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(value="/exist/{username}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> exist(@PathVariable String username){
		Boolean exist = userService.exist(username);
		System.out.println("EXIST: " + exist);
		return new ResponseEntity<>(exist, HttpStatus.OK);
	}
	
	
}
