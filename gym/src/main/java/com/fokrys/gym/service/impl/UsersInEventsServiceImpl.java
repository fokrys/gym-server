package com.fokrys.gym.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fokrys.gym.dto.EnabledDisabledDto;
import com.fokrys.gym.dto.EventInformationsDto;
import com.fokrys.gym.dto.UsersInEventsDto;
import com.fokrys.gym.entity.EventInformations;
import com.fokrys.gym.entity.TypeOfTraining;
import com.fokrys.gym.entity.User;
import com.fokrys.gym.entity.UsersInEvents;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.repository.EventInformationsRepository;
import com.fokrys.gym.repository.UserRepository;
import com.fokrys.gym.repository.UsersInEventsRepository;
import com.fokrys.gym.service.UserService;
import com.fokrys.gym.service.UsersInEventsService;

@Service
public class UsersInEventsServiceImpl implements UsersInEventsService{
	
	private final UsersInEventsRepository usersInEventsRepository;
	private final UserRepository userRepository;
	private final EventInformationsRepository eventInformationsRepository;
	
	@Autowired
	public UsersInEventsServiceImpl(UsersInEventsRepository usersInEventsRepository, EventInformationsRepository eventInformationsRepository,
			UserRepository userRepository) {
		super();
		this.usersInEventsRepository = usersInEventsRepository;
		this.userRepository = userRepository;
		this.eventInformationsRepository = eventInformationsRepository;
	}

	@Override
	public UsersInEvents save(UsersInEventsDto usersInEventsDto) throws AlreadyExistException{
		User user = userRepository.findByUsername(usersInEventsDto.getUsername());
		Optional<EventInformations> event = eventInformationsRepository.findById(usersInEventsDto.getEventId());
		Optional<UsersInEvents> isUserExistInTable = usersInEventsRepository.findByUserAndEvent(user, event.get());
		if (isUserExistInTable.isPresent()) {
				throw new AlreadyExistException();
		}
		UsersInEvents usersInEvents = new UsersInEvents(event.get(), user);
		return usersInEventsRepository.save(usersInEvents);
	}

	@Override
	public Iterable<UsersInEvents> findAll() throws NotExistException{
		Iterable<UsersInEvents> allUsersInEvents = usersInEventsRepository.findAll();
		if (allUsersInEvents == null) {
			throw new NotExistException();
		}
		return allUsersInEvents;
	}

	@Override
	public Iterable<UsersInEvents> findAllInEvent(Long eventId) throws NotExistException {
		Iterable<UsersInEvents> usersInOneEvent = usersInEventsRepository.findAllByEventId(eventId);
		System.out.println("usersInOneEvent: "+usersInOneEvent);
		if(usersInOneEvent == null) {
			throw new NotExistException();
		}
		return usersInOneEvent;
	}
	
	@Override
	public List<UsersInEventsDto> findAllUsersInEvent(Long eventId) throws NotExistException {
		Iterable<UsersInEvents> usersInOneEvent = usersInEventsRepository.findAllByEventId(eventId);
		
		List<UsersInEventsDto> usersInEventDto = new ArrayList<UsersInEventsDto>();
		for (UsersInEvents userInEvent : usersInOneEvent) {
			UsersInEventsDto userInOneEventDto = new UsersInEventsDto(userInEvent.getId(), userInEvent.getEvent().getId(), userInEvent.getUser().getUsername(), userInEvent.isUserEnabled(), userInEvent.isUserDisabled());
			usersInEventDto.add(userInOneEventDto);
		}
		
		return usersInEventDto;
	}

	@Override
	public UsersInEvents confirm(UsersInEventsDto usersInEvents) throws NotExistException {
		Optional<UsersInEvents> userInEvent = usersInEventsRepository.findById(usersInEvents.getId());
		if (userInEvent == null) {
			throw new NotExistException();
		}
		userInEvent.get().setUserEnabled(true);
		return usersInEventsRepository.save(userInEvent.get());
	}

	@Override
	public UsersInEvents delete(UsersInEventsDto usersInEventsDto) throws NotExistException {
		User user = userRepository.findByUsername(usersInEventsDto.getUsername());
		Optional<EventInformations> event = eventInformationsRepository.findById(usersInEventsDto.getEventId());
		Optional<UsersInEvents> isUserExistInTable = usersInEventsRepository.findByUserAndEvent(user, event.get());
		
		if (!isUserExistInTable.isPresent()) {
			throw new NotExistException();
		}
		
		usersInEventsRepository.delete(isUserExistInTable.get());
		
		return isUserExistInTable.get();
	}
	
	@Override
	public UsersInEvents setDisabled(UsersInEventsDto usersInEventsDto) throws NotExistException {
		User user = userRepository.findByUsername(usersInEventsDto.getUsername());
		Optional<EventInformations> event = eventInformationsRepository.findById(usersInEventsDto.getEventId());
		Optional<UsersInEvents> isUserExistInTable = usersInEventsRepository.findByUserAndEvent(user, event.get());
		
		if (!isUserExistInTable.isPresent()) {
			throw new NotExistException();
		}
		isUserExistInTable.get().setUserDisabled(true);
		usersInEventsRepository.save(isUserExistInTable.get());
		
		return isUserExistInTable.get();
	}

	@Override
	public Iterable<EventInformationsDto> findAllEventsInformationsByUsername(String username) {
		User user = userRepository.findByUsername(username);
		Iterable<UsersInEvents> usersInEvents = usersInEventsRepository.findAllByUser(user);
		List<EventInformationsDto> eventList = new ArrayList<EventInformationsDto>();
		for (UsersInEvents data: usersInEvents) {
			EventInformations event = data.getEvent();
			EventInformationsDto eventDto = new EventInformationsDto(event.getId(), event.getTitle(), event.getAddress(), 
					event.getEventStart(), event.getEventEnd(), event.getEntriesEnd(), event.getDescription(), event.getUser().getId(), 
					event.getColor(), event.getTypeOfTraining().getId(), event.getNumberOfPeople());
			eventList.add(eventDto);
		}
		return eventList;
	}

	@Override
	public Iterable<UsersInEventsDto> findAllEventsByUsername(String username) {
		User user = userRepository.findByUsername(username);
		List<UsersInEventsDto> eventList = new ArrayList<UsersInEventsDto>();
		Iterable<UsersInEvents> usersInEvents = usersInEventsRepository.findAllByUser(user);
		for (UsersInEvents data: usersInEvents) {
			UsersInEventsDto usersInEventsDto = new UsersInEventsDto(data.getId(), data.getEvent().getId(), user.getUsername(), data.isUserEnabled(), data.isUserDisabled());
			eventList.add(usersInEventsDto);
		}
		return eventList;
	}

	@Override
	public Iterable<UsersInEvents> findAllEventsByEvent(EventInformations eventInformations) {
		Iterable<UsersInEvents> events = usersInEventsRepository.findAllByEvent(eventInformations);
		return events;
	}

	@Override
	public EnabledDisabledDto getEnabledDisabled(String username) {
		User user = userRepository.findByUsername(username);
		Iterable<UsersInEvents> list = usersInEventsRepository.findAllByUser(user);
		int enabled = 0;
		int disabled = 0;
		for (UsersInEvents data: list) {
			if (data.isUserEnabled() == true) {
				enabled++;
			}
			if (data.isUserDisabled() == true) {
				disabled++;
			}
		}
		
		EnabledDisabledDto enabledDisabledDto = new EnabledDisabledDto(enabled, disabled, user.getId(), user.getUsername());
		return enabledDisabledDto;
	}
}
