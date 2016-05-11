package com.rxjava_niupai_lqtj.myui.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.rxjava_niupai_lqtj.R;
import com.rxjava_niupai_lqtj.base.BaseActivity;
import com.rxjava_niupai_lqtj.inteface.AutoLoadOnScrollListener;
import com.rxjava_niupai_lqtj.myui.adapter.RvAdapater;
import com.rxjava_niupai_lqtj.myui.view.RecyclerViewBaseOnPullToRefresh;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by user on 2016/5/9.
 */
public class TestActivity extends BaseActivity{


    @Bind(R.id.test_rv)
    RecyclerViewBaseOnPullToRefresh mRv;

    private RvAdapater mRvAdapater;
    private LinearLayoutManager mLayoutManager;
    ArrayList<String> mList = new ArrayList<String>();
    private AutoLoadOnScrollListener mAutoLoadListener;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

        initData(savedInstanceState);

    }

    private void initData(Bundle savedInstanceState) {

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRv.getRefreshableView().setLayoutManager(mLayoutManager);
        mRv.getRefreshableView().setItemAnimator(new DefaultItemAnimator());
        mRvAdapater = new RvAdapater();
        mRvAdapater.addDatas(changData(0));
        //setHeader(mRvDetail.getRefreshableView());
        mRv.getRefreshableView().setAdapter(mRvAdapater);
        mAutoLoadListener = new AutoLoadOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                changData(currentPage);
                mRvAdapater.changeData(mList);

            }
        };
        mRv.getRefreshableView().addOnScrollListener(mAutoLoadListener);
        //showNewsDetailFragment(news);
    }
    private ArrayList<String> changData(int i){
        for(int j=i*10;j<=i*10+19;j++){
            mList.add("这是数据"+j);
        }
        return mList;
    }
}
