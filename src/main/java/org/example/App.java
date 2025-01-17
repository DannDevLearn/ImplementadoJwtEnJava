package org.example;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class App{

    private static final String SECRET_KEY = "a3b759003b53313664c0d21877265687975cb32f0709747a6ac14a20cf458843";

    public static void main( String[] args ) {

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name","Santos Bacalaos");
        extraClaims.put("lastName", "Es el jefe");

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + 300_000);



        String token = Jwts.builder()
                .subject("10ienteDann")
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(extraClaims)
                .signWith(getSignatureKey())
                .compact();

        System.out.println(token);
    }
    public static Key getSignatureKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }
}
