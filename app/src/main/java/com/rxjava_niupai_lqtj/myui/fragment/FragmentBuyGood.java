package com.rxjava_niupai_lqtj.myui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxjava_niupai_lqtj.App;
import com.rxjava_niupai_lqtj.R;
import com.rxjava_niupai_lqtj.base.BaseFragment;
import com.rxjava_niupai_lqtj.base.GridItemDecoration;
import com.rxjava_niupai_lqtj.myui.activity.MainActivity;
import com.rxjava_niupai_lqtj.myui.adapter.BuyGoodAdapter;
import com.rxjava_niupai_lqtj.myui.view.Kanner;
import com.rxjava_niupai_lqtj.myui.view.RecyclerViewBaseOnPullToRefresh;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xuxiarong on 2016/5/4.
 */
public class FragmentBuyGood extends BaseFragment{

    @Bind(R.id.fragment_buy_txt_title)
    TextView mTxtTitle;
    @Bind(R.id.fragment_buy_img_search)
    ImageView mImgSearch;
    @Bind(R.id.fragment_buy_rv_goods)
    RecyclerViewBaseOnPullToRefresh mBuyRv;
    @Bind(R.id.fragment_buy_kanner)
    Kanner mKanner;

    private App app;
    private Context mContext;
    private RecyclerView.LayoutManager mLayoutManager;
    private BuyGoodAdapter mBuyGoodAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_buy_good;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mContext = getActivity();
        app = (App)mContext.getApplicationContext();
        initData();
//      if (mNewsListAdapter.getmNewsList().size() == 0) {
//            loadLatestNews();
//      }
    }

    public static FragmentBuyGood newInstance() {
        return new FragmentBuyGood();
    }


    private void initData() {
        mLayoutManager = new GridLayoutManager(mContext,2);
        mBuyGoodAdapter = new BuyGoodAdapter();
        mBuyRv.getRefreshableView().setLayoutManager(mLayoutManager);
        mBuyRv.getRefreshableView().setItemAnimator(new DefaultItemAnimator());
        mBuyRv.getRefreshableView().addItemDecoration(new GridItemDecoration(mContext, true));
        mBuyRv.getRefreshableView().setAdapter(mBuyGoodAdapter);
        setHeader(mBuyRv.getRefreshableView());
        loadKennerUrl();
    }



    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_buy_frist, view, false);
        ButterKnife.bind(header,(MainActivity)mContext);
        mBuyGoodAdapter.setHeaderView(header);
    }

    private void loadKennerUrl() {
        //MyRetrofitManager.builder().getGoodsDetailById();
    }
}
