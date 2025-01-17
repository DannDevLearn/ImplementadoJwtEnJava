package creadojwe;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ExampleJwe {
    public static void main(String[] args) {

        String MY_AES = "039512D3BFAF3BE53DAA390F0B288D1C63B69150D38446CD76CA0D84844871AB";
        String sKey = "6d0de58a8b97eb46136fc6867e34e2f552af8cf325c19571c2e8b3687f7308b5";

        String jwe = Jwts.builder()
                .subject("Michael Scott")
                .claim("username", "Mike")
                //.signWith(getSK(sKey))
                .encryptWith(getSecretKey(MY_AES), Jwts.ENC.A192CBC_HS384)
                .compact();

        System.out.println(jwe);
        System.out.println(isValidToken(jwe));
    }

    private static SecretKey getSK(String k){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(k));
    }

    private static SecretKey getSecretKey(String myKey){
        return new SecretKeySpec(Decoders.BASE64.decode(myKey), "AES");
    }

    private static boolean isValidToken(String token){
        String MY_AES = "039512D3BFAF3BE53DAA390F0B288D1C63B69150D38446CD76CA0D84844871AB";
        Jwe<Claims> jwe;
        try{
            jwe = Jwts.parser()
                    .decryptWith(getSecretKey(MY_AES))
                    .build()
                    .parseEncryptedClaims(token);
            System.out.println(jwe.getPayload());
            return true;
        }catch (JwtException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
