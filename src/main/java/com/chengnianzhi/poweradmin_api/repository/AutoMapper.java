package com.chengnianzhi.poweradmin_api.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengnianzhi.poweradmin_api.entity.PermissionEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AutoMapper extends BaseMapper<PermissionEntity> {
}
