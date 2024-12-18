package com.chengnianzhi.poweradmin_api.service.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengnianzhi.poweradmin_api.dto.auth.Permission.PermissionAddReq;
import com.chengnianzhi.poweradmin_api.dto.auth.Permission.PermissionQueryReq;
import com.chengnianzhi.poweradmin_api.dto.auth.Permission.PermissionUpdateReq;
import com.chengnianzhi.poweradmin_api.entity.PermissionEntity;
import com.chengnianzhi.poweradmin_api.repository.PermissionMapper;
import com.chengnianzhi.poweradmin_api.service.BaseService;
import com.chengnianzhi.poweradmin_api.utils.BeanUtils;
import com.chengnianzhi.poweradmin_api.utils.StrUtils;
import org.springframework.stereotype.Service;

@Service
public class PermissionService extends BaseService<PermissionMapper, PermissionEntity> {

    public boolean add(PermissionAddReq req){
        return getBaseMapper().insert(BeanUtils.copy(req, getEntityClass())) > 0;
    }

    public IPage<PermissionEntity> query(PermissionQueryReq req) {
        LambdaQueryWrapper<PermissionEntity> param = Wrappers.lambdaQuery();
        if (req.getId() != 0) {
            param.eq(PermissionEntity::getId, req.getId());
        }
        if (StrUtils.hasLength(req.getName())) {
            param.like(PermissionEntity::getName, req.getName());
        }
        if (StrUtils.hasLength(req.getType())) {
            param.eq(PermissionEntity::getType, req.getType());
        }
        param.orderByDesc(PermissionEntity::getCreateTime);
        var page = Page.<PermissionEntity>of(req.getPage(), req.getSize());
        return this.page(page, param);
    }

    // 更新记录
    public boolean update(PermissionUpdateReq req) {
        Assert.notNull(req.getId(), "id is required");
        var entity = BeanUtils.copy(req, PermissionEntity.class);
        return this.getBaseMapper().updateById(entity) == 1;
    }
}
