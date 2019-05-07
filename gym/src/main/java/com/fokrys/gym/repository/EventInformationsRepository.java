package com.fokrys.gym.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fokrys.gym.entity.EventInformations;
import com.fokrys.gym.entity.User;

@Repository
public interface EventInformationsRepository extends CrudRepository<EventInformations, Long> {
	Optional<EventInformations> findByUser(User user);
}
