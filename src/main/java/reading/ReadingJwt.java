package reading;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;

public class ReadingJwt {

    private static final String SECRET_KEY = "941901ef3bfb07b10a36bf6861202d7cd2bec143cd4c73a4258de3949624fca5";

    public static void main(String[] args) {

        String authToken = CreateJwt.createToken();

        try{
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith((SecretKey) getSignature())
                    .build()
                    .parseSignedClaims(authToken);
            System.out.println(claims.getPayload().get("Frase del dia"));
        }catch (JwtException ex){
            System.out.println(ex.getMessage());
        }

    }

    private static Key getSignature(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }
}
