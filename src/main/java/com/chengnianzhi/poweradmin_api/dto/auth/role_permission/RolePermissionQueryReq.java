package com.chengnianzhi.poweradmin_api.dto.auth.role_permission;

import lombok.Data;

@Data
public class RolePermissionQueryReq {
    private long id;
    private long roleId;
    private long permissionId;
}
