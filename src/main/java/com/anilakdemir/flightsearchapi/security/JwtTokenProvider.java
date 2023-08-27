package com.anilakdemir.flightsearchapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * @author anilakdemir
 */
@Component
public class JwtTokenProvider {

    @Value("${flightsearch.jwt.security.app.key}")
    private String APP_KEY;

    @Value("${flightsearch.jwt.security.expire.time}")
    private Long EXPIRE_TIME;

    public String generateJwtToken(Authentication authentication) {

        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(jwtUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsernameFromToken(String token) {

        Jws<Claims> claimsJws = parseToken(token);

        return claimsJws
                .getBody()
                .getSubject();
    }

    private Jws<Claims> parseToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token);
        return claimsJws;
    }

    public boolean validateToken(String token) {

        boolean isValid;

        try {
            Jws<Claims> claimsJws = parseToken(token);

            isValid = !isTokenExpired(claimsJws);
        } catch (Exception e) {
            isValid = false;
        }

        return isValid;
    }

    private boolean isTokenExpired(Jws<Claims> claimsJws) {

        Date expirationDate = claimsJws.getBody().getExpiration();

        return expirationDate.before(new Date());
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(APP_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
