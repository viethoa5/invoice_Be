package com.example.invoice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${spring.secretkey}")
    private String accessKey;
    @Value("${spring.expiredTime}")
    private Long expriedTime;
    @Value("${spring.refresh.secret-key}")
    private String refreshKey;
    @Value("${spring.refresh.expiration}")
    private Long expriedRefreshTime;
    public String extractUsernameFromAccessToken(String token) {
        return extractClaim(token, Claims::getSubject, accessKey);
    }
    public String extractUsernameFromRefreshToken(String token) {
        return extractClaim(token, Claims::getSubject, refreshKey);
    }
    public String generateRefreshToken(UserDetails userDetails) {
        return  generateRefreshToken(new HashMap<>(), userDetails);
    }
    private String generateRefreshToken(Map<String, Objects> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, refreshKey, expriedRefreshTime);
    }
    public String generateAccessToken(UserDetails userDetails) {
        return  generateAccessToken(new HashMap<>(), userDetails);
    }
    public boolean isAccessTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsernameFromAccessToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpried(token, accessKey);
    }

    public boolean isRefreshTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsernameFromRefreshToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpried(token, refreshKey);
    }
    private String generateAccessToken(Map<String, Objects> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, accessKey, expriedTime);
    }
    private boolean isTokenExpried(String token, String key) {
        return extractExpirationFromAccessToken(token, key).before(new Date());
    }
    private Date extractExpirationFromAccessToken(String token, String key) {
        return extractClaim(token, Claims::getExpiration, key);
    }
    private String buildToken(Map<String, Objects> extraClaims, UserDetails userDetails, String key, long expriedTime) {
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expriedTime))
                .signWith(getSignInKey(key), SignatureAlgorithm.HS256)
                .compact();
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, String key) {
        final Claims claims = extraAllClaims(token, key);
        return claimsResolver.apply(claims);
    }
    private Claims extraAllClaims(String token, String key) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey(key))
                .build()
                .parseClaimsJws(token).getBody();
    }
    private Key getSignInKey(String key) {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
