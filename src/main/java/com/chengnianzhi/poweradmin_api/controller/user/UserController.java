package com.chengnianzhi.poweradmin_api.controller.user;

import com.chengnianzhi.poweradmin_api.dto.RespDto;
import com.chengnianzhi.poweradmin_api.dto.user.UserFullInfoDTO;
import com.chengnianzhi.poweradmin_api.dto.user.UserLoginDetails;
import com.chengnianzhi.poweradmin_api.dto.user.UserLoginDto;
import com.chengnianzhi.poweradmin_api.dto.user.UserLoginReq;
import com.chengnianzhi.poweradmin_api.service.token.TokenService;
import com.chengnianzhi.poweradmin_api.service.user.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping("/getuserinfo")
    public RespDto<UserFullInfoDTO> getUserData(@RequestParam Long uid) {
        return userService.getUserData(uid);
    }

    @PostMapping("/login")
    public RespDto<UserLoginDto> login(@RequestBody UserLoginReq req){
        RespDto<UserLoginDetails> resp = userService.login(req);
        if (resp.notOk()) {
            return resp.copyError();
        }else {
            UserLoginDto userInfo = resp.getData().getLoginUserBasicInfo();
            var token = tokenService.createToken(userInfo.getUid(), userInfo.getUsername());
            userInfo.setToken(token);
            return RespDto.ok(userInfo);
        }
    }

    @GetMapping("/current_user")
    public RespDto<UserFullInfoDTO> currentUser() {
        return RespDto.ok(UserFullInfoDTO.currentUser());
    }
}
