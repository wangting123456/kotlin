package com.example.rxjava;

import io.reactivex.Observable;
import retrofit2.http.*;


/**
 * @author zlq
 * @version V
 * @descript
 * @cretime 2019/9/26 17:10
 */
public interface MainServices {


    @POST("/terminal/system.do?action=getFileDetailList")
    @FormUrlEncoded
    Observable<Response> queryFileDetail(@Field("json") String json);

}
