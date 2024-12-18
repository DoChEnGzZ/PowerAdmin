package com.chengnianzhi.poweradmin_api.dto.auth.role_permission;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RolePermissionAddReq {
    @NotNull
    private long roleId;
    @NotNull
    private long permissionId;
}
