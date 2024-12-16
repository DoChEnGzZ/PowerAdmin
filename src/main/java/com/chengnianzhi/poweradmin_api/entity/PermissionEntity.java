package com.chengnianzhi.poweradmin_api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.chengnianzhi.poweradmin_api.constant.PermissionType;
import lombok.Data;

@Data
@TableName("permission")
public class PermissionEntity {
    @TableId(type = IdType.AUTO)
    private long id;
    private PermissionType type;
    private String name;
    private String description;
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
