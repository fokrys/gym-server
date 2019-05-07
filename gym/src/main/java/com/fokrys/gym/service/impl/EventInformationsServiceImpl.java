package com.fokrys.gym.service.impl;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fokrys.gym.dto.EventInformationsDto;
import com.fokrys.gym.dto.UsersInEventsDto;
import com.fokrys.gym.entity.EventInformations;
import com.fokrys.gym.entity.TypeOfTraining;
import com.fokrys.gym.entity.User;
import com.fokrys.gym.entity.UsersInEvents;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.repository.EventInformationsRepository;
import com.fokrys.gym.repository.TypeOfTrainingRepository;
import com.fokrys.gym.repository.UserRepository;
import com.fokrys.gym.service.EventInformationsService;
import com.fokrys.gym.service.UsersInEventsService;

@Service
public class EventInformationsServiceImpl implements EventInformationsService {
	
	private final EventInformationsRepository eventInformationsRepository;
	private final UserRepository userRepository;
	private final TypeOfTrainingRepository typeOfTrainingRepository;
	private final UsersInEventsService usersInEventsService;

	@Autowired
	public EventInformationsServiceImpl(EventInformationsRepository eventInformationsRepository, UserRepository userRepository, 
			TypeOfTrainingRepository typeOfTrainingRepository, UsersInEventsService usersInEventsService) {
		super();
		this.eventInformationsRepository = eventInformationsRepository;
		this.userRepository = userRepository;
		this.typeOfTrainingRepository = typeOfTrainingRepository;
		this.usersInEventsService = usersInEventsService;
	}

	@Override
	public EventInformationsDto save(EventInformationsDto eventInformationsDto) {
		
		Optional<User> user = userRepository.findById(eventInformationsDto.getCouchId());
		Optional<TypeOfTraining> typeOfTraining = typeOfTrainingRepository.findById(eventInformationsDto.getTypeId());
		
		
		
		EventInformations eventInformations = new EventInformations();
		eventInformations.setTitle(eventInformationsDto.getTitle());
		eventInformations.setDescription(eventInformationsDto.getDescription());
		eventInformations.setAddress(eventInformationsDto.getAddress());
		eventInformations.setColor(eventInformationsDto.getColor());
		eventInformations.setEventStart(eventInformationsDto.getEventStart());
		eventInformations.setEventEnd(eventInformationsDto.getEventEnd());
		eventInformations.setEntriesEnd(eventInformationsDto.getEntriesEnd());
		eventInformations.setUser(user.get());
		eventInformations.setTypeOfTraining(typeOfTraining.get());
		eventInformations.setNumberOfPeople(eventInformationsDto.getNumberOfPeople());
		EventInformations eventFromBase = eventInformationsRepository.save(eventInformations);

		
		eventInformationsDto.setId(eventFromBase.getId());
		
		
//		
//				
//		EventInformations eventInformationss = new EventInformations();
//		eventInformationss.setName(eventInformationsDto.getName());
//		eventInformationss.setAddress(eventInformationsDto.getAddress());
//		eventInformationss.setEventStartTime(eventInformationsDto.getEntriesEndTime());
//		eventInformationss.setEntriesEndTime(eventInformationsDto.getEntriesEndTime());
//		eventInformationss.setUser(user.get());
//		
		
		return eventInformationsDto;
	}

//	@Override
//	public Iterable<EventInformations> findAll() {
//		return eventInformationsRepository.findAll();
//	}
	
	@Override
	public Iterable<EventInformationsDto> findAll() {
		
		List<EventInformationsDto> eventInformationsDtoList = new ArrayList<EventInformationsDto>();
		
		Iterable<EventInformations> eventInformationsTable = eventInformationsRepository.findAll();
		for (EventInformations event: eventInformationsTable) {
			EventInformationsDto eventInformationsDto = new EventInformationsDto();
			eventInformationsDto.setTitle(event.getTitle());
			eventInformationsDto.setAddress(event.getAddress());
			eventInformationsDto.setColor(event.getColor());
			eventInformationsDto.setDescription(event.getDescription());
			eventInformationsDto.setEventStart(event.getEventStart());
			eventInformationsDto.setEventEnd(event.getEventEnd());
			eventInformationsDto.setEntriesEnd(event.getEntriesEnd());
			eventInformationsDto.setCouchId(event.getUser().getId());
			eventInformationsDto.setTypeId(event.getTypeOfTraining().getId());
			eventInformationsDto.setId(event.getId());
			eventInformationsDto.setNumberOfPeople(event.getNumberOfPeople());
			eventInformationsDtoList.add(eventInformationsDto);			
		}
		
		return eventInformationsDtoList;
	}

	@Override
	public EventInformations findOne(EventInformationsDto eventInformationsDto) {
		return eventInformationsRepository.findById(eventInformationsDto.getId()).get();
	}
	
	@Override
	public EventInformations findById(Long id) {
		return eventInformationsRepository.findById(id).get();
	}

	@Override
	public EventInformations findOneByTrainer(EventInformationsDto eventInformationsDto) {
		//do zmiany! IFY!!
		Optional<User> user = userRepository.findById(eventInformationsDto.getCouchId());
		Optional<EventInformations> eventInformations = eventInformationsRepository.findByUser(user.get());
		return eventInformations.get();
	}

	@Override
	public EventInformations delete(Long id) throws NotExistException {
		Optional<EventInformations> eventInformations = eventInformationsRepository.findById(id);
		Iterable<UsersInEvents> usersInEvents = usersInEventsService.findAllEventsByEvent(eventInformations.get());
		
		if(usersInEvents != null) {
			for (UsersInEvents ss: usersInEvents) {
				UsersInEventsDto usersInEventsDto = new UsersInEventsDto(ss.getId(), ss.getEvent().getId(), ss.getUser().getUsername(), ss.isUserEnabled(), ss.isUserDisabled());
				usersInEventsService.delete(usersInEventsDto);
			}
		}
		
		eventInformationsRepository.delete(eventInformations.get());
		return eventInformations.get();
	}

	@Override
	public EventInformations edit(EventInformationsDto eventInformationsDto) throws NotExistException {
		Optional<User> user = userRepository.findById(eventInformationsDto.getCouchId());	
		Optional<TypeOfTraining> typeOfTraining = typeOfTrainingRepository.findById(eventInformationsDto.getTypeId());
		
		EventInformations eventInformations = eventInformationsRepository.findById(eventInformationsDto.getId()).get();
		
		if (eventInformations == null) {
			throw new NotExistException();
		}
		
		eventInformations.setId(eventInformationsDto.getId());
		eventInformations.setTitle(eventInformationsDto.getTitle());
		eventInformations.setDescription(eventInformationsDto.getDescription());
		eventInformations.setAddress(eventInformationsDto.getAddress());
		eventInformations.setColor(eventInformationsDto.getColor());
		eventInformations.setEventStart(eventInformationsDto.getEventStart());
		eventInformations.setEventEnd(eventInformationsDto.getEventEnd());
		eventInformations.setEntriesEnd(eventInformationsDto.getEntriesEnd());
		eventInformations.setUser(user.get());
		eventInformations.setTypeOfTraining(typeOfTraining.get());
		eventInformations.setNumberOfPeople(eventInformationsDto.getNumberOfPeople());
		eventInformationsRepository.save(eventInformations);
//		
//		
//		EventInformations eventInformations = eventInformationsRepository.findById(eventInformationsDto.getId()).get();
//		eventInformations.setName(eventInformationsDto.getName());
//		eventInformations.setAddress(eventInformationsDto.getAddress());
//		eventInformations.setEventStartTime(eventInformationsDto.getEntriesEndTime());
//		eventInformations.setEntriesEndTime(eventInformationsDto.getEntriesEndTime());
//		eventInformations.setDescription(eventInformationsDto.getDescription());
//		eventInformations.setUser(user.get());
		
		return eventInformations;
	}

	@Override
	public Iterable<EventInformations> findActualEvents() {
		System.out.println("findActual");
		Iterable<EventInformations> allEvents = eventInformationsRepository.findAll();
		List<EventInformations> listOfEvents = new ArrayList<EventInformations>();
		for(EventInformations event: allEvents) {
			if (!event.getEventEnd().equals(new Date()) && event.getEventEnd().after(new Date())) {
				listOfEvents.add(event);
			}
		}
		return listOfEvents;
	}
}

