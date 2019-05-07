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

import com.fokrys.gym.dto.PersonalDataDto;
import com.fokrys.gym.dto.WebsiteDescriptionDto;
import com.fokrys.gym.entity.PersonalData;
import com.fokrys.gym.entity.WebsiteDescription;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.service.PersonalDataService;

@RestController
@RequestMapping("personalData")
public class PersonalDataEndpoint {
	private final PersonalDataService personalDataService;
	
	@Autowired
	public PersonalDataEndpoint(PersonalDataService personalDataService) {
		super();
		this.personalDataService = personalDataService;
	}
	
	@GetMapping(value="/findOneById/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findOne(@PathVariable Long id) throws NotExistException {
		PersonalData personalData = personalDataService.findOneById(id);
		return new ResponseEntity<>(personalData, HttpStatus.OK);
	}
	
	@PostMapping(value="/edit", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> edit(@RequestBody PersonalDataDto personalDataDto) throws NotExistException {
		System.out.println(personalDataDto.toString());
		PersonalData personalData = personalDataService.edit(personalDataDto);
		return new ResponseEntity<>(personalData, HttpStatus.OK);
	}
}
