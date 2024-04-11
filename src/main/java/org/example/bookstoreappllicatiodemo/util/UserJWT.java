package org.example.bookstoreappllicatiodemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
public class UserJWT {
    private static final String SECRET_KEY = "jackandjill";

    public String createToken(Long id) {
        return JWT.create()
                .withClaim("id", id)
                .sign(Algorithm.HMAC256(Base64.getEncoder().encode(SECRET_KEY.getBytes())));
    }

    public Long decodeToken(String token) {
        try {
            long userId = JWT.require(Algorithm.HMAC256(Base64.getEncoder().encode(SECRET_KEY.getBytes())))
                    .build()
                    .verify(token)
                    .getClaim("id")
                    .asLong();
           return userId;
        } catch (Exception e) {
            // Handle token decoding exception
            return null;
        }
    }

}
