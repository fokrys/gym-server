package com.fokrys.gym.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fokrys.gym.dto.PriceListDto;
import com.fokrys.gym.entity.PriceList;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.exceptions.NotExistException;
import com.fokrys.gym.service.PriceListService;

@RestController
@RequestMapping("priceList")
public class PriceListEndpoint {
	
	private final PriceListService priceListService;

	@Autowired
	public PriceListEndpoint(PriceListService priceListService) {
		super();
		this.priceListService = priceListService;
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<?> save(@RequestBody PriceListDto priceListDto) throws AlreadyExistException{
		System.out.println(priceListDto.toString());
		PriceList priceList = priceListService.save(priceListDto);
		return new ResponseEntity<>(priceList, HttpStatus.OK);
	}
	
	@PostMapping(value="/edit")
	public ResponseEntity<?> edit(@RequestBody PriceListDto priceListDto) throws NotExistException{
		PriceList priceList = priceListService.edit(priceListDto);
		return new ResponseEntity<>(priceList, HttpStatus.OK);
	}
	
	@PostMapping(value="/delete")
	public ResponseEntity<?> delete(@RequestBody PriceListDto priceListDto) throws NotExistException{
		PriceList priceList = priceListService.delete(priceListDto);
		return new ResponseEntity<>(priceList, HttpStatus.OK);
	}
	
	@GetMapping(value="/findAll")
	public ResponseEntity<?> findAll() throws NotExistException{
		Iterable<PriceList> priceList = priceListService.findAll();
		return new ResponseEntity<>(priceList, HttpStatus.OK);
	}

}
