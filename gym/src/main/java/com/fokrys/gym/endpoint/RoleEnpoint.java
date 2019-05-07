package com.fokrys.gym.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fokrys.gym.dto.RoleDto;
import com.fokrys.gym.entity.Role;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.service.RoleService;

@RestController
@RequestMapping("role")
public class RoleEnpoint {
	
	private final RoleService roleService;

	@Autowired
	public RoleEnpoint(RoleService roleService) {
		super();
		this.roleService = roleService;
	}
	
	
	@PostMapping(value="/save", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveRole(@RequestBody RoleDto roleDto) throws AlreadyExistException{
		Role role = roleService.save(roleDto);
		return new ResponseEntity<>(role, HttpStatus.OK);
	}
	
	@GetMapping(value="/findAll", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll() throws NotExistException{
		Iterable<Role> roles = roleService.findAll();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
	
	@GetMapping(value="/findOne", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findOne(@RequestBody RoleDto roleDto) throws NotExistException{
		Role role = roleService.findOne(roleDto);
		return new ResponseEntity<>(role, HttpStatus.OK);
	}
	
	@PostMapping(value="/delete", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@RequestBody RoleDto roleDto) throws NotExistException{
		Role role = roleService.delete(roleDto);
		return new ResponseEntity<>(role, HttpStatus.OK);
	}
	
	@PostMapping(value="/edit", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> edit(@RequestBody RoleDto roleDto) throws NotExistException{
		Role role = roleService.edit(roleDto);
		return new ResponseEntity<>(role, HttpStatus.OK);
	}
	
}
