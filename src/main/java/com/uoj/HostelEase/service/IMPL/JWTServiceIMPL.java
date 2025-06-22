package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.service.JWTService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JWTServiceIMPL implements JWTService  {
    private final SecretKey secretKey;

    public JWTServiceIMPL() {
        try {
            SecretKey k = KeyGenerator.getInstance("HmacSHA256").generateKey();
            secretKey= Keys.hmacShaKeyFor(k.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String jwtToken(String reg_no, Map<String,String> clams) {
        return Jwts.builder()
                .claims(clams)
                .subject(reg_no)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(secretKey)
                .compact();
    }

    @Override
    public String getRegNo(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();

        }catch (Exception e){
            System.err.println("Token parsing failed: " + e.getMessage());
            return null;
        }
    }
}