package com.rxjava_niupai_lqtj.network.service;


import com.rxjava_niupai_lqtj.bean.Comment;
import com.rxjava_niupai_lqtj.bean.GoodsDetail;
import com.rxjava_niupai_lqtj.bean.IndexImgList;
import com.rxjava_niupai_lqtj.bean.Notifications;
import com.rxjava_niupai_lqtj.bean.ShowBill;
import com.rxjava_niupai_lqtj.network.manager.MyRetrofitManager;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xuxiarong on 16/5/6.
 *
 */
public interface NiuPaiServer {

    @Headers(MyRetrofitManager.CACHE_CONTROL_AGE + MyRetrofitManager.CACHE_STALE_SHORT)
    @GET("share/indexSharecommentsList.action")
    Observable<List<Comment>> getComments();

    @Headers(MyRetrofitManager.CACHE_CONTROL_AGE + MyRetrofitManager.CACHE_STALE_SHORT)
    @GET("lottery/lotteryproductutilList.action")
    Observable<List<Notifications>> getNotifications();

    @Headers(MyRetrofitManager.CACHE_CONTROL_AGE + MyRetrofitManager.CACHE_STALE_SHORT)
    @GET("products/goodsDescAjax.action")
    Observable<GoodsDetail> getGoodsDetailById(@Query("id") String id);

    @Headers(MyRetrofitManager.CACHE_CONTROL_AGE + MyRetrofitManager.CACHE_STALE_SHORT)
    @GET("share/ajaxPage.action")
    Observable<List<ShowBill>> getShareBills(@Query("id") String id,@Query("pageNo") String pageNo,@Query("pageSize") String pageSize);

    @Headers(MyRetrofitManager.CACHE_CONTROL_AGE + MyRetrofitManager.CACHE_STALE_SHORT)
    @GET("list/indexImgList.action")
    Observable<List<IndexImgList>> IndexImgList();

}
