package com.ftune.basic.android.core.exception;

/**
 * Created by ftune on 17/5/8.
 * <p>
 * App base exception.
 */
public class BaseException extends RuntimeException {

    /**
     * Define the error code below.
     * <p>
     * public static final int INVALID_PARAMS = -2;
     */

    public int errorCode;

    public BaseException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
