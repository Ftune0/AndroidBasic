package com.ftune.basic.android.core.network;

/**
 * Created by ftune on 17/5/8.
 * <p>
 * Api request base response.
 */
final class ApiBaseResponse<T> {
    public int code;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "ApiBaseResponse={" +
                ", code='" + code + '\'' +
                ", msg=" + msg +
                ", data=" + data.toString() +
                '}';
    }
}
