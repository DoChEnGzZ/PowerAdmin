package com.chengnianzhi.poweradmin_api.service.user;

import com.chengnianzhi.poweradmin_api.dto.RespDto;
import com.chengnianzhi.poweradmin_api.dto.UserFullInfoDTO;
import com.chengnianzhi.poweradmin_api.infra.errorcode.SystemErrorCode;
import com.chengnianzhi.poweradmin_api.infra.exception.BusinessException;
import com.chengnianzhi.poweradmin_api.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.chengnianzhi.poweradmin_api.entity.UserEntity;
import com.chengnianzhi.poweradmin_api.repository.UserMapper;

@Slf4j
@Service
public class UserService extends BaseService<UserMapper, UserEntity> {
    public RespDto<UserFullInfoDTO> getUserData(Long uid) {
        UserEntity userEntity = getById(uid);
        if (userEntity == null) {
            return RespDto.errorMsg(SystemErrorCode.DATA_NOT_EXIST, "用户不存在");
        } else {
            if (userEntity.getDisabled()) {
                throw new BusinessException(SystemErrorCode.USER_STATUS_ERROR);
            }
            var userData = new UserFullInfoDTO();
            userData.setUid(uid);
            userData.setUsername(userEntity.getUsername());
            userData.setNickName(userEntity.getNickName());
            // 角色和权限部分需要留到后面完成角色和权限模块之后
            // fillRoleAndPermission(userData);
            return RespDto.ok(userData);
        }
    }
}
