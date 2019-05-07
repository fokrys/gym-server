package com.fokrys.gym.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fokrys.gym.dto.RoleDto;
import com.fokrys.gym.dto.WebsiteDescriptionDto;
import com.fokrys.gym.entity.EventInformations;
import com.fokrys.gym.entity.Role;
import com.fokrys.gym.entity.User;
import com.fokrys.gym.entity.WebsiteDescription;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.repository.UserRepository;
import com.fokrys.gym.repository.WebsiteDescriptionRepository;
import com.fokrys.gym.service.WebsiteDescriptionService;

@Service
public class WebsiteDescriptionServiceImpl implements WebsiteDescriptionService {
	private final WebsiteDescriptionRepository websiteDescriptionRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public WebsiteDescriptionServiceImpl(WebsiteDescriptionRepository websiteDescriptionRepository, UserRepository userRepository) {
		super();
		this.websiteDescriptionRepository = websiteDescriptionRepository;
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void createDefaultWebDescriptions() {
		User user = userRepository.findByUsername("patryko103");
		WebsiteDescription webDesc= new WebsiteDescription(Long.valueOf(1), "O nas", "Brak informacji",
				new Date(), user);
		websiteDescriptionRepository.save(webDesc);
	}

	@Override
	public WebsiteDescription save(WebsiteDescriptionDto websiteDescriptionDto) {
		Optional<User> user = userRepository.findById(websiteDescriptionDto.getUserId());
		WebsiteDescription websiteDescription = new WebsiteDescription();
		websiteDescription.setTitle(websiteDescriptionDto.getTitle());
		websiteDescription.setDescription(websiteDescriptionDto.getDescription());
		Date date = new Date();
		websiteDescription.setDate(date);
		websiteDescription.setUser(user.get());
		return websiteDescriptionRepository.save(websiteDescription);
	}

	@Override
	public WebsiteDescription delete(WebsiteDescriptionDto websiteDescriptionDto) {
		Optional<WebsiteDescription> websiteDescription = websiteDescriptionRepository.findById(websiteDescriptionDto.getId());
		websiteDescriptionRepository.delete(websiteDescription.get());
		return websiteDescription.get();
	}

	@Override
	public WebsiteDescription findById(Long id) throws NotExistException {
		return websiteDescriptionRepository.findById(id).get();
	}

	@Override
	public WebsiteDescription findByTitle(String title) throws NotExistException {
		
		return websiteDescriptionRepository.findByTitle(title).get();
	}
	
}
