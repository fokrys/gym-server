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

import com.fokrys.gym.dto.RoleDto;
import com.fokrys.gym.dto.WebsiteDescriptionDto;
import com.fokrys.gym.entity.Role;
import com.fokrys.gym.entity.WebsiteDescription;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.repository.WebsiteDescriptionRepository;
import com.fokrys.gym.service.WebsiteDescriptionService;

@RestController
@RequestMapping("websiteInformation")
public class WebsiteDescriptionEndpoint {
	private final WebsiteDescriptionService websiteDescriptionService;
	
	@Autowired
	public WebsiteDescriptionEndpoint(WebsiteDescriptionService websiteDescriptionService) {
		super();
		this.websiteDescriptionService = websiteDescriptionService;
	}
	
	@PostMapping(value="/save", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveWebsiteDescription(@RequestBody WebsiteDescriptionDto websiteDescriptionDto) throws AlreadyExistException {
		WebsiteDescription websiteDescription = websiteDescriptionService.save(websiteDescriptionDto);
		return new ResponseEntity<>(websiteDescription, HttpStatus.OK);
	}
		
	@PostMapping(value="/delete", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@RequestBody WebsiteDescriptionDto websiteDescriptionDto) throws NotExistException{
		WebsiteDescription websiteDescription = websiteDescriptionService.delete(websiteDescriptionDto);
		return new ResponseEntity<>(websiteDescription, HttpStatus.OK);
	}
	
	@GetMapping(value="/findOne/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findOne(@PathVariable Long id) throws NotExistException{
		System.out.println("id"+id);
		WebsiteDescription websiteDescription = websiteDescriptionService.findById(id);
		return new ResponseEntity<>(websiteDescription, HttpStatus.OK);
	}
	
	@GetMapping(value="/findByTitle/{title}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findOneByTitle(@PathVariable String title) throws NotExistException{
		WebsiteDescription websiteDescription = websiteDescriptionService.findByTitle(title);
		return new ResponseEntity<>(websiteDescription, HttpStatus.OK);
	} 
}
