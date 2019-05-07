package com.fokrys.gym.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fokrys.gym.dto.LevelOfAdvancementDto;
import com.fokrys.gym.dto.TypeDto;
import com.fokrys.gym.dto.TypeOfTrainingDto;
import com.fokrys.gym.entity.TypeOfTraining;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.service.TypeOfTrainingService;

@RestController
@RequestMapping("typeOfTraining")
public class TypeOfTrainingEndpoint {

	private final TypeOfTrainingService typeOfTrainingService;

	@Autowired
	public TypeOfTrainingEndpoint(TypeOfTrainingService typeOfTrainingService) {
		super();
		this.typeOfTrainingService = typeOfTrainingService;
	}
	
	@PostMapping(value="/save", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveTypeOfTraining(@RequestBody TypeOfTrainingDto typeOfTrainingDto) throws  NotExistException{
			TypeOfTraining typeOfTraining = typeOfTrainingService.save(typeOfTrainingDto);
			return new ResponseEntity<>(typeOfTraining, HttpStatus.OK);
	}
	
	@PostMapping(value="/edit", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editTypeOfTraining(@RequestBody TypeOfTrainingDto typeOfTrainingDto) throws AlreadyExistException, NotExistException{
		System.out.println(typeOfTrainingDto.toString());
		TypeOfTraining typeOfTraining = typeOfTrainingService.edit(typeOfTrainingDto);
		return new ResponseEntity<>(typeOfTraining, HttpStatus.OK);
	}
	
	@PostMapping(value="/delete", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteTypeOfTraining(@RequestBody TypeOfTrainingDto typeOfTrainingDto) throws NotExistException{
		TypeOfTraining typeOfTraining = typeOfTrainingService.delete(typeOfTrainingDto);
		return new ResponseEntity<>(typeOfTraining, HttpStatus.OK);
	}
	
	@GetMapping(value="/findAll", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAllTypeOfTraining() throws NotExistException{
		List<TypeDto> typeOfTraining = typeOfTrainingService.findAll();
		
		return new ResponseEntity<>(typeOfTraining, HttpStatus.OK);
	}
	
	@PostMapping(value="/findOne", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findOneTypeOfTraining(@RequestBody TypeOfTrainingDto typeOfTrainingDto) throws NotExistException{
		TypeOfTraining typeOfTraining = typeOfTrainingService.findOne(typeOfTrainingDto);
		return new ResponseEntity<>(typeOfTraining, HttpStatus.OK);
	}
	
}
