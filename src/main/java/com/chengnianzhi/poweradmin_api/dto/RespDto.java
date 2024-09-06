package com.chengnianzhi.poweradmin_api.dto;

import com.chengnianzhi.poweradmin_api.infra.errorcode.ErrorCode;
import com.chengnianzhi.poweradmin_api.infra.errorcode.SystemErrorCode;
import com.chengnianzhi.poweradmin_api.utils.StrUtils;
import lombok.Data;


import com.fasterxml.jackson.annotation.JsonIgnore;

/*
* 通用请求返回类RespDto
* OK 正常请求
* SYSTEM_ERROR 系统异常
* PARAM_ERROR 参数错误
* */
@Data
public class RespDto<T> {

    private Integer code;
    private String msg;
    private T data;

    public RespDto(ErrorCode errorCode, String msg, T data) {
        this.code = errorCode.getCode();
        // 优先获取构造参数传入的msg，如果没有则获取ErrorCode中的msg
        if (StrUtils.hasLength(msg)) {
            this.msg = msg;
        } else {
            this.msg = errorCode.getMessage();
        }
        this.data = data;
    }

    private RespDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static <T> RespDto<T> ok() {
        return okMsg("");
    }

    public static <T> RespDto<T> ok(T data) {
        return ok(data, "");
    }

    public static <T> RespDto<T> ok(T data, String msg) {
        return new RespDto<>(SystemErrorCode.SUCCESS, msg, data);
    }


    public static <T> RespDto<T> okMsg(String msg) {
        return new RespDto<>(SystemErrorCode.SUCCESS, msg, null);
    }

    public static <T> RespDto<T> systemError(String msg) {
        return new RespDto<>(SystemErrorCode.SYSTEM_ERROR, msg, null);
    }

    public static <T> RespDto<T> systemError() {
        return systemError("");
    }

    public static <T> RespDto<T> paramError(String msg) {
        return new RespDto<>(SystemErrorCode.PARAM_ERROR, msg, null);
    }

    public static <T> RespDto<T> paramError() {
        return paramError("");
    }

    public static <T> RespDto<T> error(ErrorCode errorCode) {
        return errorMsg(errorCode, "");
    }

    public static <T> RespDto<T> errorMsg(ErrorCode errorCode, String msg) {
        return new RespDto<>(errorCode, msg, null);
    }

    @JsonIgnore
    public boolean isOk() {
        return this.code == SystemErrorCode.SUCCESS.getCode();
    }

    public boolean notOk() {
        return !this.isOk();
    }

    public RespDto copyError() {
        return new RespDto(this.code, this.msg);
    }
}

