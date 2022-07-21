package com.lab.lab.service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.AuthException;

import org.springframework.stereotype.Service;

import com.lab.lab.dto.AuthUser;
import com.lab.lab.enums.ResponseEnum;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {

	private final String KEY = "XuanLabKey";
	private final long EXPIRATION_TIME = 1 * 60 * 1000;

	public String generateToken(AuthUser userDto) {

		Map<String, Object> claims = new HashMap<>();
		claims.put("userName", userDto.getUsername());

		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, KEY).compact();
	}

	public void validateToken(String token) throws AuthException {
		try {
			Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
		} catch (Exception e) {
			throw new AuthException(ResponseEnum.AUTH_FAIL.getMsg());
		}
	}

}
