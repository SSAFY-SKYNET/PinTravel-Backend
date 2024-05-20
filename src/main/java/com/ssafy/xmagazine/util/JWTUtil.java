package com.ssafy.xmagazine.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssafy.xmagazine.exception.UnAuthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTUtil {
	@Value("${jwt.salt}")
	private String salt;

	@Value("${jwt.access-token.expiretime}")
	private long accessTokenExpireTime;

	@Value("${jwt.refresh-token.expiretime}")
	private long refreshTokenExpireTime;

	public String createAccessToken(int userId) {
		return create(userId, "access-token", accessTokenExpireTime);
	}

	public String createRefreshToken(int userId) {
		return create(userId, "refresh-token", refreshTokenExpireTime);
	}

	// Token 발급
	// key : Claim에 셋팅될 key 값
	// value : Claim에 셋팅 될 data 값
	// subject : payload에 sub의 value로 들어갈 subject값
	// expire : 토큰 유효기간 설정을 위한 값
	// jwt 토큰의 구성 : header + payload + signature
	private String create(int userId, String subject, long expireTime) {
		Claims claims = Jwts.claims()
				.setSubject(subject)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expireTime));

		claims.put("userId", userId);

		String jwt = Jwts.builder()
				.setHeaderParam("typ", "JWT").setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact();

		return jwt;
	}

	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = salt.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (log.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				log.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}
		return key;
	}

	public boolean checkToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
			log.debug("claims: {}", claims);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public int getUserId(String token) {
		System.out.println("token : " + token);
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new UnAuthorizedException();
		}
		Map<String, Object> value = claims.getBody();
		log.info("value : {}", value);
		return Integer.parseInt(value.get("userId").toString());
	}
}