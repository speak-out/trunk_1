package com.cloudream.hime.business.module.demo.presenter;

import android.util.Log;

import com.cloudream.hime.business.base.BasePresenter;
import com.cloudream.hime.business.bean.BaseBean;
import com.cloudream.hime.business.bean.DemoBean;
import com.cloudream.hime.business.bean.Params;
import com.cloudream.hime.business.common.retrofit.ApiService;
import com.cloudream.hime.business.common.retrofit.RequestEntity;
import com.cloudream.hime.business.module.demo.view.IdemoView;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by admin on 2016/8/29.
 */
public class DemoPresenter extends BasePresenter{

    private IdemoView mDemoView;

    public DemoPresenter(IdemoView demoView){
        mDemoView = demoView;
    }

    public void getDemo(){
        DemoBean demoBean = new DemoBean();
        ArrayList<Params> arrayList = new ArrayList<>();
        Params params = new Params();
        params.setCityId("440300");
        params.setType("3");
        arrayList.add(params);
        demoBean.setJsonrpc("2.0");
        demoBean.setParams(arrayList);
        subscription = RequestEntity.request(RequestEntity.getApiService().getDmeo(demoBean), this,2);//RequestEntity.setRequestParam(demoBean)
    }

    public void getArea(){
        BaseBean demoBean1 = new BaseBean();
        ArrayList<Params> arrayList1 = new ArrayList<>();
        Params params1 = new Params();
        params1.setCityId("440300");
        params1.setType("3");
        demoBean1.setJsonrpc("2.0");
        arrayList1.add(params1);
        demoBean1.setParams(arrayList1);
        ApiService apiService = RequestEntity.getApiService();
        Observable observable = apiService.GetAear(demoBean1);
        subscription =  RequestEntity.request(observable,this,1);
//        subscription = RequestEntity.request(RequestEntity.getApiService().GetAear(demoBean1),this,1);//将方法名作为唯一的标识作为请求返回
    }

    @Override
    public void onSuccee(Object response, int code) {
        if(response != null) {
            mDemoView.setTextView("code:"+code+"----"+response.toString());
        }
    }

    @Override
    public void onError(Throwable e) {
        if(e != null) {
            Log.e("yzmhand", e.toString());
        }
    }
}
