package com.fokrys.gym.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fokrys.gym.entity.PriceList;

@Repository
public interface PriceListRepository extends CrudRepository<PriceList, Long>{
	Optional<PriceList> findByName(String name);
}
