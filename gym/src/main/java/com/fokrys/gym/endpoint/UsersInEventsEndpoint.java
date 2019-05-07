package com.fokrys.gym.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fokrys.gym.dto.EnabledDisabledDto;
import com.fokrys.gym.dto.EventInformationsDto;
import com.fokrys.gym.dto.UsersInEventsDto;
import com.fokrys.gym.entity.UsersInEvents;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.service.UsersInEventsService;

@RestController
@RequestMapping("usersInEvents")
public class UsersInEventsEndpoint {
	private final UsersInEventsService usersInEventsService;
	
	@Autowired
	public UsersInEventsEndpoint(UsersInEventsService usersInEventsService) {
		super();
		this.usersInEventsService = usersInEventsService;
	}
	
	@PostMapping(value="/join", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> join(@RequestBody UsersInEventsDto usersInEventsDto) throws AlreadyExistException {
		UsersInEvents usersInEvents = usersInEventsService.save(usersInEventsDto);
		return new ResponseEntity<>(usersInEvents, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findByEventId/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByEventId(@PathVariable Long id) throws NotExistException {
		Iterable<UsersInEvents> usersInOneEvent = usersInEventsService.findAllInEvent(id);
		return new ResponseEntity<>(usersInOneEvent, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findAllUsersByEventId/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAllUsersByEventId(@PathVariable Long id) throws NotExistException {
		System.out.println("WYWOLANIE findAllUsersByEventId");
		
		Iterable<UsersInEventsDto> usersInOneEvent = usersInEventsService.findAllUsersInEvent(id);
		System.out.println(usersInOneEvent.toString());
		return new ResponseEntity<>(usersInOneEvent, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findAll", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll() throws NotExistException {
		Iterable<UsersInEvents> usersInEvents = usersInEventsService.findAll();
		return new ResponseEntity<>(usersInEvents, HttpStatus.OK);
	}
	
	@PostMapping(value="/confirmUserInEvent", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> confirm(@RequestBody UsersInEventsDto usersInEventsDto) throws NotExistException {
		UsersInEvents usersInEvents = usersInEventsService.confirm(usersInEventsDto);
		return new ResponseEntity<>(usersInEvents, HttpStatus.OK);
	}
	
	@PostMapping(value="/delete", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@RequestBody UsersInEventsDto usersInEventsDto) throws NotExistException {
		UsersInEvents usersInEvents = usersInEventsService.delete(usersInEventsDto);
		return new ResponseEntity<>(usersInEvents, HttpStatus.OK);
	}
	
	@PostMapping(value="/setDisabled", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> setDisabled(@RequestBody UsersInEventsDto usersInEventsDto) throws NotExistException {
		UsersInEvents usersInEvents = usersInEventsService.setDisabled(usersInEventsDto);
		return new ResponseEntity<>(usersInEvents, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getEnabledDisabled/{username}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEnabledDisabled(@PathVariable String username) throws NotExistException {
		EnabledDisabledDto events = usersInEventsService.getEnabledDisabled(username);
		return new ResponseEntity<>(events, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findAllEventsInformationsByUsername/{username}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAllEventsInformationsByUsername(@PathVariable String username) throws NotExistException {
		Iterable<EventInformationsDto> events = usersInEventsService.findAllEventsInformationsByUsername(username);
		return new ResponseEntity<>(events, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findAllEventsByUsername/{username}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAllEventsByUsername(@PathVariable String username) throws NotExistException {
		Iterable<UsersInEventsDto> events = usersInEventsService.findAllEventsByUsername(username);
		return new ResponseEntity<>(events, HttpStatus.OK);
	}
	
}
