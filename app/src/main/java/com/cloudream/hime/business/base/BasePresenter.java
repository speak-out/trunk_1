package com.cloudream.hime.business.base;

import android.util.Log;

import com.cloudream.hime.business.common.retrofit.ResponseInterface;

import rx.Subscription;

/**
 * Created by admin on 2016/8/29.
 */
public class BasePresenter implements ResponseInterface{

    protected Subscription subscription = null;
    @Override
    public void onSuccee(Object response, int code) {
        Log.e("llllllll", "请求成功");
    }

    @Override
    public void onError(Throwable e) {
        Log.e("llllllll", "请求失败");
    }

    @Override
    public void onCompleted() {
        Log.e("llllllll", "请求完成");
    }

    public void unsubscribe() {  //解除关联
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
