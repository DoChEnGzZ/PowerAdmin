package com.chengnianzhi.poweradmin_api.dto;

import lombok.Data;

@Data
public class UserFullInfoDTO {
    private Long uid;
    private String username;
    private String nickName;
    /* 其它权限相关的字段，暂时先留空 */

//    // 工具方法，用于获取当前登录的用户详情
//    public static UserFullInfoDTO currentUser() {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null) {
//            return null;
//        }
//        if (!(authentication.getDetails() instanceof UserFullInfoDTO)) {
//            return null;
//        }
//        return (UserFullInfoDTO) authentication.getDetails();
//    }
}
