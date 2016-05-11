package com.rxjava_niupai_lqtj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xuxiarong on 16/4/29.
 */
public class Notifications implements Parcelable {
//    "lotteryId": id,
//            "lotteryProductId": 开奖id
//    "lotteryProductName": 商品名称
//    "lotteryProductTitle": 商品标题
//    "lotteryProductPrice": 商品总价
//    "lotteryProductPeriod": 商品期号
//    "lotteryProductImg": 商品缩略图地址
//    "lotteryProductEndDate": 离揭晓时间
    String lotteryId;
    String lotteryProductId;
    String lotteryProductName;
    String lotteryProductTitle;
    String lotteryProductPrice;
    String lotteryProductPeriod;
    String lotteryProductImg;
    String lotteryProductEndDate;

    public Notifications(){

    }

    protected Notifications(Parcel in) {
        lotteryId = in.readString();
        lotteryProductId = in.readString();
        lotteryProductName = in.readString();
        lotteryProductTitle = in.readString();
        lotteryProductPrice = in.readString();
        lotteryProductPeriod = in.readString();
        lotteryProductImg = in.readString();
        lotteryProductEndDate = in.readString();
    }

    public String getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(String lotteryId) {
        this.lotteryId = lotteryId;
    }

    public String getLotteryProductId() {
        return lotteryProductId;
    }

    public void setLotteryProductId(String lotteryProductId) {
        this.lotteryProductId = lotteryProductId;
    }

    public String getLotteryProductName() {
        return lotteryProductName;
    }

    public void setLotteryProductName(String lotteryProductName) {
        this.lotteryProductName = lotteryProductName;
    }

    public String getLotteryProductTitle() {
        return lotteryProductTitle;
    }

    public void setLotteryProductTitle(String lotteryProductTitle) {
        this.lotteryProductTitle = lotteryProductTitle;
    }

    public String getLotteryProductPrice() {
        return lotteryProductPrice;
    }

    public void setLotteryProductPrice(String lotteryProductPrice) {
        this.lotteryProductPrice = lotteryProductPrice;
    }

    public String getLotteryProductPeriod() {
        return lotteryProductPeriod;
    }

    public void setLotteryProductPeriod(String lotteryProductPeriod) {
        this.lotteryProductPeriod = lotteryProductPeriod;
    }

    public String getLotteryProductImg() {
        return lotteryProductImg;
    }

    public void setLotteryProductImg(String lotteryProductImg) {
        this.lotteryProductImg = lotteryProductImg;
    }

    public String getLotteryProductEndDate() {
        return lotteryProductEndDate;
    }

    public void setLotteryProductEndDate(String lotteryProductEndDate) {
        this.lotteryProductEndDate = lotteryProductEndDate;
    }

    public static Creator<Notifications> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<Notifications> CREATOR = new Creator<Notifications>() {
        @Override
        public Notifications createFromParcel(Parcel in) {
            return new Notifications(in);
        }

        @Override
        public Notifications[] newArray(int size) {
            return new Notifications[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(lotteryId);
        parcel.writeString(lotteryProductId);
        parcel.writeString(lotteryProductName);
        parcel.writeString(lotteryProductTitle);
        parcel.writeString(lotteryProductPrice);
        parcel.writeString(lotteryProductPeriod);
        parcel.writeString(lotteryProductImg);
        parcel.writeString(lotteryProductEndDate);
    }
}
