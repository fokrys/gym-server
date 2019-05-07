package com.fokrys.gym.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fokrys.gym.dto.PriceListDto;
import com.fokrys.gym.entity.PriceList;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.repository.PriceListRepository;
import com.fokrys.gym.service.PriceListService;

@Service
public class PriceListServiceImpl implements PriceListService {
	
	private final PriceListRepository priceListRepository;
	
	@Autowired
	public PriceListServiceImpl(PriceListRepository priceListRepository) {
		super();
		this.priceListRepository = priceListRepository;
	}

	@Override
	public PriceList save(PriceListDto priceListDto) throws AlreadyExistException{
		Optional<PriceList> exist = priceListRepository.findByName(priceListDto.getName()); 
		if (exist.isPresent()) {
			throw new AlreadyExistException();
		}
		PriceList priceList = new PriceList(priceListDto.getName(), priceListDto.getDescription(), (Double)priceListDto.getPrice(), priceListDto.getNumberOfMonths(), priceListDto.isPopular());
		return priceListRepository.save(priceList);
	}

	@Override
	public PriceList edit(PriceListDto priceListDto) throws NotExistException{
		Optional<PriceList> exist = priceListRepository.findById(priceListDto.getId()); 
		if (!exist.isPresent()) {
			throw new NotExistException();
		}
		
		PriceList priceList = new PriceList(priceListDto.getId(), priceListDto.getName(), priceListDto.getDescription(), priceListDto.getPrice(), priceListDto.getNumberOfMonths(), priceListDto.isPopular());
		return priceListRepository.save(priceList);
	}

	@Override
	public PriceList delete(PriceListDto priceListDto) throws NotExistException{
		Optional<PriceList> exist = priceListRepository.findById(priceListDto.getId()); 
		if (!exist.isPresent()) {
			throw new NotExistException();
		}
		priceListRepository.delete(exist.get());
		
		return exist.get();
	}

	@Override
	public Iterable<PriceList> findAll() {
		Iterable<PriceList> list = priceListRepository.findAll();
		return list;
	}

}
