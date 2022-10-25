package com.marco.common.exception;


import com.marco.common.api.IErrorCode;

/**
 * Assertion processing class for throwing various API exceptions
 *
 * @author MarcoDuong
 */
public class Asserts {


    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }

}
