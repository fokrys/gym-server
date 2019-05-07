package com.fokrys.gym.endpoint;

import java.nio.file.Files;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fokrys.gym.dto.EventInformationsDto;
import com.fokrys.gym.dto.GalleryDto;
import com.fokrys.gym.exceptions.AlreadyExistException;
import com.fokrys.gym.service.GalleryService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("gallery")
public class GalleryEndpoint {
	
	private final GalleryService galleryService;
	List<String> files = new ArrayList<String>();

	@Autowired
	public GalleryEndpoint(GalleryService galleryService) {
		super();
		this.galleryService = galleryService;
	}
	
	@PostMapping(value="/save/{username}")
	public ResponseEntity<?> save(@PathVariable String username, @RequestParam("file") MultipartFile file) {
		GalleryDto galleryDto = galleryService.save(username, file);
		return new ResponseEntity<>(galleryDto, HttpStatus.OK);
	}
	
	@PostMapping(value="/setProfile")
	public ResponseEntity<?> setProfile(@RequestBody Long id) {
		GalleryDto galleryDto = galleryService.setProfile(id);
		return new ResponseEntity<>(galleryDto, HttpStatus.OK);
	}
	
	@GetMapping(value="/findAllByUsername/{username}")
	public ResponseEntity<?> findAllByUsername(@PathVariable String username) throws MalformedURLException {
		Iterable<GalleryDto> galleryDto = galleryService.findAllByUsername(username);
		return new ResponseEntity<>(galleryDto, HttpStatus.OK);
	}
	
	@GetMapping(value="/getProfile/{username}")
	public ResponseEntity<?> getProfile(@PathVariable String username) throws MalformedURLException {
		GalleryDto galleryDto = galleryService.getProfile(username);
		return new ResponseEntity<>(galleryDto, HttpStatus.OK);
	}
	
	@PostMapping(value="/delete")
	public ResponseEntity<?> delete(@RequestBody GalleryDto galleryDto) {
		GalleryDto gallery = galleryService.deleteOne(galleryDto);
		return new ResponseEntity<>(gallery, HttpStatus.OK);
	}
}
