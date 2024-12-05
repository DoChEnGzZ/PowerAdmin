package com.chengnianzhi.poweradmin_api.service.token;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.chengnianzhi.poweradmin_api.constant.JwtConstant;
import com.chengnianzhi.poweradmin_api.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Slf4j
@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expire-minutes}")
    private long expireMinutes;

    // 验证JWT Token，如果成功就返回uid，否则会抛异常
    // 这些JWT相关的异常，在GlobalExceptionHandler会捕捉
    public Long verifyThenGetUid(String token) {
        var decodedJWT = JWTUtils.verify(token, secret);
        return decodedJWT.getClaim(JwtConstant.CLAIM_KEY_UID).asLong();
    }

    // 创建JWT Token，并将uid和username编码到token中
    public String createToken(long uid, String username) {
        var token = JWTUtils.create(secret, expireMinutes,
                builder -> builder
                        .withClaim(JwtConstant.CLAIM_KEY_UID, uid)
                        .withClaim(JwtConstant.CLAIM_KEY_USERNAME, username)
        );
        return token;
    }
}
