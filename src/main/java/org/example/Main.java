package org.example;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final String SECRET_KEY = "dc075136a8ccb688b8cbc0900ecc4aa59ee3da527090c00bf58888f6771eef98"; // Clave secreta para firmar
    private static final String AES_KEY = "iTUCc/VCeBYGIx/11pVtMcnq8O3pFUFQ6RjbhE8+tmE="; // Clave AES para cifrado (en base64)

    public static void main(String[] args) {
        try {
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("name", "Santos Bacalaos");
            extraClaims.put("lastName", "Es el jefe");

            Date issuedAt = new Date(System.currentTimeMillis());
            Date expiration = new Date(issuedAt.getTime() + 300_000);

            String token = Jwts.builder()
                    .issuer("Me")
                    .subject("Bob")
                    .audience().add("You").and()
                    .issuedAt(issuedAt)
                    .expiration(expiration)
                    .claims(extraClaims)
                    .claim("song","I want to be free")
                    .signWith(getSignatureKey()) // Firmando el JWT
                    .compact();

            System.out.println("JWT firmado: " + token);

            // Cifrar el JWT
            String encryptedJwt = encryptJwt(token, AES_KEY);
            System.out.println("JWT cifrado: " + encryptedJwt);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptJwt(String jwt, String base64AesKey) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(base64AesKey), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(jwt.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static SecretKey getSignatureKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
    }
}
