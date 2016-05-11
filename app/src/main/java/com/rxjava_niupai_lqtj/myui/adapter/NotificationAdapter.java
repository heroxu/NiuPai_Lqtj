package com.rxjava_niupai_lqtj.myui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rxjava_niupai_lqtj.R;
import com.rxjava_niupai_lqtj.bean.Notifications;
import com.rxjava_niupai_lqtj.myui.activity.GoodsDetailActivity;
import com.rxjava_niupai_lqtj.myui.view.Anticlockwise;
import com.rxjava_niupai_lqtj.network.manager.MyRetrofitManager;
import com.rxjava_niupai_lqtj.utils.DensityUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xuxairong on 2016/5/4.
 */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder>{

    private Context mContext;
    private List<Notifications> mList;
    private long lastPos = -1;
    private boolean isAnim = true;
    final String  IntentFrom = "FragmentNotification";
    private OnItemClickListener mListener;
//    int   productEndDate = 0;
    public NotificationAdapter(Context context, List<Notifications> list){
        this.mContext = context;
        this.mList = list;
    }

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    @Override
    public NotificationAdapter.NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false);
        return new NotificationHolder(view);
    }

    @Override
    public void onBindViewHolder( NotificationAdapter.NotificationHolder holder, int position) {
        final Notifications notifications = mList.get(position);
        if(notifications == null){
            return;
        }
        int productEndDate = Integer.valueOf(notifications.getLotteryProductEndDate());

        //holder.mBuyTxtConten.setText(content);
        DisplayMetrics dm = new DisplayMetrics();

        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        float wdith = dm.widthPixels;
        float wdithDp = DensityUtil.px2dip(mContext,wdith);
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.mBuyCvItem.getLayoutParams();
        params.width = dm.widthPixels/2;
        params.height = dm.widthPixels*2/3;
        holder.mBuyCvItem.setLayoutParams(params);
        Glide.with(mContext).load(MyRetrofitManager.BASE_NIUPAI_URL+notifications.getLotteryProductImg()).placeholder(R.drawable.ic_placeholder).into(holder.mNotiImgPic);
        holder.mNotiTxtName.setText(notifications.getLotteryProductName());
        holder.mNotiTxtNumber.setText(notifications.getLotteryProductPeriod());
        holder.mNotiChromTime.initTime(productEndDate/60,productEndDate%60);
        holder.mNotiChromTime.start();
        //计时器完成
        holder.mNotiChromTime.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener()
        {
            @Override
            public void onTimeComplete()
            {
                Toast.makeText(mContext, "计时完成!", Toast.LENGTH_SHORT).show();
            }
        });
//        holder.mNotiChromTime.setBase(SystemClock.elapsedRealtime());
//        holder.mNotiChromTime.start();
//
//        changeChronometerTime(holder.mNotiChromTime,productEndDate);
//        holder.mNotiChromTime.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
//            int endTime = productEndDate;
//            @Override
//            public void onChronometerTick(Chronometer chronometer) {
//                endTime--;
//            if (SystemClock.elapsedRealtime() - chronometer.getBase() > productEndDate * 1000) {
//                chronometer.stop();
//                chronometer.setVisibility(View.GONE);
//
//            } else {
//                //changeChronometerTime(chronometer,endTime);
//                chronometer.setText(FormatMiss(endTime));
//            }
//            }
//        });

        if(mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //    mListener.onItemClick(position, notifications.getLotteryProductName());
                    GoodsDetailActivity.start(mContext,notifications.getLotteryProductId(),IntentFrom);
                }
            });
        }
        if(isAnim) {
            startAnimator(holder.mBuyCvItem, position);
        }
    }

//    public  String FormatMiss(int miss){
//        String hh=miss/3600>9?miss/3600+"":"0"+miss/3600;
//        String  mm=(miss % 3600)/60>9?(miss % 3600)/60+"":"0"+(miss % 3600)/60;
//        String ss=(miss % 3600) % 60>9?(miss % 3600) % 60+"":"0"+(miss % 3600) % 60;
//        return hh+":"+mm+":"+ss;
//    }
//
//    private void changeChronometerTime(Chronometer chronometer,int time){
//        if(time>0){
//            String hour = time/3600<=9?("0"+time/3600):(""+time/3600);
//            String munite = time%3600/60<=9?("0"+time%3600/60):(""+time%3600/60);
//            String second = time%60<=9?("0"+time%60):(""+time%60);
//            chronometer.setText(hour + " : " + munite + " : " + second);
//        }
//
//    }
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private void startAnimator(View view, long position) {
        if (position > lastPos) {
            view.startAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.item_bottom_in));
            lastPos = position;
        }
    }



    @Override
    public void onViewDetachedFromWindow(NotificationHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.mBuyCvItem.clearAnimation();
    }

    class NotificationHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.buy_rl_item)
        CardView mBuyCvItem;
        @Bind(R.id.noti_img_pic)
        ImageView mNotiImgPic;
        @Bind(R.id.noti_txt_name)
        TextView mNotiTxtName;
        @Bind(R.id.noti_txt_number)
        TextView mNotiTxtNumber;
        @Bind(R.id.noti_chronometer_time)
        Anticlockwise mNotiChromTime;
        public NotificationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void changeData(List<Notifications> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void addData(List<Notifications> list) {
        if (this.mList == null) {
            changeData(list);
        } else {
            this.mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setmNotificationsList(List<Notifications> list) {
        this.mList = list;
    }

    public List<Notifications> getmNotificationsList() {
        return mList;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T data);
    }
}
