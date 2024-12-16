package com.chengnianzhi.poweradmin_api.dto.user;

import com.chengnianzhi.poweradmin_api.constant.RegExpConst;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRegisterReq {
    @NotBlank(message = "用户名不能为空")
    @Size(max = 20, message = "用户名长度不能超过20")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = RegExpConst.PASSWORD, message = "密码长度在6到15之间，由大写字母、小写字母和数字构成")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank(message = "昵称不能为空")
    @Size(max = 20, message = "昵称长度不能超过20")
    private String nickName;
}
