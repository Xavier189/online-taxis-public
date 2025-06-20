package com.cola.internal.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Xavier
 * @date 2025/6/16
 * @description 分页响应
 */
public class PageResponse<T> extends Response {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int totalCount = 0;

    private int pageSize = 1;

    private int pageIndex = 1;

    @Setter
    private Collection<T> data;

    public int getPageSize() {
        return Math.max(pageSize, 1);
    }

    public void setPageSize(int pageSize) {
        this.pageSize = Math.max(pageSize, 1);
    }

    public int getPageIndex() {
        return Math.max(pageIndex, 1);
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = Math.max(pageIndex, 1);
    }

    public List<T> getData() {
        if (null == data) {
            return Collections.emptyList();
        }
        if (data instanceof List) {
            return (List<T>) data;
        }
        return new ArrayList<>(data);
    }

    public static PageResponse<Void> buildSuccess() {
        PageResponse<Void> response = new PageResponse<>();
        response.setSuccess(true);
        return response;
    }

    public static PageResponse<Void> buildFailure(int errorCode, String errorMessage) {
        PageResponse<Void> response = new PageResponse<>();
        response.setSuccess(false);
        response.setCode(errorCode);
        response.setMessage(errorMessage);
        return response;
    }

    public static <T> PageResponse<T> of(int pageSize, int pageIndex) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setData(Collections.emptyList());
        response.setTotalCount(0);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }

    public static <T> PageResponse<T> of(Collection<T> data, int totalCount, int pageSize, int pageIndex) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTotalCount(totalCount);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }
}
