package com.chengnianzhi.poweradmin_api.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("user")
public class UserEntity {
    @TableId(type = IdType.AUTO)
    private Long uid;
    private String username;
    private String password;
    private String nickName;
    private Boolean disabled;
    @TableField(fill = FieldFill.INSERT) // 表示需要在insert时自动填充
    private Long createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE) // 表示需要在insert和update里都自动填充
    private Long updateTime;
}