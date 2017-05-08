package com.ftune.basic.android.core.network;

import com.ftune.basic.android.application.Constants;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by ftune on 17/5/8.
 * <p>
 * Retrofit api request adapter.
 */
public class RestApiAdapter {

    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            //UnserializeResponseConverterFactory use to unseriallize
            ResponseConverterFactory responseConverterFactory = ResponseConverterFactory.create();

            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            CookiesManager cookiesManager = new CookiesManager();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.retryOnConnectionFailure(true);
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.addInterceptor(logInterceptor);
            builder.cookieJar(cookiesManager);
            okHttpClient = builder.build();

//            builder.addNetworkInterceptor(httpLogInterceptor);
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(responseConverterFactory)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static void release() {
        CookieJar cookieManager = okHttpClient.cookieJar();
        if (cookieManager instanceof CookiesManager) {
            ((CookiesManager) cookieManager).clearAllCookie();
        }
        okHttpClient = null;
        retrofit = null;
    }

//    public static class HttpTokenInterceptor implements Interceptor {
//        @Override public Response intercept(Chain chain) throws IOException {
//            Request originalRequest = chain.request();
//            originalRequest.headers().newBuilder().add()
//            if (Your.sToken == null || alreadyHasAuthorizationHeader(originalRequest)) {
//                return chain.proceed(originalRequest);
//            }
//            Request authorised = originalRequest.newBuilder()
//                    .header("Authorization", Your.sToken)
//                    .build();
//            return chain.proceed(authorised);
//        }
//    }

}
