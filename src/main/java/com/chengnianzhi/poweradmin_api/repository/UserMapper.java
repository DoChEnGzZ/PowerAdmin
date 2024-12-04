package com.chengnianzhi.poweradmin_api.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengnianzhi.poweradmin_api.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    UserEntity getByUsername(String username);
}

