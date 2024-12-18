package com.chengnianzhi.poweradmin_api.dto.auth.Permission;

import com.chengnianzhi.poweradmin_api.constant.PermissionType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PermissionUpdateReq {
    @NotNull(message = "权限id不能为空")
    private long id;
    private PermissionType type;
    @Size(max = 20, message = "权限名称长度不能超过20")
    private String name;
    @Size(max = 100, message = "权限描述长度不能超过100")
    private String description;
}
