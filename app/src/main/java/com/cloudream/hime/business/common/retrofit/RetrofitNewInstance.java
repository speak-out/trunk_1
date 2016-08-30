package com.cloudream.hime.business.common.retrofit;

import com.cloudream.hime.business.config.Constant;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yuer on 2016/8/24.
 */
public class RetrofitNewInstance {
    private static Retrofit mRetrofit;
    private static Converter.Factory gonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static OkHttpClient mOkHttpClient = null;
    public static Retrofit getInstance() {
        if (mRetrofit != null) {
            return mRetrofit;
        }
        mOkHttpClient =
                new OkHttpClient().newBuilder()
                        .addInterceptor(new ResponseInterceptor())
                        .addInterceptor(new RequestInterceptor())
                        .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.serverIp)
                .client(mOkHttpClient)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gonConverterFactory)
                .build();
        return mRetrofit;
    }


}
