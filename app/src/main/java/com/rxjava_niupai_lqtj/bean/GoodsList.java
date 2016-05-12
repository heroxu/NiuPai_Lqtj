package com.rxjava_niupai_lqtj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2016/5/6.
 */
public class GoodsList implements Parcelable{
    private String productId; // 商品id
    private String spellbuyProductId; // 商品序号
    private String productName; // 商品名称
    private String productPrice; // 商品总价
    private String singlePrice; // 商品最小购买单位
    private String spellbuyLimit; // 商品限购份数
    private String productTitle; // 商品标题
    private String headImage; // 商品缩略图地址
    private String currentBuyCount; // 当前已购买份数
    private String buyer; // null
    private String userId; // null
    private String productPeriod; // 商品期号
    private String buyDate; // null
    private String buyCount; // null
    private String productStyle; // 显示样式 goods_xp(新品), goods_rq(人气), goods_tj(推荐), goods_xs(限时)
    
    public GoodsList(){
        
    }

    protected GoodsList(Parcel in) {
        productId = in.readString();
        spellbuyProductId = in.readString();
        productName = in.readString();
        productPrice = in.readString();
        singlePrice = in.readString();
        spellbuyLimit = in.readString();
        productTitle = in.readString();
        headImage = in.readString();
        currentBuyCount = in.readString();
        buyer = in.readString();
        userId = in.readString();
        productPeriod = in.readString();
        buyDate = in.readString();
        buyCount = in.readString();
        productStyle = in.readString();
    }

    public static final Creator<GoodsList> CREATOR = new Creator<GoodsList>() {
        @Override
        public GoodsList createFromParcel(Parcel in) {
            return new GoodsList(in);
        }

        @Override
        public GoodsList[] newArray(int size) {
            return new GoodsList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productId);
        parcel.writeString(spellbuyProductId);
        parcel.writeString(productName);
        parcel.writeString(productPrice);
        parcel.writeString(singlePrice);
        parcel.writeString(spellbuyLimit);
        parcel.writeString(productTitle);
        parcel.writeString(headImage);
        parcel.writeString(currentBuyCount);
        parcel.writeString(buyer);
        parcel.writeString(userId);
        parcel.writeString(productPeriod);
        parcel.writeString(buyDate);
        parcel.writeString(buyCount);
        parcel.writeString(productStyle);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSpellbuyProductId() {
        return spellbuyProductId;
    }

    public void setSpellbuyProductId(String spellbuyProductId) {
        this.spellbuyProductId = spellbuyProductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(String singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getSpellbuyLimit() {
        return spellbuyLimit;
    }

    public void setSpellbuyLimit(String spellbuyLimit) {
        this.spellbuyLimit = spellbuyLimit;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getCurrentBuyCount() {
        return currentBuyCount;
    }

    public void setCurrentBuyCount(String currentBuyCount) {
        this.currentBuyCount = currentBuyCount;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(String productPeriod) {
        this.productPeriod = productPeriod;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount;
    }

    public String getProductStyle() {
        return productStyle;
    }

    public void setProductStyle(String productStyle) {
        this.productStyle = productStyle;
    }

    public static Creator<GoodsList> getCREATOR() {
        return CREATOR;
    }
}
