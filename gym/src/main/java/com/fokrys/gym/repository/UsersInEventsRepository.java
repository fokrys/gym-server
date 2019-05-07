package com.fokrys.gym.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fokrys.gym.entity.EventInformations;
import com.fokrys.gym.entity.User;
import com.fokrys.gym.entity.UsersInEvents;

public interface UsersInEventsRepository extends CrudRepository<UsersInEvents, Long>{
	
	Iterable<UsersInEvents> findAllByEventId(Long eventId);
	
	Iterable<UsersInEvents> findAllByEvent(EventInformations eventInformations);
	
	Iterable<UsersInEvents> findAllByUser(User user);
	
	
	UsersInEvents findByUser(User user);
	
	Optional<UsersInEvents> findByUserAndEvent(User user, EventInformations event);
	
//	UsersInEvents delete(UsersInEvents userInEvent);
}
