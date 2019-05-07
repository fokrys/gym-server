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

import com.fokrys.gym.dto.EventInformationsDto;
import com.fokrys.gym.dto.RoleDto;
import com.fokrys.gym.dto.WebsiteDescriptionDto;
import com.fokrys.gym.entity.EventInformations;
import com.fokrys.gym.entity.Role;
import com.fokrys.gym.entity.UsersInEvents;
import com.fokrys.gym.entity.WebsiteDescription;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.repository.EventInformationsRepository;
import com.fokrys.gym.service.EventInformationsService;
import com.fokrys.gym.service.UsersInEventsService;

@RestController
@RequestMapping("eventInformations")
public class EventInformationsEndpoint {
	
	private final EventInformationsService eventInformationsService;

	@Autowired
	public EventInformationsEndpoint(EventInformationsService eventInformationsService) {
		super();
		this.eventInformationsService = eventInformationsService;;
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<?> saveEventInformations(@RequestBody EventInformationsDto eventInformationsDto) throws AlreadyExistException{
		System.out.println(eventInformationsDto.toString());
		EventInformationsDto eventInformations = eventInformationsService.save(eventInformationsDto);
		return new ResponseEntity<>(eventInformations, HttpStatus.OK);
	}
	
	@PostMapping(value="/edit", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> edit(@RequestBody EventInformationsDto eventInformationsDto) throws NotExistException{
		EventInformations eventInformations = eventInformationsService.edit(eventInformationsDto);
		return new ResponseEntity<>(eventInformations, HttpStatus.OK);
	}
	
//	@PostMapping(value="/delete", produces= MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> delete(@RequestBody EventInformationsDto eventInformationsDto) throws NotExistException{
//		EventInformations eventInformations = eventInformationsService.delete(eventInformationsDto);
//		return new ResponseEntity<>(eventInformations, HttpStatus.OK);
//	}
	
	@GetMapping(value="/delete/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable Long id) throws NotExistException{
		EventInformations eventInformations = eventInformationsService.delete(id);
				
		return new ResponseEntity<>(eventInformations, HttpStatus.OK);
	}
	
	@GetMapping(value="/findOne/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findOne(@PathVariable Long id) throws NotExistException{
		EventInformations eventInformations = eventInformationsService.findById(id);
		return new ResponseEntity<>(eventInformations, HttpStatus.OK);
	}
	
	@GetMapping(value="/findAll", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll() throws NotExistException { 
		Iterable<EventInformationsDto> eventInormationsDto = eventInformationsService.findAll();
		return new ResponseEntity<>(eventInormationsDto, HttpStatus.OK);
	}
	
	@GetMapping(value="/findActualEvents", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findActualEvents() { 
		Iterable<EventInformations> list = eventInformationsService.findActualEvents();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
