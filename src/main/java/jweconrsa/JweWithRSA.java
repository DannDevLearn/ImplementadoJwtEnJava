package jweconrsa;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.KeyAlgorithm;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class JweWithRSA {
    public static void main(String[] args) {
        KeyPair keyPair = Jwts.SIG.RS512.keyPair().build();
        KeyAlgorithm<PublicKey, PrivateKey> algo = Jwts.KEY.RSA_OAEP_256;

        AeadAlgorithm enc = Jwts.ENC.A256GCM;


        String jwe = Jwts.builder()
                .claim("username", "Erin")
                .encryptWith(keyPair.getPublic(), algo, enc)
                .compact();
        System.out.println(jwe);

        Claims claims = Jwts.parser()
                .decryptWith(keyPair.getPrivate())
                .build()
                .parseEncryptedClaims(jwe)
                .getPayload();
        System.out.println(claims.get("username"));
    }
}
