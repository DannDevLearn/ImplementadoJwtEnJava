package nuevoalgoritmo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

public class ConRsa {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        long start = System.nanoTime();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        String token = Jwts.builder()
                .subject("Example RSA")
                .claim("Frase", "Santos bacalaos es el jefe")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 300_000))
                .signWith(keyPair.getPrivate())
//                .signWith(keyPair.getPrivate(), Jwts.SIG.RS512)
                .compact();

        System.out.println("JWT: " +token);

        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicK = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));


        try {
            // Validar y analizar el token
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(publicK) // Establecer la clave pública
                    .build()
                    .parseSignedClaims(token); // Analizar el token

            // Extraer claims
            Claims claims = claimsJws.getBody();
            System.out.println("Token válido");
            System.out.println("Claims: " + claims);
        } catch (Exception e) {
            System.out.println("Token inválido: " + e.getMessage());
        }

        long end = System.nanoTime();

        System.out.println(end - start);
    }
}
