package com.example.helpdev.security;

import com.nimbusds.jwt.JWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static final long serialVersionUID = 1L;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis()  + expiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
                .compact();
    }

    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expiDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if (username != null && expiDate != null && now.before(expiDate)) {
                return true;
            }
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        } else {
            return null;
        }
    }

}
