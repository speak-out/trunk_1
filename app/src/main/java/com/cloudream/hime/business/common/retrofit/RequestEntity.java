package com.cloudream.hime.business.common.retrofit;

import android.support.annotation.NonNull;
import android.util.Log;

import com.cloudream.hime.business.base.BaseApplication;
import com.cloudream.hime.business.common.database.CacheDataBase;
import com.cloudream.hime.business.common.retrofit.ApiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Cache;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by yuer on 2016/8/24.
 */
public class RequestEntity {

    public static final String TAG = "YZMLog---";
    private BehaviorSubject cache;

    public static ApiService getApiService() {
        return RetrofitNewInstance.getInstance().create(ApiService.class);
    }

    public static Subscription request(Observable<Object> observable, ResponseInterface requestFace, final int code) {

        final ResponseInterface request = requestFace;
        Observer observer = new Observer<Object>() {

            @Override
            public void onCompleted() {
                Log.i(TAG, "====onCompleted====");
                request.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.toString());
                Object object = CacheDataBase.readCache("longjian");
                if (object != null && !object.equals("")) {
                    request.onSuccee(object, code);
                } else {
                    request.onError(e);
                }
            }

            @Override
            public void onNext(Object response) {
                CacheDataBase.saveCache(response, "longjian");
                request.onSuccee(response, code);
            }
        };
        Subscription subscruiption = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return subscruiption;
    }
}



