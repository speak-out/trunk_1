package com.cloudream.hime.business.common.retrofit;


import com.cloudream.hime.business.bean.BaseBean;
import com.cloudream.hime.business.bean.DemoBean;
import com.cloudream.hime.business.bean.RequesetBean;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by yuer on 2016/8/24.
 */
public interface ApiService {
    @GET("data/福利/{pagenum}/{page}")
    Observable<ResponseBody> getMeimei(@Path(RequesetBean.getDemo.pagenum) int pageNum, @Path(RequesetBean.getDemo.page) int page);

    //    String requestString = "/getServiceList"+RequestConstant.getExUrl();
//    @Headers( "Content-Type: application/json" )
    @POST("appapi/getCorplist")
    Observable<Object> getDmeo(@Body DemoBean map);

    @POST("appapi/getArea")
    Observable<Object> GetAear(@Body BaseBean bean);
}
