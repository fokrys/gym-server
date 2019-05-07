package com.fokrys.gym.service;

import com.fokrys.gym.dto.LevelOfAdvancementDto;
import com.fokrys.gym.entity.LevelOfAdvancement;
import com.fokrys.gym.exceptions.NotExistException;

public interface LevelOfAdvancementService {
	
	Iterable<LevelOfAdvancement> findAll() throws NotExistException;
	LevelOfAdvancement findOne(Long id) throws NotExistException;
	LevelOfAdvancement findOneById(Long id) throws NotExistException;
}
