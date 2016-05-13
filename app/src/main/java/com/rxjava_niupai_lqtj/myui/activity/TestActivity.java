package com.rxjava_niupai_lqtj.myui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.rxjava_niupai_lqtj.R;
import com.rxjava_niupai_lqtj.base.BaseActivity;
import com.rxjava_niupai_lqtj.bean.IndexImgList;
import com.rxjava_niupai_lqtj.inteface.AutoLoadOnScrollListener;
import com.rxjava_niupai_lqtj.myui.adapter.RvAdapater;
import com.rxjava_niupai_lqtj.myui.view.Kanner;
import com.rxjava_niupai_lqtj.network.manager.MyRetrofitManager;
import com.rxjava_niupai_lqtj.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by user on 2016/5/9.
 */
public class TestActivity extends BaseActivity{


//    @Bind(R.id.test_rv)
//    RecyclerViewBaseOnPullToRefresh mRv;
      @Bind(R.id.fragment_buy_kanner)
      Kanner mKanner;

        private String[] imgUrls;       //轮播图需要的url数组
        private String id = "hot20";    //商品默认查询id
        private String typeId;          //商品默认分类id
        private String pageNo = "0";    //商品默认页数
        private String pageSize = "10"; //商品默认每页个数
    private List<IndexImgList> mIndexImgLists = new ArrayList<IndexImgList>();
    private Context mContext;
    private RvAdapater mRvAdapater;
    private LinearLayoutManager mLayoutManager;
    ArrayList<String> mList = new ArrayList<String>();
    private AutoLoadOnScrollListener mAutoLoadListener;
    @Override
    protected int getLayoutId() {
        return R.layout.item_fragment_buy_frist;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mContext = this;
        initData(savedInstanceState);
        loadHeaderViewData();



//        mKanner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String title = mIndexImgLists.get(mKanner.currentItem).getTitle();
//                String url = mIndexImgLists.get(mKanner.currentItem).getProUrl();
//                WebViewActivity.start(mContext,mIndexImgLists.get(mKanner.currentItem).getTitle(),mIndexImgLists.get(mKanner.currentItem).getProUrl());
//            }
//        });
    }

    private void initData(Bundle savedInstanceState) {

//        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        mRv.getRefreshableView().setLayoutManager(mLayoutManager);
//        mRv.getRefreshableView().setItemAnimator(new DefaultItemAnimator());
//        mRvAdapater = new RvAdapater();
//        mRvAdapater.addDatas(changData(0));
//        //setHeader(mRvDetail.getRefreshableView());
//        mRv.getRefreshableView().setAdapter(mRvAdapater);
//        mAutoLoadListener = new AutoLoadOnScrollListener(mLayoutManager) {
//            @Override
//            public void onLoadMore(int currentPage) {
//                changData(currentPage);
//                mRvAdapater.changeData(mList);
//
//            }
//        };
//        mRv.getRefreshableView().addOnScrollListener(mAutoLoadListener);
        //showNewsDetailFragment(news);
    }
    private ArrayList<String> changData(int i){
        for(int j=i*10;j<=i*10+19;j++){
            mList.add("这是数据"+j);
        }
        return mList;
    }

    private void loadHeaderViewData() {
        MyRetrofitManager.builder().getIndexImgList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        // showProgress();
                    }
                }).map(new Func1<List<IndexImgList>, List<IndexImgList>>() {
            @Override
            public List<IndexImgList> call(List<IndexImgList> result) {

                return result;
            }
        })
                .subscribe(new Action1<List<IndexImgList>>() {
                    @Override
                    public void call(final List<IndexImgList> result) {
                        if(result!=null && result.size()>0) {
                            mIndexImgLists = result;
                            imgUrls = new String[result.size()];
                            for (int i = 0; i < result.size(); i++) {
                                imgUrls[i] = MyRetrofitManager.BASE_IMAGE_URL + result.get(i).getProImg();
                            }
                            mKanner.setImagesUrl(imgUrls);

                        }else{  //服务器返回数据异常情况待处理

                        }
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
