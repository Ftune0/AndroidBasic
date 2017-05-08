package com.ftune.basic.android.core.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by Fortune on 16/7/23.
 */
public final class UnserializeResponseConverterFactory extends Converter.Factory {

    public static UnserializeResponseConverterFactory create() {
        Gson gson = new GsonBuilder().setExclusionStrategies(
                new ModelExclusionStrategy(null, UnserializeApiBaseResponse.class)).create();
        return create(gson);
    }

    public static UnserializeResponseConverterFactory create(Gson gson) {
        return new UnserializeResponseConverterFactory(gson);
    }

    private final Gson gson;

    private UnserializeResponseConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new GsonResponseBodyConverter<>(gson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }
}
