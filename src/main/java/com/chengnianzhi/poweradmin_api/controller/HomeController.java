package com.chengnianzhi.poweradmin_api.controller;

import com.chengnianzhi.poweradmin_api.dto.RespDto;
import com.chengnianzhi.poweradmin_api.entity.User;
import com.chengnianzhi.poweradmin_api.infra.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @Value("${welcome.content}")
    private String welcome;

    @RequestMapping("/")
    public RespDto<User> home() {
        log.info("Welcome: {}", welcome);
        return RespDto.ok(new User());
    }

    @RequestMapping("/system_error")
    public RespDto<String> systemError() {
        return RespDto.systemError("test error");
    }

    @RequestMapping("/business_error")
    public RespDto<String> businessError() {
        throw new BusinessException("test error");
    }
}
