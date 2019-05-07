package com.fokrys.gym.service;

import java.io.ByteArrayOutputStream;

import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.fokrys.gym.dto.GalleryDto;
import com.fokrys.gym.entity.Gallery;

public interface GalleryService {

	void init();
	GalleryDto save(String username, MultipartFile file);
	void deleteAll();
	Iterable<GalleryDto> findAllByUsername(String username);
	GalleryDto setProfile(Long id);
	GalleryDto getProfile(String username);
	GalleryDto deleteOne(GalleryDto galleryDto);
}
