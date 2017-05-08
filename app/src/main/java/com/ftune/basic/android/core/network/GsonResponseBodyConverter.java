package com.ftune.basic.android.core.network;

import com.google.gson.Gson;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by ftune on 17/5/8.
 * <p>
 * Gson response body converter.
 */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    /**
     * Get response type use in gson parsing.
     *
     * @param args types
     * @return ParameterizedType
     */
    private ParameterizedType getResponseType(final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return ApiBaseResponse.class;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String response = value.string();
            ParameterizedType responseType = getResponseType(type);
            ApiBaseResponse<T> apiBaseResponse = gson.fromJson(response, responseType);
            //If response is success, return json result; else throw exception to subscriber.
            if (apiBaseResponse.code == 0) {
                return apiBaseResponse.data;
            } else {
                throw new ApiException(apiBaseResponse.code, apiBaseResponse.msg);
            }
        } finally {
            value.close();
        }
    }
}
