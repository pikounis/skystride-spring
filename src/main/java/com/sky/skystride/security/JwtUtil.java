package com.sky.skystride.security;

import com.sky.skystride.dto.ProfileDTO;
import com.sky.skystride.entities.SkyUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.function.Function;


public class JwtUtil {
    private static final String SECRET_KEY = "just_move_to_next_slide";

    public Integer extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("userId", Integer.class);  // Extract the user ID from the token
    }

    public static String generateToken(String username, Integer userId){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .claim("userId", userId)  // Add user ID to the claims
                .compact();
    }

    public String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, ProfileDTO user){
        String email = extractEmail(token);
        return email.equals(user.getEmail()) && !isTokenExpired(token);
    }
}
