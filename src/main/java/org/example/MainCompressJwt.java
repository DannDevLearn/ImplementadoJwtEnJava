package org.example;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class MainCompressJwt {

    private static final String SECRET_KEY = "dc075136a8ccb688b8cbc0900ecc4aa59ee3da527090c00bf58888f6771eef98";

    public static void main(String[] args) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + 300_000);

        String authToken = Jwts.builder()
                .subject("Your account")
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(getSignatureKey())
//                .compressWith(Jwts.ZIP.DEF)
                .compact();

        System.out.println(authToken);

    }
    public static Key getSignatureKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }
}
