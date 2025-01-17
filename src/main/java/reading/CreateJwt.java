package reading;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateJwt {

    private static final String SECRET_KEY = "941901ef3bfb07b10a36bf6861202d7cd2bec143cd4c73a4258de3949624fca5";

    public static String createToken(){
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + 360_000);

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("Frase del dia", "Santos bacalaons es el jefe");
        extraClaims.put("Cancion", "Las del Nata");
        extraClaims.put("Total", 9000);

        return Jwts.builder()
                .subject("Ejemplo Token")
                .claims(extraClaims)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(getSignature())
                .compact();
    }

    private static Key getSignature(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

}
