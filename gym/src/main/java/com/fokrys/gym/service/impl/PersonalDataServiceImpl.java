package com.fokrys.gym.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fokrys.gym.dto.PersonalDataDto;
import com.fokrys.gym.entity.PersonalData;
import com.fokrys.gym.entity.User;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.repository.PersonalDataRepository;
import com.fokrys.gym.repository.UserRepository;
import com.fokrys.gym.service.PersonalDataService;

@Service
public class PersonalDataServiceImpl implements PersonalDataService{
	
	private final PersonalDataRepository personalDataRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public PersonalDataServiceImpl(PersonalDataRepository personalDataRepository, UserRepository userRepository) {
		super();
		this.personalDataRepository = personalDataRepository;
		this.userRepository = userRepository;
	}

	@Override
	public PersonalData save(PersonalDataDto personalDataDto) throws NotExistException{

		
		PersonalData actualPersonalData = new PersonalData();
		actualPersonalData.setName(personalDataDto.getName());
		actualPersonalData.setSurname(personalDataDto.getSurname());
		actualPersonalData.setAddress(personalDataDto.getAddress());
		actualPersonalData.setDescription(personalDataDto.getDescription());
		
		return personalDataRepository.save(actualPersonalData);
	}
	
	@Override
	public PersonalData save(PersonalData personalData){
		return personalDataRepository.save(personalData);
	}

	@Override
	public Iterable<PersonalData> findAll() throws NotExistException {
		Iterable<PersonalData> personalDatas = personalDataRepository.findAll();
		if(personalDatas == null) {
			throw new NotExistException();
		}
		return personalDatas;
	}


	@Override
	public PersonalData save() {
		PersonalData personalData = new PersonalData();
		
		return personalDataRepository.save(personalData);
	}

	@Override
	public PersonalData findOneById(Long id) {
		return personalDataRepository.findById(id).get();
	}

	@Override
	public PersonalData edit(PersonalDataDto personalDataDto) throws NotExistException {
		Optional<PersonalData> personalDataOp = personalDataRepository.findById(personalDataDto.getId());
		if (!personalDataOp.isPresent()) {
			throw new NotExistException();
		}
		
		PersonalData personalData = personalDataOp.get();
		if (personalDataDto.getName() != null) {
			personalData.setName(personalDataDto.getName());
		}
		if (personalDataDto.getSurname() != null) {
			personalData.setSurname(personalDataDto.getSurname());
		}
		if (personalDataDto.getDescription() != null) {
			personalData.setDescription(personalDataDto.getDescription());
		}
		if (personalDataDto.getAddress() != null) {
			personalData.setAddress(personalDataDto.getAddress());
		}
		
		
		
		return personalDataRepository.save(personalData);
	}

	
	
	
}
