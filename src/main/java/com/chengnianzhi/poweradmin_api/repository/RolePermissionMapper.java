package com.chengnianzhi.poweradmin_api.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chengnianzhi.poweradmin_api.entity.RoleEntity;
import com.chengnianzhi.poweradmin_api.entity.RolePermissionEntity;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.util.List;

public interface RolePermissionMapper extends BaseMapper<RolePermissionMapper> {
    int AddRolePermission(RolePermissionEntity rolePermissionEntity);
    int DeleteRolePermission(RolePermissionEntity rolePermissionEntity);
    IPage<RolePermissionEntity> getByRoleId(long roleId);
    IPage<RolePermissionEntity> getByPermissionId(long permissionId);
}
