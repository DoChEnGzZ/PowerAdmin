package com.chengnianzhi.poweradmin_api.dto.auth.Role;

import lombok.Data;

@Data
public class RoleUpdateReq {
    private long id;
    private String name;
    private String description;
}
