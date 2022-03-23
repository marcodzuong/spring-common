package com.bachdv.lib.common.api;

import lombok.Getter;

/**
 * @author BachDV
 * Date : 22/03/2022
 */
@Getter
public enum ResultCode implements IErrorCode{
    SUCCESS(200, "SUCCESS"),
    FAILED(500, "FAILED"),
    VALIDATE_FAILED(404, "VALIDATE_FAILED"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN");

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

}
