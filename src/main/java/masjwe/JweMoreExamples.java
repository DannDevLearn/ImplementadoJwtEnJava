package masjwe;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwe;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.AeadAlgorithm;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

public class JweMoreExamples {
    public static void main(String[] args) {
        AeadAlgorithm enc = Jwts.ENC.A256GCM;
        SecretKey secretKey = enc.key().build();

        Map<String, Object> claims = new HashMap<>();
        claims.put("Username", "Michael Scott");
        claims.put("Age", 30);

        String jwe =  Jwts.builder().claims(claims).encryptWith(secretKey, enc).compact();

        System.out.println(jwe);




        System.out.println(getClaim(jwe, "Username", secretKey));
    }

    private static String getClaim(String authToken, String nameClaim, SecretKey secretKey){
        Claims payload = Jwts.parser()
                .decryptWith(secretKey)
                .build()
                .parseEncryptedClaims(authToken)
                .getPayload();

        return payload.get(nameClaim).toString();
    }
}
