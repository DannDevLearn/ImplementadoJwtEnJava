package yosolito;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
//import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
//import java.util.Base64;

public class VerificandoYClaims {

    private static final String SECRET_KEY = "fa0c914883bcae4b4532015b1cbd2d14c4d1e30197e8afa1c1c0d921f90afd09";

    public static boolean isValidToken(String authToken){

//        byte[] SKBytes = Base64.getDecoder().decode(SECRET_KEY);
//        SecretKey secretKey = new SecretKeySpec(SKBytes, "HmacSHA384");

        try{
            Jwts.parser()
                    .verifyWith((SecretKey) getSignatureKey())
                    .build()
                    .parseSignedClaims(authToken);

            System.out.println("Token valido!");
            return true;
        }catch (MalformedJwtException e){
        System.out.println(e.getMessage());
        }catch (ExpiredJwtException e){
            System.out.println(e.getMessage());
        }catch (UnsupportedJwtException e){
            System.out.println(e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    private static Key getSignatureKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

}
