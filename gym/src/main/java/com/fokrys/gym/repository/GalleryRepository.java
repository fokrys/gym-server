package com.fokrys.gym.repository;


import org.springframework.data.repository.CrudRepository;

import com.fokrys.gym.entity.Gallery;
import com.fokrys.gym.entity.User;

public interface GalleryRepository extends CrudRepository<Gallery, Long>{
	Iterable<Gallery> findAllByUser(User user);
	Gallery findByUserAndProfile(User user, boolean profile);
}
