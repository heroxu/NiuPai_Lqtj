package com.rxjava_niupai_lqtj.myui.activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.ImageView;

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
    @Bind(R.id.main_img_buy)
    ImageView mImgBuy;
    @Bind(R.id.main_img_noti)
    ImageView mImgNoti;
    @Bind(R.id.main_img_showbill)
    ImageView mImgShowbill;
    @Bind(R.id.main_img_cart)
    ImageView mImgCart;
    @Bind(R.id.main_img_person)
    ImageView mImgPerson;

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
    List<ImageView> mViewIds = new ArrayList<ImageView>();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_main;
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
    protected void afterCreate(Bundle savedState) {
        mContext = this;
        app = (App)mContext.getApplicationContext();
        app.addClearActivity(this);
        mViewIds.add(mImgBuy);
        mViewIds.add(mImgNoti);
        mViewIds.add(mImgShowbill);
        mViewIds.add(mImgCart);
        mViewIds.add(mImgPerson);
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
                selectCurrentItem(mViewIds.get(arg0));
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

    @OnClick({ R.id.main_img_buy, R.id.main_img_noti, R.id.main_img_showbill,R.id.main_img_cart,R.id.main_img_person})
    public void selectCurrentItem(ImageView rlView) {
        switch (rlView.getId()) {
            case R.id.main_img_buy:
                mVpPager.setCurrentItem(0);
                mImgBuy.setImageResource(R.drawable.buy_press);
                mImgNoti.setImageResource(R.drawable.noti_no_press);
                mImgShowbill.setImageResource(R.drawable.show_bill_no_press);
                mImgCart.setImageResource(R.drawable.cart_no_press);
                mImgPerson.setImageResource(R.drawable.person_no_press);
                break;
            case R.id.main_img_noti:
                mVpPager.setCurrentItem(1);
                mImgBuy.setImageResource(R.drawable.buy_no_press);
                mImgNoti.setImageResource(R.drawable.noti_press);
                mImgShowbill.setImageResource(R.drawable.show_bill_no_press);
                mImgCart.setImageResource(R.drawable.cart_no_press);
                mImgPerson.setImageResource(R.drawable.person_no_press);
                break;
            case R.id.main_img_showbill:
                mVpPager.setCurrentItem(2);
                mImgBuy.setImageResource(R.drawable.buy_no_press);
                mImgNoti.setImageResource(R.drawable.noti_no_press);
                mImgShowbill.setImageResource(R.drawable.show_bill_press);
                mImgCart.setImageResource(R.drawable.cart_no_press);
                mImgPerson.setImageResource(R.drawable.person_no_press);
                break;
            case R.id.main_img_cart:
                mVpPager.setCurrentItem(3);
                mImgBuy.setImageResource(R.drawable.buy_no_press);
                mImgNoti.setImageResource(R.drawable.noti_no_press);
                mImgShowbill.setImageResource(R.drawable.show_bill_no_press);
                mImgCart.setImageResource(R.drawable.cart_press);
                mImgPerson.setImageResource(R.drawable.person_no_press);
                break;
            case R.id.main_img_person:
                mVpPager.setCurrentItem(4);
                mImgBuy.setImageResource(R.drawable.buy_no_press);
                mImgNoti.setImageResource(R.drawable.noti_no_press);
                mImgShowbill.setImageResource(R.drawable.show_bill_no_press);
                mImgCart.setImageResource(R.drawable.cart_no_press);
                mImgPerson.setImageResource(R.drawable.person_press);
                break;
            default:
                break;
        }
    }
}
