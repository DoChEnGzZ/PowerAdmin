package com.chengnianzhi.poweradmin_api.infra.global;

import com.chengnianzhi.poweradmin_api.dto.RespDto;
import com.chengnianzhi.poweradmin_api.infra.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 捕获业务异常
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<RespDto<?>> businessExceptionHandler(HttpServletRequest req, BusinessException e) {
        log.error("BusinessException: {}", e.getErrorCode(), e);
        if (e.getErrorCode() != null) {
            return ResponseEntity.ok(RespDto.error(e.getErrorCode()));
        }else {
            return ResponseEntity.ok(RespDto.systemError());
        }
    }
}
