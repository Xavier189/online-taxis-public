package com.cola.internal.common;

import lombok.Setter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Xavier
 * @date 2025/6/16
 * @description 多信息响应
 */
@Setter
public class MultiResponse<T> extends Response {

    @Serial
    private static final long serialVersionUID = 1L;

    private Collection<T> data;

    public List<T> getData() {
        if (null == data) {
            return Collections.emptyList();
        }
        if (data instanceof List) {
            return (List<T>) data;
        }
        return new ArrayList<>(data);
    }

    public static MultiResponse<Void> buildSuccess() {
        MultiResponse<Void> response = new MultiResponse<>();
        response.setSuccess(true);
        return response;
    }

    public static MultiResponse<Void> buildFailure(int errorCode, String errorMessage) {
        MultiResponse<Void> response = new MultiResponse<>();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return response;
    }

    public static <T> MultiResponse<T> of(Collection<T> data) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

}
