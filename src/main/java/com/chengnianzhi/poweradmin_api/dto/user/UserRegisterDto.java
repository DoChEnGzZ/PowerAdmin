package com.chengnianzhi.poweradmin_api.dto.user;

import com.chengnianzhi.poweradmin_api.entity.UserEntity;
import lombok.Data;

@Data
public class UserRegisterDto {
    private Long uid;
    private String username;
    private String nickName;
    private boolean disabled;

    public void fill(UserEntity user) {
        this.uid = user.getUid();
        this.username = user.getUsername();
        this.disabled = user.getDisabled();
        this.nickName = user.getNickName();
    }
}
