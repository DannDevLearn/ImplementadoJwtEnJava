package yosolito;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static final String SECRET_KEY = "fa0c914883bcae4b4532015b1cbd2d14c4d1e30197e8afa1c1c0d921f90afd09";
    private static final String AES_KEY = "iTUCc/VCeBYGIx/11pVtMcnq8O3pFUFQ6RjbhE8+tmE=";

    public static String getToken(){
        return createToken();
    }

    private static String createToken(){
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + 300_000);
        Map<String, Object> claims = new HashMap<>();
        claims.put("Frase", "Santos bacalaos es el Jefe");
        claims.put("Edad", 30);

        Header header = Jwts.header()
                .add("Cancion", "Heridas By Caifanes")
                .build();

        String message = "It's beautiful day";
        byte[] content = message.getBytes(StandardCharsets.UTF_8);
        return Jwts.builder()
                .header()
                .add(header)
                    .and()
                .subject("Uncle Bob")
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(claims)
                .signWith(getSignatureKey())
                .compact();

//        try {
//            return encryptJwt(token, AES_KEY);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }

    public static String encryptJwt(String jwt, String base64AesKey) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(base64AesKey), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(jwt.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static Key getSignatureKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

}
