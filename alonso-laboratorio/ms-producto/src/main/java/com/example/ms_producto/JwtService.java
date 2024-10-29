package com.example.ms_producto;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Service

public class JwtService {



    @Value("${security.jwt.secret-key}")

    private String secretKey;



    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extraxtAllClaims(token);

        return claimsResolver.apply(claims);

    }



    public boolean isTokenValid(String token) {

        return !isTokenExpired(token);

    }



    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());

    }



    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);

    }



    private Claims extraxtAllClaims(String token) {

        return Jwts

                .parserBuilder()

                .setSigningKey(getSignInKey())

                .build()

                .parseClaimsJws(token)

                .getBody();

    }



    private Key getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);

    }



}