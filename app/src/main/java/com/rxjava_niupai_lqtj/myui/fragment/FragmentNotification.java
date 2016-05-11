package com.rxjava_niupai_lqtj.myui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.rxjava_niupai_lqtj.App;
import com.rxjava_niupai_lqtj.R;
import com.rxjava_niupai_lqtj.base.BaseFragment;
import com.rxjava_niupai_lqtj.bean.Notifications;
import com.rxjava_niupai_lqtj.inteface.AutoLoadOnScrollListener;
import com.rxjava_niupai_lqtj.myui.adapter.NotificationAdapter;
import com.rxjava_niupai_lqtj.myui.view.RecyclerViewBaseOnPullToRefresh;
import com.rxjava_niupai_lqtj.network.manager.MyRetrofitManager;
import com.rxjava_niupai_lqtj.utils.L;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;



/**
 * Created by xuxiarong on 2016/5/4.
 */
public class FragmentNotification extends BaseFragment{
    @Bind(R.id.appheader_txt_title)
    TextView mBuyTxtTitle;
    @Bind(R.id.fragment_noti_rv)
    RecyclerViewBaseOnPullToRefresh mBuyRv;


    private App app;
    private Context mContext;
    private NotificationAdapter mNotificationAdapter;
    private AutoLoadOnScrollListener mAutoLoadListener;
//    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private List<String> list = new ArrayList<String>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notification;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mContext = getActivity();
        app = (App)mContext.getApplicationContext();
        initData();
        if(mNotificationAdapter.getmNotificationsList().size()==0){
            loadNotifications();
        }
    }

    public static FragmentNotification newInstance() {
        return new FragmentNotification();
    }

    private void initData() {

        mBuyTxtTitle.setText(mContext.getResources().getString(R.string.fragment_noti_title));
//        mLinearLayoutManager = new LinearLayoutManager(getActivity());
//        mLinearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mGridLayoutManager = new GridLayoutManager(mContext,2);
  //      mGridLayoutManager.setOrientation(GridLayout.VERTICAL);
        mBuyRv.getRefreshableView().setLayoutManager(mGridLayoutManager);
   //     mBuyRv.getRefreshableView().addItemDecoration(new GridItemDecoration(mContext, true));
        mNotificationAdapter = new NotificationAdapter(mContext,new ArrayList<Notifications>());
        mBuyRv.getRefreshableView().setAdapter(mNotificationAdapter);
        mAutoLoadListener = new AutoLoadOnScrollListener(mGridLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                mAutoLoadListener.setLoading(false);
                loadNotifications();
            //    mNotificationAdapter.changeData(list);
            }
        };
        mBuyRv.setScrollingWhileRefreshingEnabled(true);
        mBuyRv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mBuyRv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>(){

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                loadNotifications();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {

            }
        });

//        mBuyRv.getRefreshableView().addOnScrollListener(mAutoLoadListener);
        mNotificationAdapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                Toast.makeText(mContext, position + "," + data, Toast.LENGTH_SHORT).show();
            }
        });

    }
    OkHttpClient client = new OkHttpClient();

    String getDate(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    private void loadNotifications() {

        MyRetrofitManager.builder().getNotifications()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .doOnSubscribe(new Action0() {
                   @Override
                   public void call() {
                      // showProgress();
                   }
               }).map(new Func1<List<Notifications>, List<Notifications>>() {
                   @Override
                   public List<Notifications> call(List<Notifications> result) {

                       System.out.print(result);
//                       //return changeReadState(newsList);
                       return result;
                   }
               })
               .subscribe(new Action1<List<Notifications>>() {
                   @Override
                   public void call(List<Notifications> result) {
                       //mAutoLoadListener.setLoading(false);
                       mBuyRv.onRefreshComplete();

                       mNotificationAdapter.changeData(result);
                       for (int i = 0 ;i<result.size(); i++) {

                       }
                       //curDate = newsList.getDate();
                   }
               }, new Action1<Throwable>() {
                   @Override
                   public void call(Throwable throwable) {
                       //mAutoLoadListener.setLoading(false);
                       L.e(throwable, "Load before news error");
                       throwable.printStackTrace();
                       //mLoadBeforeSnackbar.show();
                   }
               });
    }

}
