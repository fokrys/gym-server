package com.fokrys.gym.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fokrys.gym.entity.LevelOfAdvancement;
import com.fokrys.gym.entity.TypeOfTraining;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.service.LevelOfAdvancementService;

@RestController
@RequestMapping("levelOfAdvancement")
public class LevelOfAdvancementEndpoint {
	private final LevelOfAdvancementService levelOfAdvancementService;

	@Autowired
	public LevelOfAdvancementEndpoint(LevelOfAdvancementService levelOfAdvancementService) {
		super();
		this.levelOfAdvancementService = levelOfAdvancementService;
	}
	
	@GetMapping(value="/findAll", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAllLevelOfAdvancement() throws NotExistException{
		Iterable<LevelOfAdvancement> levelOfAdvancement = levelOfAdvancementService.findAll();
		return new ResponseEntity<>(levelOfAdvancement, HttpStatus.OK);
	}

}
