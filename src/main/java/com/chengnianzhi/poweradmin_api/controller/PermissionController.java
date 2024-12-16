package com.chengnianzhi.poweradmin_api.controller;

import com.chengnianzhi.poweradmin_api.dto.RespDto;
import com.chengnianzhi.poweradmin_api.dto.auth.PermissionDto;
import com.chengnianzhi.poweradmin_api.dto.auth.PermissionListDto;
import com.chengnianzhi.poweradmin_api.dto.auth.PermissionQueryReq;
import com.chengnianzhi.poweradmin_api.dto.common.table.TableResultGenerator;
import com.chengnianzhi.poweradmin_api.entity.PermissionEntity;
import com.chengnianzhi.poweradmin_api.service.auth.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permission/list")
    public RespDto<PermissionListDto> getPermissionList(@RequestBody PermissionQueryReq req) {
        var generator = new TableResultGenerator<PermissionDto, PermissionEntity>();
        var listDTO = new PermissionListDto();
        generator.list(() -> permissionService.query(req), listDTO, PermissionDto.class);
        return RespDto.ok(listDTO);
    }
}
