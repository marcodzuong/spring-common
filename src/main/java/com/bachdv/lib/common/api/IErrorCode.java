package com.bachdv.lib.common.api;

/**
 * @author BachDV
 * Date : 22/03/2022
 */
public interface IErrorCode {
    /**
     * return code
     */
    long getCode();

    /**
     * return messages
     */
    String getMessage();
}
