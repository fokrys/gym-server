package com.fokrys.gym.service;

import com.fokrys.gym.dto.WebsiteDescriptionDto;
import com.fokrys.gym.entity.WebsiteDescription;
import com.fokrys.gym.exceptions.NotExistException;

public interface WebsiteDescriptionService {
	WebsiteDescription save(WebsiteDescriptionDto websiteDescriptionDto);
	WebsiteDescription delete(WebsiteDescriptionDto websiteDescriptionDto);
	WebsiteDescription findById(Long id) throws NotExistException;
	WebsiteDescription findByTitle(String title) throws NotExistException;
}
