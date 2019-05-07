package com.fokrys.gym.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fokrys.gym.entity.TypeOfTraining;

@Repository
public interface TypeOfTrainingRepository extends CrudRepository<TypeOfTraining, Long>{
	Optional<TypeOfTraining> findByTitle(String title);
}
