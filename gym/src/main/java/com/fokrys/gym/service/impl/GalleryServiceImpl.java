package com.fokrys.gym.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fokrys.gym.dto.GalleryDto;
import com.fokrys.gym.entity.Gallery;
import com.fokrys.gym.entity.User;
import com.fokrys.gym.repository.GalleryRepository;
import com.fokrys.gym.repository.UserRepository;
import com.fokrys.gym.service.GalleryService;

@Service
public class GalleryServiceImpl implements GalleryService {

	private final GalleryRepository galleryRepository;
	private final UserRepository userRepository;
	
	private final Path rootLocation = Paths.get("target\\classes\\static\\upload-dir");
	
	@Autowired
	public GalleryServiceImpl(GalleryRepository galleryRepository, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.galleryRepository = galleryRepository;
	}
	
	private Integer countOfPictures(User user) {
		Iterable<Gallery> images = galleryRepository.findAllByUser(user);
		int number = 0;
		for (Gallery image: images) {
			number++;
		}
		return number;
	}

	@Override
	public GalleryDto save(String username, MultipartFile file) {
		String filenameWithoutType = StringUtils.cleanPath(file.getOriginalFilename());
		String typeOfFile = filenameWithoutType.substring(filenameWithoutType.length() - 4);
		filenameWithoutType = filenameWithoutType.substring(0, filenameWithoutType.length() - 4);
		
		User user = userRepository.findByUsername(username);
		int numberOfPictures = countOfPictures(user);
		String filename =  filenameWithoutType+"_"+user.getId()+"_"+(numberOfPictures+1)+typeOfFile;
        try {
            if (file.isEmpty()) {
            	System.out.println("Failed to store empty file" + filename);
            	return null;
            }
            if (filename.contains("..")) {
                // This is a security check
            	System.out.println("Cannot store file with relative path outside current directory " + filename);
            	return null;
            }
            try (InputStream inputStream = file.getInputStream()) {
            	String upp = rootLocation.toString();
            	Path fileNameAndPath = Paths.get(upp, filename);
            	Files.write(fileNameAndPath, file.getBytes());
            	Gallery image = new Gallery(filename, false, user);
            	Iterable<Gallery> gallery = galleryRepository.findAllByUser(user);
            	List<Gallery> cos = new ArrayList<Gallery>();
            	cos = (List<Gallery>) gallery;
            	if (cos.size() == 0) {
            		image.setProfile(true);
            	}
            	image = galleryRepository.save(image);
            	GalleryDto galleryDto = new GalleryDto(image.getId(), image.getName(), image.isProfile(), image.getUser().getUsername(), image.getUser().getId());
            	return galleryDto;
            	
            }
            
        }
        catch (IOException e) {
        	System.out.println("Failed to store file " + filename);
        	System.out.println(e);
        }
		return null;
	}
	
	@Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
	
	@Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            System.out.println("Could not initialize storage");
            System.out.println(e);
        }
    }

	@Override
	public Iterable<GalleryDto> findAllByUsername(String username) {
		User user = userRepository.findByUsername(username);
		Iterable<Gallery> images = galleryRepository.findAllByUser(user);
		List<GalleryDto> imagesList = new ArrayList<GalleryDto>();
		for (Gallery gallery: images) {
			GalleryDto galleryDto = new GalleryDto(gallery.getId(), gallery.getName(), gallery.isProfile(), gallery.getUser().getUsername(), gallery.getUser().getId());
			imagesList.add(galleryDto);
		}
		return imagesList;
	}

	@Override
	public GalleryDto setProfile(Long id) {
		Optional<Gallery> imageOp = galleryRepository.findById(id);
		Gallery image = imageOp.get();
		Gallery gallery = galleryRepository.findByUserAndProfile(image.getUser(), true);
		if (gallery != null) {
			gallery.setProfile(false);
			galleryRepository.save(gallery);
		}
		
		image.setProfile(true);
		
		galleryRepository.save(image);
		GalleryDto galleryDto = new GalleryDto(image.getId(), image.getName(), image.isProfile(), image.getUser().getUsername(), image.getUser().getId());
		return galleryDto;
	}

	@Override
	public GalleryDto getProfile(String username) {
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			return null;
		} 
		
		GalleryDto galleryDto = new GalleryDto();
		Gallery image = galleryRepository.findByUserAndProfile(user, true);
		if (image != null) {
			galleryDto = new GalleryDto(image.getId(), image.getName(), image.isProfile(), image.getUser().getUsername(), image.getUser().getId());
		}
		return galleryDto;
	}

	@Override
	public GalleryDto deleteOne(GalleryDto galleryDto) {
		Gallery image = galleryRepository.findById(galleryDto.getId()).get();
		System.out.println("image: "+image.getName()+" : "+image.getId());
		galleryRepository.delete(image);
		Path location =  Paths.get("target\\classes\\static\\upload-dir\\"+galleryDto.getName());
		FileSystemUtils.deleteRecursively(location.toFile());
		
		return galleryDto;
	}

	
	
	

}
