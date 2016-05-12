package com.rxjava_niupai_lqtj.myui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxjava_niupai_lqtj.App;
import com.rxjava_niupai_lqtj.R;
import com.rxjava_niupai_lqtj.base.BaseFragment;
import com.rxjava_niupai_lqtj.bean.GoodsList;
import com.rxjava_niupai_lqtj.bean.IndexImgList;
import com.rxjava_niupai_lqtj.myui.adapter.GoodListAdapter;
import com.rxjava_niupai_lqtj.myui.view.Kanner;
import com.rxjava_niupai_lqtj.myui.view.RecyclerViewBaseOnPullToRefresh;
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
 * Created by xuxiarong on 2016/5/4.
 */
public class FragmentBuyGood extends BaseFragment{

    @Bind(R.id.fragment_buy_txt_title)
    TextView mTxtTitle;
    @Bind(R.id.fragment_buy_img_search)
    ImageView mImgSearch;
    @Bind(R.id.fragment_buy_rv_goods)
    RecyclerViewBaseOnPullToRefresh mBuyRv;

    Kanner mKanner;

    private App app;
    private Context mContext;
    private RecyclerView.LayoutManager mLayoutManager;
    private GoodListAdapter mGoodListAdapter;
    private String[] imgUrls;       //轮播图需要的url数组
    private String id = "hot20";    //商品默认查询id
    private String typeId;          //商品默认分类id
    private String pageNo = "0";    //商品默认页数
    private String pageSize = "10"; //商品默认每页个数

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
        mGoodListAdapter = new GoodListAdapter();
        mBuyRv.getRefreshableView().setLayoutManager(mLayoutManager);
//        mBuyRv.getRefreshableView().setItemAnimator(new DefaultItemAnimator());
//        mBuyRv.getRefreshableView().addItemDecoration(new GridItemDecoration(mContext, true));
        mBuyRv.getRefreshableView().setAdapter(mGoodListAdapter);
        setHeader(mBuyRv.getRefreshableView());
        loadHeaderViewData();
        loadGoodListData();
    }


    /**
     * 给RecycleView设置头部的View
     * @param view
     */
    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_buy_frist, view, false);
        mKanner = (Kanner) header.findViewById(R.id.fragment_buy_kanner);
        mKanner.setVisibility(View.VISIBLE);
        mGoodListAdapter.setHeaderView(header);
    }

    /**
     * 加载RecycleView头部的View需要的数据
     */
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
                    public void call(List<IndexImgList> result) {
                        if(result!=null && result.size()>0) {
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

    private void loadGoodListData() {
        MyRetrofitManager.builder().getGoodList(this.id,this.pageNo,this.pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        // showProgress();
                    }
                }).map(new Func1<ArrayList<GoodsList>, ArrayList<GoodsList>>() {
            @Override
            public ArrayList<GoodsList> call(ArrayList<GoodsList> result) {

                return result;
            }
        })
                .subscribe(new Action1<ArrayList<GoodsList>>() {
                    @Override
                    public void call(ArrayList<GoodsList> result) {
                        //服务器成功返回数据
                        if(result!=null && result.size()>0) {
                            mGoodListAdapter.addDatas(result);
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
