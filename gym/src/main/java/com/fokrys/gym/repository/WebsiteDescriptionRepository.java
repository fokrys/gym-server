package com.fokrys.gym.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fokrys.gym.entity.WebsiteDescription;

@Repository
public interface WebsiteDescriptionRepository extends CrudRepository<WebsiteDescription, Long>{
	Optional<WebsiteDescription> findByTitle(String title);

}
