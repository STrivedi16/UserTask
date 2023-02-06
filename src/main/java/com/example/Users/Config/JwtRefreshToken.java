package com.example.Users.Config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtRefreshToken implements Serializable {

	private final long JWT_Refresh_token_validity = 86400000; // (24*60*60*1000) to reduce the milisecond

	public static Boolean ref = true;

	private final String TYPE = "refresh";

	@Value("${jwt.secret}")
	private String secret;

	public String getUsernameFromToken(String reftoken) {
		return getClaimsFromReftoken(reftoken, Claims::getSubject);
	}

	public java.util.Date getExpirationDate(String reftoken) {
		return getClaimsFromReftoken(reftoken, Claims::getExpiration);

	}

	public String getTypeFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return (String) claims.get("Type");
	}

	public <T> T getClaimsFromReftoken(String reftoken, Function<Claims, T> claimresolver) {
		final Claims claims = getAllClaimsFromReftoken(reftoken);

		return claimresolver.apply(claims);
	}

	public Claims getAllClaimsFromReftoken(String reftoken) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(reftoken).getBody();
	}

	public String generatereftoken(UserDetails details) {

		HashMap<String, Object> claims = new HashMap<>();

		claims.put("Type", TYPE);
		return dogenerate(claims, details.getUsername());

	}

	// .claim("Type", Type)
	private String dogenerate(Map<String, Object> claims, String object) {
		return Jwts.builder().setClaims(claims).setSubject(object).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_Refresh_token_validity * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}
