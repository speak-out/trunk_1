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
                request.onError(e);
            }

            @Override
            public void onNext(Object response) {
                request.onSuccee(response, code);
            }
        };

        return observable
                .doOnNext(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        CacheDataBase.getInstance(BaseApplication.getInstance()).saveCache(o,"longjian");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public Subscription subscribeData(@NonNull Observer<Object> observer) {  //传入一个用来做处理的observer 处理请求的数据的observer
        if (cache == null) {   //没有缓存
            cache = BehaviorSubject.create();  //双面间谍 BehaviorSubject
            Observable.create(new Observable.OnSubscribe<Object>() {
                @Override
                public void call(Subscriber<? super Object> subscriber) {
                    Object object = CacheDataBase.getInstance(BaseApplication.getInstance()).readCache("longjain");
                    if (object == null) {
//                        loadFromNetwork();
                    } else {
//                        setDataSource(DATA_SOURCE_DISK);
                        subscriber.onNext(object);
                    }
                }
            })
                    .subscribeOn(Schedulers.io())
                    .subscribe(cache);//这里BehaviorSubject这里充当observer
        } else {
//            setDataSource(DATA_SOURCE_MEMORY);
        }
        return cache.observeOn(AndroidSchedulers.mainThread()).subscribe(observer); //这里BehaviorSubject充当observable
    }
}

//    public void loadFromNetwork() {
//        Network.getGankApi()
//                .getBeauties(100, 1)
//                .subscribeOn(Schedulers.io())
//                .map(GankBeautyResultToItemsMapper.getInstance())
//                .doOnNext(new Action1<List<Item>>() {
//                    @Override
//                    public void call(List<Item> items) {
//                        Database.getInstance().writeItems(items);
//                    }
//                })
//                .subscribe(new Action1<List<Item>>() {
//                    @Override
//                    public void call(List<Item> items) {
//                        cache.onNext(items);
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                });

