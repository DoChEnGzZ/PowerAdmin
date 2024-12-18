package com.chengnianzhi.poweradmin_api.service.role;

import com.chengnianzhi.poweradmin_api.dto.RespDto;
import com.chengnianzhi.poweradmin_api.dto.auth.Role.RoleDto;
import com.chengnianzhi.poweradmin_api.entity.RoleEntity;
import com.chengnianzhi.poweradmin_api.infra.errorcode.SystemErrorCode;
import com.chengnianzhi.poweradmin_api.repository.RoleMapper;
import com.chengnianzhi.poweradmin_api.service.BaseService;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class RoleService extends BaseService<RoleMapper, RoleEntity> {
    public RespDto<RoleDto> GetById(long id) {
        RoleEntity roleEntity = baseMapper.GetRoleById(id);
        if (roleEntity == null) {
            return RespDto.error(SystemErrorCode.ROLE_NOT_EXIST);
        }
        RoleDto resp = new RoleDto();
        RoleDto.fill(roleEntity);
        return RespDto.ok(resp);
    }
}
