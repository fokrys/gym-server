package com.fokrys.gym.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fokrys.gym.dto.LevelOfAdvancementDto;
import com.fokrys.gym.entity.LevelOfAdvancement;
import com.fokrys.gym.entity.User;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.repository.LevelOfAdvancementRepository;
import com.fokrys.gym.service.LevelOfAdvancementService;

@Service
public class LevelOfAdvancementServiceImpl implements LevelOfAdvancementService{
	
	private final LevelOfAdvancementRepository levelOfAdvancementRepository;

	@Autowired
	public LevelOfAdvancementServiceImpl(LevelOfAdvancementRepository levelOfAdvancementRepository) {
		super();
		this.levelOfAdvancementRepository = levelOfAdvancementRepository;
	}
	
	@Autowired
	public void generateLevelsOfAdvancement() {
		LevelOfAdvancement begginer = new LevelOfAdvancement((long)1, "Początkujący");
		LevelOfAdvancement intermediate = new LevelOfAdvancement((long)2, "Średniozaawansowany");
		LevelOfAdvancement advanced = new LevelOfAdvancement((long)3, "Zaawansowany");
		
		levelOfAdvancementRepository.save(begginer);
		levelOfAdvancementRepository.save(intermediate);
		levelOfAdvancementRepository.save(advanced);
	}

	@Override
	public Iterable<LevelOfAdvancement> findAll() throws NotExistException {
		Iterable<LevelOfAdvancement> levelsOfAdvancement = levelOfAdvancementRepository.findAll();
		if (levelsOfAdvancement == null) {
			throw new NotExistException();
		}
		return levelsOfAdvancement;
	}

	@Override
	public LevelOfAdvancement findOne(Long id) throws NotExistException {
		System.out.println("TUTAJ!");
		Optional<LevelOfAdvancement> levelOfAdvancement = levelOfAdvancementRepository.findById(id);
		if (levelOfAdvancement == null) {
			throw new NotExistException();
		}
		return levelOfAdvancement.get();
	}
	
	@Override
	public LevelOfAdvancement findOneById(Long id) throws NotExistException {
		Optional<LevelOfAdvancement> levelOfAdvancement = levelOfAdvancementRepository.findById(id);
		if (levelOfAdvancement == null) {
			throw new NotExistException();
		}
		return levelOfAdvancement.get();
	}
}
