package com.chengnianzhi.poweradmin_api.dto.auth;

import com.chengnianzhi.poweradmin_api.constant.ModuleName;
import com.chengnianzhi.poweradmin_api.constant.PermissionType;
import com.chengnianzhi.poweradmin_api.dto.common.table.property.ActionProperty;
import com.chengnianzhi.poweradmin_api.dto.common.table.property.FieldProperty;
import com.chengnianzhi.poweradmin_api.dto.common.table.property.RuleProperty;
import com.chengnianzhi.poweradmin_api.dto.common.table.property.SelectOptionProperty;
import com.chengnianzhi.poweradmin_api.entity.PermissionEntity;
import com.chengnianzhi.poweradmin_api.utils.BeanUtils;
import lombok.Data;

import static com.chengnianzhi.poweradmin_api.constant.PermissionType.SelectOption.*;
import static com.chengnianzhi.poweradmin_api.dto.common.table.TableFieldType.EnumNumber;


@Data
@ActionProperty(module = ModuleName.PERMISSION)
public class PermissionDto {
    private Long id;
    @FieldProperty(title = "名称", canEdit = true, searchable = true)
    @RuleProperty(required = true)
    private String name;
    @FieldProperty(title = "描述", canEdit = true, searchable = true)
    @RuleProperty(required = true)
    private String description;
    @FieldProperty(title = "类型", canEdit = true, searchable = true, type = EnumNumber)
    @RuleProperty(required = true)
    @SelectOptionProperty(label = LabelWebFunction, intValue = WebFunction)
    @SelectOptionProperty(label = LabelApi, intValue = Api)
    @SelectOptionProperty(label = LabelMenu, intValue = Menu)
    private PermissionType type;
    private Long createTime;
    private Long updateTime;

    public static PermissionDto from(PermissionEntity entity) {
        return BeanUtils.copy(entity, PermissionDto.class);
    }
}
