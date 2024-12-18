package com.chengnianzhi.poweradmin_api.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengnianzhi.poweradmin_api.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {
    int AddRole(RoleEntity roleEntity);
    int UpdateRole(RoleEntity roleEntity);
    RoleEntity GetRoleById(long roleId);

}
