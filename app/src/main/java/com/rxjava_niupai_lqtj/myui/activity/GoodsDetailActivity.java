package com.rxjava_niupai_lqtj.myui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rxjava_niupai_lqtj.App;
import com.rxjava_niupai_lqtj.R;
import com.rxjava_niupai_lqtj.base.BaseActivity;
import com.rxjava_niupai_lqtj.base.BaseRecyclerAdapter;
import com.rxjava_niupai_lqtj.bean.GoodsDetail;
import com.rxjava_niupai_lqtj.myui.adapter.GoodsDetailAdapter;
import com.rxjava_niupai_lqtj.myui.view.RecyclerViewBaseOnPullToRefresh;
import com.rxjava_niupai_lqtj.network.manager.MyRetrofitManager;
import com.rxjava_niupai_lqtj.myui.view.Kanner;
import com.rxjava_niupai_lqtj.utils.L;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by user on 2016/5/9.
 */
public class GoodsDetailActivity extends BaseActivity{


    @Bind(R.id.activity_goosdetail_img_back)
    ImageView mImgBack;
    @Bind(R.id.activity_goosdetail_img_share)
    ImageView mImgShare;
    @Bind(R.id.activity_goosdetail_img_cart)
    ImageView mImgCart;
    @Bind(R.id.activity_goosdetail_img_home)
    ImageView mImgHome;
    @Bind(R.id.activity_goosdetail_rv_detail)
    RecyclerViewBaseOnPullToRefresh mRvDetail;
    @Bind(R.id.activity_goosdetail_ll_unsell)
    LinearLayout mLlUnsell;
    @Bind(R.id.activity_goosdetail_ll_selling)
    LinearLayout mLlSelling;
    @Bind(R.id.activity_goosdetail_buy_now)
    TextView mTxtBuyNow;
    @Bind(R.id.activity_goosdetail_add_bill)
    TextView mTxtAddBill;
    @Bind(R.id.activity_goosdetail_go_now)
    TextView mTxtGoNow;

    public final  static String LOTTERY_ID = "lotteryProductId";
    public final  static String INTENT_FROM = "intentFrom";
    private String lotteryProductId;
    private String intentFrom;
    private Context mContext;
    private App app;

    private RecyclerView.LayoutManager mLayoutManager;
    private GoodsDetailAdapter mGoodsDetailAdapter;
    private View mHeadView;
    private Kanner mKanner;
    public static void start(Context context, String lotteryProductId,String intentFrom) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra(LOTTERY_ID, lotteryProductId);
        intent.putExtra(INTENT_FROM,intentFrom);
        context.startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LOTTERY_ID,this.lotteryProductId);
        outState.putString(INTENT_FROM,this.intentFrom);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mContext = this;
        app = (App)mContext.getApplicationContext();
        app.addClearActivity(this);
        initData(savedInstanceState);
        loadGoodsDetailById();
    }

    private void initData(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            if(savedInstanceState.containsKey(LOTTERY_ID) && savedInstanceState.containsKey(INTENT_FROM)){
                this.lotteryProductId = savedInstanceState.getString(LOTTERY_ID);
                this.intentFrom = savedInstanceState.getString(INTENT_FROM);
            }
        }else{
            this.lotteryProductId = getIntent().getExtras().getString(LOTTERY_ID);
            this.intentFrom = getIntent().getExtras().getString(INTENT_FROM);
        }

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvDetail.getRefreshableView().setLayoutManager(mLayoutManager);
        mRvDetail.getRefreshableView().setItemAnimator(new DefaultItemAnimator());
        mGoodsDetailAdapter = new GoodsDetailAdapter();
        setHeader(mRvDetail.getRefreshableView());
        mRvDetail.getRefreshableView().setAdapter(mGoodsDetailAdapter);
        mGoodsDetailAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {

            }
        });
        //showNewsDetailFragment(news);
    }
    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.item_good_detail_first, view, false);
        mHeadView = header;
        mGoodsDetailAdapter.setHeaderView(header);
    }

    private void loadGoodsDetailById() {
        MyRetrofitManager.builder().
        getGoodsDetailById(this.lotteryProductId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(new Action0() {
            @Override
            public void call() {
                // showProgress();
            }
        }).map(new Func1<GoodsDetail, GoodsDetail>() {
            @Override
            public GoodsDetail call(GoodsDetail goodsDetail) {
                L.e(goodsDetail.getId());
                return goodsDetail;
            }
        }).subscribe(new Action1<GoodsDetail>() {
            @Override
            public void call(GoodsDetail goodsDetail) {
                L.e(goodsDetail.getId());
            }
        },new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                L.e(throwable,"Load news detail error");
            }
        });
    }
}
