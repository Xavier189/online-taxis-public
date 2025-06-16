package com.cola.internal.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author Xavier
 * @date 2025/6/16
 * @description 分页查询请求
 */
public abstract class PageQuery extends Query {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String ASC = "ASC";

    public static final String DESC = "DESC";

    private static final int DEFAULT_PAGE_SIZE = 10;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private int pageIndex = 1;

    @Getter
    private String orderBy;

    @Getter
    private String orderDirection = DESC;

    @Getter
    @Setter
    private String groupBy;

    @Getter
    @Setter
    private boolean needTotalCount = false;


    public int getPageSize() {
        if (pageSize < 1) pageSize = DEFAULT_PAGE_SIZE;
        return pageSize;
    }

    public PageQuery setPageSize(int pageSize) {
        if (pageSize < 1) pageSize = DEFAULT_PAGE_SIZE;
        this.pageSize = pageSize;
        return this;
    }

    public int getPageIndex() {
        return Math.max(pageIndex, 1);
    }

    public PageQuery setPageIndex(int pageIndex) {
        this.pageIndex = Math.max(pageIndex, 1);
        return this;
    }

    public int getOffset() {
        return (getPageIndex() - 1) * getPageSize();
    }

    public PageQuery setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public PageQuery setOrderDirection(String orderDirection) {
        if (ASC.equalsIgnoreCase(orderDirection) || DESC.equalsIgnoreCase(orderDirection))
            this.orderDirection = orderDirection;
        return this;
    }

}
