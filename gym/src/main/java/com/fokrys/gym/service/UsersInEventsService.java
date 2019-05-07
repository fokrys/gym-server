package com.fokrys.gym.service;


import com.fokrys.gym.dto.EnabledDisabledDto;
import com.fokrys.gym.dto.EventInformationsDto;
import com.fokrys.gym.dto.UsersInEventsDto;
import com.fokrys.gym.entity.EventInformations;
import com.fokrys.gym.entity.UsersInEvents;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;

public interface UsersInEventsService {
	
	UsersInEvents save(UsersInEventsDto usersInEvents) throws AlreadyExistException;
	UsersInEvents confirm(UsersInEventsDto usersInEvents) throws NotExistException;
	UsersInEvents delete(UsersInEventsDto usersInEvents) throws NotExistException;
	UsersInEvents setDisabled(UsersInEventsDto usersInEvents) throws NotExistException;
	EnabledDisabledDto getEnabledDisabled(String username);
	Iterable<UsersInEvents> findAll() throws NotExistException;
	Iterable<UsersInEvents> findAllInEvent(Long eventId) throws NotExistException;
	Iterable<UsersInEventsDto> findAllUsersInEvent(Long eventId) throws NotExistException;
	Iterable<EventInformationsDto> findAllEventsInformationsByUsername(String username);
	Iterable<UsersInEventsDto> findAllEventsByUsername(String username);
	Iterable<UsersInEvents> findAllEventsByEvent(EventInformations eventInformations);
}
