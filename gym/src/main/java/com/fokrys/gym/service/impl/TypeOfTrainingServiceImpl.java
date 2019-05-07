package com.fokrys.gym.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fokrys.gym.dto.TypeDto;
import com.fokrys.gym.dto.TypeOfTrainingDto;
import com.fokrys.gym.entity.LevelOfAdvancement;
import com.fokrys.gym.entity.TypeOfTraining;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.repository.TypeOfTrainingRepository;
import com.fokrys.gym.service.LevelOfAdvancementService;
import com.fokrys.gym.service.TypeOfTrainingService;

@Service
public class TypeOfTrainingServiceImpl implements TypeOfTrainingService{
	
	private final TypeOfTrainingRepository typeOfTrainingRepository;
	private final LevelOfAdvancementService levelOfAdvancementService;

	@Autowired
	public TypeOfTrainingServiceImpl(TypeOfTrainingRepository typeOfTrainingRepository, LevelOfAdvancementService levelOfAdvancementService) {
		super();
		this.typeOfTrainingRepository = typeOfTrainingRepository;
		this.levelOfAdvancementService = levelOfAdvancementService;
	}

	@Override
	public TypeOfTraining save(TypeOfTrainingDto typeOfTrainingDto) throws NotExistException {
		LevelOfAdvancement levelOfAdvancement = levelOfAdvancementService.findOne(typeOfTrainingDto.getLevelId());
		TypeOfTraining typeOfTraining = new TypeOfTraining(typeOfTrainingDto.getTitle(), typeOfTrainingDto.getDescription(), typeOfTrainingDto.getContraindications(), levelOfAdvancement);
		
		typeOfTrainingRepository.save(typeOfTraining);
		return typeOfTraining;
	}

	@Override
	public List<TypeDto> findAll() throws NotExistException {
		
		List<TypeDto> cos = new ArrayList<>();
		for(TypeOfTraining cc : typeOfTrainingRepository.findAll()) {
			TypeDto hi = new TypeDto();
			hi.setId(cc.getId());
			hi.setContraindications(cc.getContraindications());
			hi.setDescription(cc.getDescription());
			hi.setTitle(cc.getTitle());
			hi.setLevelId(cc.getLevelOfAdvancement().getId());
			hi.setLevelName(cc.getLevelOfAdvancement().getName());
			cos.add(hi);
		}
		
		return cos;
	}

	@Override
	public TypeOfTraining findOne(TypeOfTrainingDto typeOfTrainingDto) throws NotExistException {
		Optional<TypeOfTraining> typeOfTrainingCheck = typeOfTrainingRepository.findById(typeOfTrainingDto.getId());
		if(typeOfTrainingCheck == null) {
			throw new NotExistException();
		}
		return typeOfTrainingCheck.get();
	}

	@Override
	public TypeOfTraining delete(TypeOfTrainingDto typeOfTrainingDto) throws NotExistException {
		Optional<TypeOfTraining> typeOfTraining = typeOfTrainingRepository.findById(typeOfTrainingDto.getId());
		if(typeOfTraining == null) {
			throw new NotExistException();
		}
		typeOfTrainingRepository.delete(typeOfTraining.get());
		
		return typeOfTraining.get();
	}

	@Override
	public TypeOfTraining edit(TypeOfTrainingDto typeOfTrainingDto) throws NotExistException {
		LevelOfAdvancement levelOfAdvancement = levelOfAdvancementService.findOne(typeOfTrainingDto.getLevelId());
		TypeOfTraining typeOfTrainingCheck = typeOfTrainingRepository.findById(typeOfTrainingDto.getId()).orElse(null);
		System.out.println("TUTAJ!");
		if(typeOfTrainingCheck == null) {
			System.out.println("NOT EXIST!");
			throw new NotExistException();
		}
		
		TypeOfTraining typeOfTraining = new TypeOfTraining(typeOfTrainingDto.getId(), typeOfTrainingDto.getTitle(), typeOfTrainingDto.getDescription(), typeOfTrainingDto.getContraindications(), levelOfAdvancement);
		
		return typeOfTrainingRepository.save(typeOfTraining);
	}
	
	private boolean exist(TypeOfTrainingDto typeOfTrainingDto) {
		Optional<TypeOfTraining> typeOfTraining = typeOfTrainingRepository.findByTitle(typeOfTrainingDto.getTitle());
		if(typeOfTraining == null) {
			return false;
		} else {
			return true;
		}
	}
	
}
