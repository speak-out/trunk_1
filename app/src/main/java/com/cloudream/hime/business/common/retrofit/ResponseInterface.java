package com.cloudream.hime.business.common.retrofit;

/**
 * Created by yuer on 2016/8/24.
 */
public interface ResponseInterface {
    //请求成功
    public void onSuccee(Object response,int code);
    //请求错误
    public void onError(Throwable e);
     //请求结束
    public void onCompleted();

}
