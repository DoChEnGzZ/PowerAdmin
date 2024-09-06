package com.chengnianzhi.poweradmin_api.entity;


import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String name;
    private String phone;
    private String email;
    private Integer status;
    private String createTime;
    private String updateTime;
    private String lastLoginTime;
    private String lastLoginIp;
    private Integer loginCount;
    private Integer roleId;
    private String avatar;
    private String description;
    private String department;
    private String position;
}
