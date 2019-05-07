package com.fokrys.gym.service;

import java.util.List;

import com.fokrys.gym.dto.TypeDto;
import com.fokrys.gym.dto.TypeOfTrainingDto;
import com.fokrys.gym.entity.TypeOfTraining;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;

public interface TypeOfTrainingService {
	
	TypeOfTraining save(TypeOfTrainingDto typesOfTrainingDto) throws NotExistException;
	List<TypeDto> findAll() throws NotExistException;
	TypeOfTraining findOne(TypeOfTrainingDto typesOfTrainingDto) throws NotExistException;
	TypeOfTraining delete(TypeOfTrainingDto typesOfTrainingDto) throws NotExistException;
	TypeOfTraining edit(TypeOfTrainingDto typesOfTrainingDto) throws NotExistException;
}
