package com.example.Users.Config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	@Autowired
	private UsersRepository repository;

	private static final long serialVersionUID = -2550185165626007488L;

	private static final long JWT_TOEKN_VALIDITY = 5 * 60 * 60;

	private static final String TYPE = "Access";

//	private String secret = "Java@2711";

	@Value("${jwt.secret}")
	private String secret;

	// Get USername from token

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// get Exrationdate From Token

	public Date getExpriationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);

	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver)

	{
		final Claims claims = getAllClaimsFromToken(token);

		return claimResolver.apply(claims);
	}

	// FROM RETRIEVING ANY INFORMATION FROM TOKEN WE WILL NEED SECRET KEY

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// TO GET THE TYPE FROM TOKEN
	public String getTypeFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return (String) claims.get("Type");
	}

	// CHECK THE TOKEN IS EXPIRED OR NOT
	public boolean isTokenExpire(String token) {
		final Date expiration = getExpriationDateFromToken(token);

		return expiration.before(new Date());
	}

	public String generateToken(UserDetails details) {
		HashMap<String, Object> claims = new HashMap<>();

		Users employee = this.repository.findByEmailIgnoreCase(details.getUsername());

		claims.put("Type", TYPE);

		
		claims.put("UserName", employee.getUsername());
		return doGenerate(claims, details.getUsername());
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string

	private String doGenerate(Map<String, Object> claims, String object) {
		return Jwts.builder().setClaims(claims).setSubject(object).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOEKN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

	}

	// TO CHECK VALIDATE TOKEN

	public boolean validateToken(String token, UserDetails details) {
		final String username = getUsernameFromToken(token);

		return (username.equals(details.getUsername()) && !isTokenExpire(token));
	}

}
