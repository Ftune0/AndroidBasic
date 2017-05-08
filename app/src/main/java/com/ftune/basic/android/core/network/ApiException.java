package com.ftune.basic.android.core.network;

import com.ftune.basic.android.core.exception.BaseException;

/**
 * Created by ftune on 17/5/8.
 * <p>
 * Api request error response.
 */
public class ApiException extends BaseException {

    public static final int SESSION_TIMEOUT = -9000;
    public static final int APP_UPDATE_INFO = -9001;
    public static final int INVALID_PARAMS = -9002;
    public static final int RESPONSE_DATA_NULL = -9003;

    public ApiException(int errorCode, String message) {
        super(errorCode, message);
    }
}
