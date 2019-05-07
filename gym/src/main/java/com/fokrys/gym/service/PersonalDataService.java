package com.fokrys.gym.service;

import com.fokrys.gym.dto.PersonalDataDto;
import com.fokrys.gym.entity.PersonalData;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;

public interface PersonalDataService {
	PersonalData save(PersonalDataDto personalDataDto) throws NotExistException;
	PersonalData save(PersonalData personalData);
	PersonalData save();
	PersonalData edit(PersonalDataDto personalDataDto) throws NotExistException;;
	Iterable<PersonalData> findAll() throws NotExistException;
	PersonalData findOneById(Long id);
}
