package com.webAPI.training.JWT;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.executable.ValidateOnExecution;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import static java.util.Collections.emptyList;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtTokenCreator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static final String secret = "baobao";
	
	static final String TOKEN_PREFIX_STRING = "Bearer ";
	static final String HEADER_STRING = "Authorization";
    private final static long JWT_EXPIRATION = 604800000L;
	
	public static void addAuthentication(HttpServletResponse res, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);


		String JWT = Jwts.builder().setSubject(username).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256, secret).compact();
		
		res.addHeader(HEADER_STRING, TOKEN_PREFIX_STRING + " " + JWT);
	}

	public static Authentication getAuthentication(HttpServletRequest req) {
		
		String tokenString = req.getHeader(HEADER_STRING);
		if(tokenString!= null) {
			String user = Jwts.parser().setSigningKey(secret).parseClaimsJws(tokenString.replace(TOKEN_PREFIX_STRING," ")).getBody().getSubject();
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) :null;
		}
		return null;
		
	}

}
