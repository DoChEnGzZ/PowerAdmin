package com.chengnianzhi.poweradmin_api.controller;

import com.chengnianzhi.poweradmin_api.dto.RespDto;
import com.chengnianzhi.poweradmin_api.dto.auth.Role.RoleDto;
import com.chengnianzhi.poweradmin_api.service.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(method = {RequestMethod.GET}, path = "/test")
    public String test() {
        return "test";
    }

    @GetMapping("/get")
    public RespDto<RoleDto> getRoleById(@RequestParam("id") Long id) {
        return roleService.GetById(id);
    }
}
