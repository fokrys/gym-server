package com.fokrys.gym.security;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fokrys.gym.entity.User;

@Service
public interface JWTService {
	String toToken(User user);

    Optional<String> getSubFromToken(String token);
}
