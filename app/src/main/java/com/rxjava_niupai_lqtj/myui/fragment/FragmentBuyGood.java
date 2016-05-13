package com.rxjava_niupai_lqtj.myui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rxjava_niupai_lqtj.App;
import com.rxjava_niupai_lqtj.R;
import com.rxjava_niupai_lqtj.base.BaseFragment;
import com.rxjava_niupai_lqtj.base.BaseRecyclerAdapter;
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
public class FragmentBuyGood extends BaseFragment implements View.OnClickListener {

    private final String HOT20 = "hot20";
    private final String DATE20 = "date20";
    private final String PRICE20_HIGHT = "price20";
    private final String PRICEAsSC20 = "priceAsc20";
    private final String ABOUT20 = "about20";
    private final String SURPLUS = "surplus20";
    private boolean isPrice20 = true;

    @Bind(R.id.fragment_buy_txt_title)
    TextView mTxtTitle;
    @Bind(R.id.fragment_buy_img_search)
    ImageView mImgSearch;
    @Bind(R.id.fragment_buy_rv_goods)
    RecyclerViewBaseOnPullToRefresh mBuyRv;

    private Kanner mKanner;

    private ImageView buy_img_classfly;
    private ImageView buy_img_game_area;
    private ImageView buy_img_show_bill;
    private ImageView buy_img_normal_probelm;

    private TextView buy_txt_noti_winner;

    private TextView buy_txt_popularity;
    private TextView buy_txt_newest;
    private TextView buy_txt_progress;
    private TextView buy_txt_all_person;
    private RelativeLayout buy_rl_all_person;

    private ImageView buy_img_arrow_up;
    private ImageView buy_img_arrow_donw;


    private App app;
    private Context mContext;
    private RecyclerView.LayoutManager mLayoutManager;
    private GoodListAdapter mGoodListAdapter;
    private String[] imgUrls;       //轮播图需要的url数组
    private String id = "hot20";    //商品默认查询id

    private String typeId;          //商品默认分类id
    private String pageNo = "0";    //商品默认页数
    private String pageSize = "10"; //商品默认每页个数
    private List<IndexImgList> mIndexImgLists = new ArrayList<IndexImgList>();
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
        mGoodListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<GoodsList>() {
            @Override
            public void onItemClick(int position, GoodsList data) {
                Toast.makeText(mContext, position + "," + data.getProductName(), Toast.LENGTH_SHORT).show();
            }
        });
        loadHeaderViewData();
        loadGoodListData();
    }


    /**
     * 给RecycleView设置头部的View
     * @param view
     */
    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_buy_frist, view, false);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "toubeidianji", Toast.LENGTH_SHORT).show();

            }
        });

        mKanner = (Kanner) header.findViewById(R.id.fragment_buy_kanner);

        buy_img_classfly = (ImageView) header.findViewById(R.id.buy_img_classfly);
        buy_img_classfly.setOnClickListener(this);
        buy_img_game_area = (ImageView) header.findViewById(R.id.buy_img_game_area);
        buy_img_game_area.setOnClickListener(this);
        buy_img_show_bill = (ImageView) header.findViewById(R.id.buy_img_show_bill);
        buy_img_show_bill.setOnClickListener(this);
        buy_img_normal_probelm = (ImageView) header.findViewById(R.id.buy_img_normal_probelm);
        buy_img_normal_probelm.setOnClickListener(this);

        buy_txt_noti_winner = (TextView) header.findViewById(R.id.buy_txt_noti_winner);
        buy_txt_noti_winner.setOnClickListener(this);

        buy_txt_popularity = (TextView) header.findViewById(R.id.buy_txt_popularity);
        buy_txt_popularity.setOnClickListener(this);
        buy_txt_newest = (TextView) header.findViewById(R.id.buy_txt_newest);
        buy_txt_newest.setOnClickListener(this);
        buy_txt_progress = (TextView) header.findViewById(R.id.buy_txt_progress);
        buy_txt_progress.setOnClickListener(this);
        buy_txt_all_person = (TextView) header.findViewById(R.id.buy_txt_all_person);
        buy_rl_all_person = (RelativeLayout) header.findViewById(R.id.buy_rl_all_person);
        buy_rl_all_person.setOnClickListener(this);

        buy_img_arrow_up = (ImageView) header.findViewById(R.id.buy_img_arrow_up);
        buy_img_arrow_donw = (ImageView) header.findViewById(R.id.buy_img_arrow_donw);

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
                    public void call(final List<IndexImgList> result) {
                        if(result!=null && result.size()>0) {
                            mIndexImgLists = result;
                            imgUrls = new String[result.size()];
                            for (int i = 0; i < result.size(); i++) {
                                imgUrls[i] = MyRetrofitManager.BASE_IMAGE_URL + result.get(i).getProImg();
                            }
                            mKanner.setImagesUrl(imgUrls);
                            mKanner.setIndexImgLists(result);
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


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.buy_img_classfly:
                Toast.makeText(mContext,"分类",Toast.LENGTH_SHORT).show();

                break;
            case  R.id.buy_img_game_area:
                Toast.makeText(mContext,"游戏",Toast.LENGTH_SHORT).show();
                break;

            case  R.id.buy_img_show_bill:
                Toast.makeText(mContext,"晒单",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.buy_img_normal_probelm:
                Toast.makeText(mContext,"常见问题",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.buy_txt_noti_winner:


                Toast.makeText(mContext,"中奖者",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.buy_txt_popularity:
                this.id = HOT20;
                buy_txt_popularity.setTextColor(getResources().getColor(R.color.theme_btn_red));
                buy_txt_newest.setTextColor(getResources().getColor(R.color.txt_normal_title));
                buy_txt_progress.setTextColor(getResources().getColor(R.color.txt_normal_title));
                buy_txt_all_person.setTextColor(getResources().getColor(R.color.txt_normal_title));
                Toast.makeText(mContext,"人气",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.buy_txt_newest:
                this.id = DATE20;
                buy_txt_popularity.setTextColor(getResources().getColor(R.color.txt_normal_title));
                buy_txt_newest.setTextColor(getResources().getColor(R.color.theme_btn_red));
                buy_txt_progress.setTextColor(getResources().getColor(R.color.txt_normal_title));
                buy_txt_all_person.setTextColor(getResources().getColor(R.color.txt_normal_title));
                Toast.makeText(mContext,"最新",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.buy_txt_progress:
                this.id = SURPLUS;
                buy_txt_popularity.setTextColor(getResources().getColor(R.color.txt_normal_title));
                buy_txt_newest.setTextColor(getResources().getColor(R.color.txt_normal_title));
                buy_txt_progress.setTextColor(getResources().getColor(R.color.theme_btn_red));
                buy_txt_all_person.setTextColor(getResources().getColor(R.color.txt_normal_title));
                Toast.makeText(mContext,"进度",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.buy_rl_all_person:

                this.id = isPrice20?PRICEAsSC20:PRICE20_HIGHT;
                buy_img_arrow_up.setVisibility(isPrice20?View.VISIBLE:View.GONE);
                buy_img_arrow_donw.setVisibility(isPrice20?View.GONE:View.VISIBLE);
                isPrice20 = !isPrice20;
                buy_txt_popularity.setTextColor(getResources().getColor(R.color.txt_normal_title));
                buy_txt_newest.setTextColor(getResources().getColor(R.color.txt_normal_title));
                buy_txt_progress.setTextColor(getResources().getColor(R.color.txt_normal_title));
                buy_txt_all_person.setTextColor(getResources().getColor(R.color.theme_btn_red));
                Toast.makeText(mContext,"总需人次",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
