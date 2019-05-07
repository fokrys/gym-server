package com.fokrys.gym.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fokrys.gym.entity.LevelOfAdvancement;

@Repository
public interface LevelOfAdvancementRepository extends CrudRepository<LevelOfAdvancement, Long>{

}
