package com.chengnianzhi.poweradmin_api.infra.global;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.chengnianzhi.poweradmin_api.dto.RespDto;
import com.chengnianzhi.poweradmin_api.infra.errorcode.SystemErrorCode;
import com.chengnianzhi.poweradmin_api.infra.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 捕获业务异常
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<RespDto<?>> businessExceptionHandler(HttpServletRequest req, BusinessException e) {
        log.error("BusinessException: {}", e.getErrorCode(), e);
        if (e.getErrorCode() != null) {
            return ResponseEntity.ok(RespDto.error(e.getErrorCode()));
        } else {
            return ResponseEntity.ok(RespDto.systemError());
        }
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseEntity<RespDto<?>> tokenExpire(HttpServletRequest request, Throwable e) {
        log.error("tokenExpired", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(RespDto.error(SystemErrorCode.INVALID_TOKEN));
    }

    @ExceptionHandler(value = JWTDecodeException.class)
    public ResponseEntity<RespDto<?>> jwtDecodeException(HttpServletRequest request, Throwable e) {
        log.error("jwtDecodeException", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(RespDto.errorMsg(SystemErrorCode.NO_PERMISSION,"token格式错误"));
    }
}
