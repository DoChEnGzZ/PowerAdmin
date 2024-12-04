package com.chengnianzhi.poweradmin_api.controller;

import com.chengnianzhi.poweradmin_api.dto.RespDto;
import com.chengnianzhi.poweradmin_api.dto.UserFullInfoDTO;
import com.chengnianzhi.poweradmin_api.entity.UserEntity;
import com.chengnianzhi.poweradmin_api.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getuserinfo")
    public RespDto<UserFullInfoDTO> getUserData(@RequestParam Long uid) {
        return userService.getUserData(uid);
    }

}
