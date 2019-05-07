package com.fokrys.gym.service;

import com.fokrys.gym.dto.EventInformationsDto;
import com.fokrys.gym.entity.EventInformations;
import com.fokrys.gym.entity.UsersInEvents;
import com.fokrys.gym.exceptions.NotExistException;

public interface EventInformationsService {
	EventInformationsDto save(EventInformationsDto eventInformationsDto);
	Iterable<EventInformationsDto> findAll();
	EventInformations findOne(EventInformationsDto eventInformationsDto);
	EventInformations findById(Long id);
	EventInformations findOneByTrainer(EventInformationsDto eventInformationsDto);
	EventInformations delete(Long id) throws NotExistException;
	EventInformations edit(EventInformationsDto eventInformationsDto) throws NotExistException;
	Iterable<EventInformations> findActualEvents();
}
