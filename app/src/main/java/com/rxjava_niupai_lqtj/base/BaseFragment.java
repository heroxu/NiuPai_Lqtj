package com.rxjava_niupai_lqtj.base;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by laucherish on 16/3/15.
 */
public abstract class BaseFragment extends Fragment {

    public static final String TAG = BaseFragment.class.getSimpleName();
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        afterCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


//    public static void saveFragment(Activity activity,Bundle bundle,BaseFragment baseFragment){
//        activity.getFragmentManager().putFragment(bundle,baseFragment.getClass().getName(),baseFragment);
//    }
//    public static BaseFragment getFragment(Activity activity,Bundle bundle,String fragmentName){
//        BaseFragment baseFragment =(BaseFragment) activity.getFragmentManager().getFragment(bundle,fragmentName);
//        return baseFragment;
//    }
    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);
}
