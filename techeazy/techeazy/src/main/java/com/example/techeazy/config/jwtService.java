package com.example.techeazy.config;

import com.example.techeazy.entities.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;


@Service
public class jwtService {

    @Value("${security.jwt.expiration-minutes}")
    private long Expiration_Minutes;

    @Value("${security.jwt.secret-key}")
    private  String SECRET_KEY;

    public String generateToken(Student user, Map<String, Object> extraClaims){
        Date issuedAt=new Date(System.currentTimeMillis());
        Date expiration=new Date(issuedAt.getTime()+(Expiration_Minutes*60*1000));

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key generateKey(){

        byte[] secretAsBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretAsBytes);
    }
    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }
    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody();
    }


}
