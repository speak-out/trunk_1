package com.cloudream.hime.business.common.retrofit;

import android.util.Log;

import com.cloudream.hime.business.common.database.CacheDataBase;
import com.cloudream.hime.business.config.RequestConstant;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yuer on 2016/8/25.
 */
public class RequestInterceptor implements Interceptor {

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request oldRequest = chain.request();
            long time = System.currentTimeMillis();
            String request_id = RequestConstant.getRequestId(time);
            // 添加新的参数
            HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host())
                    .addQueryParameter("request_id",request_id)
                    .addQueryParameter("time",String.valueOf(time/1000))
                    .addQueryParameter("sign",RequestConstant.getSign(time,request_id))
                    ;
            // 新的请求
            Request newRequest = oldRequest.newBuilder()
                    .method(oldRequest.method(), oldRequest.body())
                    .url(authorizedUrlBuilder.build())
                    .header("accept", "application/json")
                    .header( "Content-type","application/json")
                    .build();
            return chain.proceed(newRequest);
        }
}
