package com.cloudream.hime.business.module.main.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cloudream.hime.business.R;
import com.cloudream.hime.business.base.BaseActivity;
import com.cloudream.hime.business.bean.BaseBean;
import com.cloudream.hime.business.bean.DemoBean;
import com.cloudream.hime.business.bean.Params;
import com.cloudream.hime.business.common.retrofit.RequestEntity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity  {
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button1)
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        button.setOnClickListener(this);
//        button1.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.button:
////                Map<String, Object> parmsMap = new HashMap<>();
////                parmsMap.put("cityId", 440300);
////                parmsMap.put("type", 3);
//                DemoBean demoBean = new DemoBean();
//                ArrayList<Params> arrayList = new ArrayList<>();
//                Params params = new Params();
//                params.setCityId("440300");
//                params.setType("3");
//                arrayList.add(params);
//                demoBean.setJsonrpc("2.0");
//                demoBean.setParams(arrayList);
////                parmsMap.put("districtId", 0);
//                subscription = RequestEntity.request(RequestEntity.getApiService().getDmeo(demoBean), this,2);//RequestEntity.setRequestParam(demoBean)
//                break;
//            case R.id.button1:
//                BaseBean demoBean1 = new BaseBean();
//                ArrayList<Params> arrayList1 = new ArrayList<>();
//                Params params1 = new Params();
//                params1.setCityId("440300");
//                params1.setType("3");
//                demoBean1.setJsonrpc("2.0");
//                arrayList1.add(params1);
//                demoBean1.setParams(arrayList1);
//                subscription = RequestEntity.request(RequestEntity.getApiService().GetAear(demoBean1),this,1);
//                break;
//        }
//    }
//    @Override
//    public void onSuccee(Object response,int code) {
//       super.onSuccee(response,code);
//        if(code == 2){
//            Log.e("mmmmmmmmm", "自己处理==Demo"+response.toString());
//        }
//        if(code == 1){
//            Log.e("mmmmmmmmm", "自己处理==Aear"+response.toString());
//        }
//
//    }
//
//    @Override
//    public void onError() {
//        super.onError();
//        Log.e("mmmmmmmmm", "请求失败，自己处理");
//    }

}
