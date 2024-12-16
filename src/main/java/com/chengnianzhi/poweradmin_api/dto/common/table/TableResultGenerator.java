package com.chengnianzhi.poweradmin_api.dto.common.table;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chengnianzhi.poweradmin_api.constant.GeneralFieldName;
import com.chengnianzhi.poweradmin_api.constant.ModuleName;
import com.chengnianzhi.poweradmin_api.dto.common.table.property.*;
import com.chengnianzhi.poweradmin_api.utils.BeanUtils;
import com.chengnianzhi.poweradmin_api.utils.StrUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class TableResultGenerator<DTO, Entity> {
    public void list(Supplier<IPage<Entity>> querier, IListResult<DTO> listResult, Class<DTO> dtoClass) {
        IPage<Entity> paged = querier.get();
        listResult.setTotal(paged.getTotal());
        List<Entity> records = paged.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return;
        }
        records.forEach(item -> listResult.getData().add(BeanUtils.copy(item, dtoClass)));
        listResult.setAction(buildAction(dtoClass));
        listResult.setFields(buildFields(dtoClass));
    }

    public Action buildAction(Class<DTO> clazz) {
        var action = new Action();
        ActionProperty actionProperty = clazz.getDeclaredAnnotation(ActionProperty.class);
        if (actionProperty != null) {
            if (actionProperty.module() != ModuleName.None) {
                action.setCommonPermissions(actionProperty.module());
            }
            if (StrUtils.hasLength(actionProperty.addPermission())) {
                action.setAddPermission(actionProperty.addPermission());
            }
            if (StrUtils.hasLength(actionProperty.editPermission())) {
                action.setEditPermission(actionProperty.editPermission());
            }
            if (StrUtils.hasLength(actionProperty.deletePermission())) {
                action.setDeletePermission(actionProperty.deletePermission());
            }
        }
        action.grant();
        return action;
    }

    public List<Field> buildFields(Class<DTO> clazz) {
        var tableFields = new ArrayList<Field>();
        ReflectionUtils.doWithFields(clazz, field -> {
            var name = field.getName();
            FieldProperty fieldProperty = field.getAnnotation(FieldProperty.class);
            RuleProperty[] ruleProperties = field.getAnnotationsByType(RuleProperty.class);
            FieldAssociationProperty associationProperty = field.getAnnotation(FieldAssociationProperty.class);
            SelectOptionProperty[] optionProperties = field.getAnnotationsByType(SelectOptionProperty.class);
            if (fieldProperty == null) {
                switch (name) {
                    case GeneralFieldName.CreateTime -> tableFields.add(new Field("创建时间", name)
                            .withType(TableFieldType.Time)
                            .withCanAdd(false));
                    case GeneralFieldName.UpdateTime -> tableFields.add(new Field("更新时间", name)
                            .withType(TableFieldType.Time)
                            .withCanAdd(false));
                    case GeneralFieldName.ID -> tableFields.add(new Field("ID", name)
                            .withCanAdd(false));
                }
            } else {
                if (StrUtils.hasLength(fieldProperty.dataIndex())) {
                    name = fieldProperty.dataIndex();
                }
                var tableField = Field.builder()
                        .title(fieldProperty.title())
                        .dataIndex(name)
                        .type(fieldProperty.type())
                        .canShow(fieldProperty.canShow())
                        .canEdit(fieldProperty.canEdit())
                        .canAdd(fieldProperty.canAdd())
                        .searchable(fieldProperty.searchable())
                        .rules(new ArrayList<>())
                        .options(new ArrayList<>())
                        .build();
                Arrays.stream(ruleProperties).forEach(ruleProperty -> {
                    var message = ruleProperty.message();
                    if (StrUtils.hasNoLength(message) && ruleProperty.required()) {
                        message = "%s不能为空".formatted(tableField.getTitle());
                    }
                    tableField.getRules().add(FieldRule.builder()
                            .required(ruleProperty.required())
                            .message(message)
                            .pattern(ruleProperty.pattern())
                            .build());
                });
                if (associationProperty != null) {
                    tableField.setAssociation(Association.builder()
                            .path(associationProperty.path())
                            .queryFields(Arrays.stream(associationProperty.queryFields()).toList())
                            .valueField(associationProperty.valueField())
                            .labelFields(Arrays.stream(associationProperty.labelFields()).toList())
                            .build());
                }
                Arrays.stream(optionProperties).forEach(option -> {
                    var optionBuilder = SelectOption.builder();
                    optionBuilder.label(option.label());
                    if (StrUtils.hasLength(option.strValue())) {
                        optionBuilder.value(option.strValue());
                    } else {
                        optionBuilder.value(option.intValue());
                    }
                    tableField.getOptions().add(optionBuilder.build());
                });
                tableFields.add(tableField);
            }
        });
        return tableFields;
    }

}




