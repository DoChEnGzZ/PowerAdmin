package com.chengnianzhi.poweradmin_api.dto.auth.Role;

import com.chengnianzhi.poweradmin_api.constant.ModuleName;
import com.chengnianzhi.poweradmin_api.dto.common.table.AbstractListResult;
import com.chengnianzhi.poweradmin_api.dto.common.table.property.ActionProperty;
import com.chengnianzhi.poweradmin_api.dto.common.table.property.FieldProperty;
import com.chengnianzhi.poweradmin_api.dto.common.table.property.RuleProperty;
import com.chengnianzhi.poweradmin_api.entity.RoleEntity;
import com.chengnianzhi.poweradmin_api.utils.BeanUtils;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
//@ActionProperty(module = ModuleName.ROLE)
public class RoleDto {
    private long id;
//    @FieldProperty(title = "名称", canEdit = true, searchable = true)
//    @RuleProperty(required = true)
    private String name;
//    @FieldProperty(title = "展示名称", canEdit = true, searchable = true)
//    @RuleProperty(required = true)
    private String description;
    private Long createTime;
    private Long updateTime;

    public static RoleDto fill(RoleEntity entity) {
        return BeanUtils.copy(entity, RoleDto.class);
    }
}

