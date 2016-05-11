package com.rxjava_niupai_lqtj.myui.activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rxjava_niupai_lqtj.App;
import com.rxjava_niupai_lqtj.R;
import com.rxjava_niupai_lqtj.base.BaseNotSwipeActivity;
import com.rxjava_niupai_lqtj.myui.adapter.MainFragmentAdapter;
import com.rxjava_niupai_lqtj.myui.fragment.FragmentBuyGood;
import com.rxjava_niupai_lqtj.myui.fragment.FragmentMyCart;
import com.rxjava_niupai_lqtj.myui.fragment.FragmentNotification;
import com.rxjava_niupai_lqtj.myui.fragment.FragmentPerson;
import com.rxjava_niupai_lqtj.myui.fragment.FragmentShowBill;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xuxiarong on 2016/5/4.
 */
public class MainActivity extends BaseNotSwipeActivity{

//    @Bind(R.id.fl_main)
//    ViewGroup mViewGroup;
//    @Bind(R.id.iv_main)
//    ImageView mIvMain;
    @Bind(R.id.main_img_washgold)
    ImageView mImgWashgod;
    @Bind(R.id.main_img_announced)
    ImageView mImgAnniunced;
    @Bind(R.id.main_img_discover)
    ImageView mImgDiscover;
    @Bind(R.id.main_img_bill)
    ImageView mImgBill;
    @Bind(R.id.main_img_person)
    ImageView mImgPerson;


    @Bind(R.id.main_txt_washgold)
    TextView mTxtWashgod;
    @Bind(R.id.main_txt_announced)
    TextView mTxtAnniunced;
    @Bind(R.id.main_txt_discover)
    TextView mTxtDiscover;
    @Bind(R.id.main_txt_bill)
    TextView mTxtBill;
    @Bind(R.id.main_txt_person)
    TextView mTxtPerson;

    @Bind(R.id.rl_washgold)
    RelativeLayout mRlWashgod;
    @Bind(R.id.rl_announced)
    RelativeLayout mRlAnniunced;
    @Bind(R.id.rl_discover)
    RelativeLayout mRlDiscover;
    @Bind(R.id.rl_bill)
    RelativeLayout mRlBill;
    @Bind(R.id.rl_person)
    RelativeLayout mRlPerson;

    @Bind(R.id.main_vp_pager)
    ViewPager mVpPager;

    private final long ANIMTION_TIME = 1000;

    private FragmentBuyGood mFragmentBuyGood;
    private FragmentNotification mFragmentNotification;
    private FragmentShowBill mFragmentShowBill;
    private FragmentMyCart mFragmentMyCart;
    private FragmentPerson mFragmentPerson;
    private ArrayList<Fragment> mFragmentsList = new ArrayList<Fragment>();

    private Context mContext;
    private App app;
    List<RelativeLayout> mViewIds = new ArrayList<RelativeLayout>();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mFragmentBuyGood.isAdded()){
            getFragmentManager().putFragment(outState,FragmentBuyGood.class.getName(),mFragmentBuyGood);
        }
        if(mFragmentNotification.isAdded()){
            getFragmentManager().putFragment(outState,FragmentNotification.class.getName(),mFragmentNotification);
        }
        if(mFragmentShowBill.isAdded()){
            getFragmentManager().putFragment(outState,FragmentShowBill.class.getName(),mFragmentShowBill);
        }
        if(mFragmentMyCart.isAdded()){
            getFragmentManager().putFragment(outState,FragmentMyCart.class.getName(),mFragmentMyCart);
        }
        if(mFragmentPerson.isAdded()){
            getFragmentManager().putFragment(outState,FragmentPerson.class.getName(),mFragmentPerson);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_main;
    }

    @Override
    protected void afterCreate(Bundle savedState) {
        mContext = this;
        app = (App)mContext.getApplicationContext();
        app.addClearActivity(this);
        mViewIds.add(mRlWashgod);
        mViewIds.add(mRlAnniunced);
        mViewIds.add(mRlDiscover);
        mViewIds.add(mRlBill);
        mViewIds.add(mRlPerson);
        initData(savedState);
    }

    private void initData(Bundle savedState) {
        mContext = this;
        app = (App) getApplication();
        app.addClearActivity(this);
        if (savedState == null) {
            mFragmentBuyGood = FragmentBuyGood.newInstance();
            mFragmentNotification = FragmentNotification.newInstance();
            mFragmentShowBill = FragmentShowBill.newInstance();
            mFragmentMyCart = FragmentMyCart.newInstance();
            mFragmentPerson = FragmentPerson.newInstance();
        } else {
            mFragmentBuyGood = (FragmentBuyGood) getFragmentManager().getFragment(savedState,FragmentBuyGood.class.getName());
            mFragmentNotification = (FragmentNotification) getFragmentManager().getFragment(savedState,FragmentNotification.class.getName());
            mFragmentShowBill = (FragmentShowBill) getFragmentManager().getFragment(savedState,FragmentShowBill.class.getName());
            mFragmentMyCart = (FragmentMyCart) getFragmentManager().getFragment(savedState,FragmentMyCart.class.getName());
            mFragmentPerson = (FragmentPerson) getFragmentManager().getFragment(savedState,FragmentPerson.class.getName());
        }
        mFragmentsList.add(mFragmentBuyGood);
        mFragmentsList.add(mFragmentNotification);
        mFragmentsList.add(mFragmentShowBill);
        mFragmentsList.add(mFragmentMyCart);
        mFragmentsList.add(mFragmentPerson);
        mVpPager.setAdapter(new MainFragmentAdapter(getFragmentManager(),mFragmentsList));
        mVpPager.setOffscreenPageLimit(5);
        mVpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                pickDoor(mViewIds.get(arg0));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        mVpPager.setCurrentItem(0);
    }

    @OnClick({ R.id.rl_washgold, R.id.rl_announced, R.id.rl_discover,R.id.rl_bill,R.id.rl_person})
    public void pickDoor(RelativeLayout rlView) {
        switch (rlView.getId()) {
            case R.id.rl_washgold:
                mVpPager.setCurrentItem(0);
                mImgWashgod.setImageResource(R.drawable.bottom_main_press);
                mTxtWashgod.setTextColor(this.getResources().getColor(R.color.theme_btn_red));
                mImgAnniunced.setImageResource(R.drawable.bottom_collect_normal);
                mTxtAnniunced.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgDiscover.setImageResource(R.drawable.bottom_msg_normal);
                mTxtDiscover.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgBill.setImageResource(R.drawable.bottom_cart_normal);
                mTxtBill.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgPerson.setImageResource(R.drawable.bottom_my_normal);
                mTxtPerson.setTextColor(this.getResources().getColor(R.color.darkgray));
                break;
            case R.id.rl_announced:
                mVpPager.setCurrentItem(1);
                mImgWashgod.setImageResource(R.drawable.bottom_main_normal);
                mTxtWashgod.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgAnniunced.setImageResource(R.drawable.bottom_collect_press);
                mTxtAnniunced.setTextColor(this.getResources().getColor(R.color.theme_btn_red));
                mImgDiscover.setImageResource(R.drawable.bottom_msg_normal);
                mTxtDiscover.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgBill.setImageResource(R.drawable.bottom_cart_normal);
                mTxtBill.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgPerson.setImageResource(R.drawable.bottom_my_normal);
                mTxtPerson.setTextColor(this.getResources().getColor(R.color.darkgray));
                break;
            case R.id.rl_discover:
                mVpPager.setCurrentItem(2);
                mImgWashgod.setImageResource(R.drawable.bottom_main_normal);
                mTxtWashgod.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgAnniunced.setImageResource(R.drawable.bottom_collect_normal);
                mTxtAnniunced.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgDiscover.setImageResource(R.drawable.bottom_msg_press);
                mTxtDiscover.setTextColor(this.getResources().getColor(R.color.theme_btn_red));
                mImgBill.setImageResource(R.drawable.bottom_cart_normal);
                mTxtBill.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgPerson.setImageResource(R.drawable.bottom_my_normal);
                mTxtPerson.setTextColor(this.getResources().getColor(R.color.darkgray));
                break;
            case R.id.rl_bill:
                mVpPager.setCurrentItem(3);
                mImgWashgod.setImageResource(R.drawable.bottom_main_normal);
                mTxtWashgod.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgAnniunced.setImageResource(R.drawable.bottom_collect_normal);
                mTxtAnniunced.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgDiscover.setImageResource(R.drawable.bottom_msg_normal);
                mTxtDiscover.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgBill.setImageResource(R.drawable.bottom_cart_press);
                mTxtBill.setTextColor(this.getResources().getColor(R.color.theme_btn_red));
                mImgPerson.setImageResource(R.drawable.bottom_my_normal);
                mTxtPerson.setTextColor(this.getResources().getColor(R.color.darkgray));
                break;
            case R.id.rl_person:
                mVpPager.setCurrentItem(4);
                mImgWashgod.setImageResource(R.drawable.bottom_main_normal);
                mTxtWashgod.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgAnniunced.setImageResource(R.drawable.bottom_collect_normal);
                mTxtAnniunced.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgDiscover.setImageResource(R.drawable.bottom_msg_normal);
                mTxtDiscover.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgBill.setImageResource(R.drawable.bottom_cart_normal);
                mTxtBill.setTextColor(this.getResources().getColor(R.color.darkgray));
                mImgPerson.setImageResource(R.drawable.bottom_my_normal2);
                mTxtPerson.setTextColor(this.getResources().getColor(R.color.theme_btn_red));
                break;
            default:
                break;
        }
    }
}
