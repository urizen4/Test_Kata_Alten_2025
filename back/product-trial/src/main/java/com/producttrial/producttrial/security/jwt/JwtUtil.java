package com.producttrial.producttrial.security.jwt;



import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil
{
    private static final String SECRET_KEY = "alten-kata-2025-@Aidar4-isAwesome!UseThisKey4JwtOnly#Secure";

    private Key getSignInKey()
    {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Génère un token avec les claims personnalisés
    public String generateToken(String email)
    {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 heures pour l'expiration du token
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extraire le subject (email ici)
    public String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    // Vérifie si le token est valide
    public boolean isTokenValid(String token, String userEmail)
    {
        final String username = extractUsername(token);
        return (username.equals(userEmail)) && !isTokenExpired(token);
    }

    // Vérification du token expiré ?
    private boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    // Date d'expiration du token
    private Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    // Méthode générique pour extraire une info depuis le token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extraction brute des claims
    private Claims extractAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
