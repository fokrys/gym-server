package com.fokrys.gym.service;

import com.fokrys.gym.dto.PriceListDto;
import com.fokrys.gym.entity.PriceList;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;

public interface PriceListService {
	PriceList save(PriceListDto priceListDto) throws AlreadyExistException;
	PriceList edit(PriceListDto priceListDto) throws NotExistException;
	PriceList delete(PriceListDto priceListDto) throws NotExistException;
	Iterable<PriceList> findAll();
}
