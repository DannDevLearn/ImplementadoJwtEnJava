package org.example;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class MainLastExample {

    private static final String SECRET_KEY = "90a26fa45f2ea405927fcce2fb730c788d020f80d26a683bd808172522a16337";

    public static void main(String[] args) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + 300_000);

        String authToken = Jwts.builder()
                .subject("Example")
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claim("Frase", "Santos Bacalaos es el Jefe")
                .signWith(getSignatureKey())
                .compact();

        System.out.println(authToken);
    }

    public static Key getSignatureKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }
}
