package com.bachdv.lib.common.exception;

import com.bachdv.lib.common.api.IErrorCode;

/**
 * Assertion processing class for throwing various API exceptions
 *
 * @author BachDV
 * Date : 25/03/2022
 */
public class Asserts {

    //if(!userDetails.isEnabled()){
    //      Asserts.fail("Account is disabled");
    // }
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }

}
