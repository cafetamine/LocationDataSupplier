package com.actilive.lds.rest;

import com.actilive.lds.api.ApiErrorResponse;
import com.actilive.lds.api.ApiErrorCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class ApiErrorAdapter {

    private static final Map<ApiErrorCode, HttpStatus> ApiErrorToHttpStatusMap = new HashMap();
    static {
        ApiErrorToHttpStatusMap.put(ApiErrorCode.MarkBadRequest, HttpStatus.BAD_REQUEST);
        ApiErrorToHttpStatusMap.put(ApiErrorCode.NotFound, HttpStatus.NOT_FOUND);
        ApiErrorToHttpStatusMap.put(ApiErrorCode.Duplicate, HttpStatus.CONFLICT);
        ApiErrorToHttpStatusMap.put(ApiErrorCode.InternalServerError, HttpStatus.INTERNAL_SERVER_ERROR);

        Assert.isTrue(ApiErrorToHttpStatusMap.values().size() == ApiErrorCode.values().length, "Not all ApiErrorCodes were registered");
    }

    public static @NotNull HttpStatus getHttpStatus(@NotNull final ApiErrorCode apiCode) {
        return ApiErrorToHttpStatusMap.getOrDefault(apiCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static @NotNull ResponseEntity<Object> mapResponse(final @NotNull ApiErrorCode errorCode,
                                                              final @NotNull String errorMessage) {
        return new ResponseEntity<>(new ApiErrorResponse(errorCode, errorMessage), getHttpStatus(errorCode));
    }

}