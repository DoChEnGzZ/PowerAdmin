package com.chengnianzhi.poweradmin_api.dto.common.table;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class AbstractPagination implements IPagination {
    @NotNull
    protected int page;
    @NotNull
    @Max(value = 50, message = "一次最多只能获取50条记录")
    protected int size;

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return size;
    }
}
