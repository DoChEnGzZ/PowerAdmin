package com.chengnianzhi.poweradmin_api.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.LocalDateTime;
import java.util.function.Consumer;

public class JWTUtils {
    public static final String SECRET = "salt";

    // 创建token
    public static String create(String secret, long expireMinutes, Consumer<JWTCreator.Builder> customClaim){
        LocalDateTime time = LocalDateTime.now();
        Algorithm algorithm = com.auth0.jwt.algorithms.Algorithm.HMAC512(secret);
        JWTCreator.Builder builder = JWT.create()
                .withIssuer(SECRET).withIssuedAt(TimeUtils.toDate(time)).withExpiresAt(TimeUtils.toDate(time.plusMinutes(expireMinutes)));
        customClaim.accept(builder);
        return builder.sign(algorithm);
    }

    public static DecodedJWT verify(String token, String secret){
        Algorithm algorithm = Algorithm.HMAC512(secret);
        return JWT.require(algorithm).build().verify(token);
    }

}
