package com.chengnianzhi.poweradmin_api.dto.auth.Permission;

import com.chengnianzhi.poweradmin_api.dto.common.table.AbstractPagination;
import lombok.Data;

@Data
public class PermissionQueryReq extends AbstractPagination {
    private long id;
    private String name;
    private String type;
}
