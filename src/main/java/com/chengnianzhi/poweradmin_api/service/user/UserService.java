package com.chengnianzhi.poweradmin_api.service.user;

import com.chengnianzhi.poweradmin_api.dto.RespDto;
import com.chengnianzhi.poweradmin_api.dto.user.*;
import com.chengnianzhi.poweradmin_api.infra.errorcode.SystemErrorCode;
import com.chengnianzhi.poweradmin_api.infra.exception.BusinessException;
import com.chengnianzhi.poweradmin_api.service.BaseService;
import com.chengnianzhi.poweradmin_api.utils.BcryptUtils;
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

    public RespDto<UserLoginDetails> login(UserLoginReq req) {
        UserLoginDetails resp = new UserLoginDetails();
        UserEntity user = getBaseMapper().getByUsername(req.getUsername());
        if ((user != null) && (BcryptUtils.matches(req.getPassword(), user.getPassword()))) {
            if (user.getDisabled()){
                return RespDto.error(SystemErrorCode.USER_STATUS_ERROR);
            }
            resp.getLoginUserBasicInfo().fill(user);
            return RespDto.ok(resp);
        } else {
            return RespDto.error(SystemErrorCode.USER_PASSWD_ERROR);
        }
    }

    public RespDto<UserRegisterDto> register(UserRegisterReq req) {
        // 判断用户是否已注册
        if (getBaseMapper().getByUsername(req.getUsername()) != null) {
            return RespDto.error(SystemErrorCode.USER_EXIST);
        }
        // 创建新用户
        UserEntity user = new UserEntity();
        user.setUsername(req.getUsername());
        user.setPassword(BcryptUtils.encode(req.getPassword()));
        user.setNickName(req.getNickName());
        user.setDisabled(false);
        if (getBaseMapper().insert(user) != 1) {
            return RespDto.error(SystemErrorCode.REGISTER_FAILED);
        }
        var resp = new UserRegisterDto();
        resp.fill(user);
        return RespDto.ok(resp);
    }
}
