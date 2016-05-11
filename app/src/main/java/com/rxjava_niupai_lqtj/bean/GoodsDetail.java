package com.rxjava_niupai_lqtj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2016/5/10.
 */
public class GoodsDetail implements Parcelable{
    private String id;// 开奖id
    private String productId;// 商品id
    private String productName;// 商品名称
    private String productTitle;// 商品标题
    private String productPrice;// 商品总价
    private String productImg;// 商品缩略图地址
    private String productPeriod;// 商品期号
    private String userName;// 中奖者昵称
    private String location;// 中奖者地点
    private String announcedTime;// 揭晓时间
    private String announcedType;// 揭晓类型
    private String buyTime;// 购买时间
    private String spellbuyRecordId;// 购买序号
    private String spellbuyProductId;// 商品序号
    private String randomNumber;// 幸运号码
    private String dateSum;// 最后100个时间和
    private String sscNumber;// 时时彩号码
    private String sscPeriod;// 时时彩期数
    private String buyNumberCount;// 中奖者购买份数
    private String userId;// 中奖者id
    private String buyUser;// 中奖者
    private String userFace;// 中奖者头像地址
    private String status;// 交易状态 1未提交收获地址 2等待发货 3等待收货 4已确认收货 10交易完成 11交易取消
    private String shareStatus;// 晒单状态 -1 暂未晒单 0等待审核 1未审核通过2审核通过
    private String shareId;// 晒单ID

    public GoodsDetail(){

    }

    protected GoodsDetail(Parcel in) {
        id = in.readString();
        productId = in.readString();
        productName = in.readString();
        productTitle = in.readString();
        productPrice = in.readString();
        productImg = in.readString();
        productPeriod = in.readString();
        userName = in.readString();
        location = in.readString();
        announcedTime = in.readString();
        announcedType = in.readString();
        buyTime = in.readString();
        spellbuyRecordId = in.readString();
        spellbuyProductId = in.readString();
        randomNumber = in.readString();
        dateSum = in.readString();
        sscNumber = in.readString();
        sscPeriod = in.readString();
        buyNumberCount = in.readString();
        userId = in.readString();
        buyUser = in.readString();
        userFace = in.readString();
        status = in.readString();
        shareStatus = in.readString();
        shareId = in.readString();
    }

    public static final Creator<GoodsDetail> CREATOR = new Creator<GoodsDetail>() {
        @Override
        public GoodsDetail createFromParcel(Parcel in) {
            return new GoodsDetail(in);
        }

        @Override
        public GoodsDetail[] newArray(int size) {
            return new GoodsDetail[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(String productPeriod) {
        this.productPeriod = productPeriod;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAnnouncedTime() {
        return announcedTime;
    }

    public void setAnnouncedTime(String announcedTime) {
        this.announcedTime = announcedTime;
    }

    public String getAnnouncedType() {
        return announcedType;
    }

    public void setAnnouncedType(String announcedType) {
        this.announcedType = announcedType;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getSpellbuyRecordId() {
        return spellbuyRecordId;
    }

    public void setSpellbuyRecordId(String spellbuyRecordId) {
        this.spellbuyRecordId = spellbuyRecordId;
    }

    public String getSpellbuyProductId() {
        return spellbuyProductId;
    }

    public void setSpellbuyProductId(String spellbuyProductId) {
        this.spellbuyProductId = spellbuyProductId;
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getDateSum() {
        return dateSum;
    }

    public void setDateSum(String dateSum) {
        this.dateSum = dateSum;
    }

    public String getSscNumber() {
        return sscNumber;
    }

    public void setSscNumber(String sscNumber) {
        this.sscNumber = sscNumber;
    }

    public String getSscPeriod() {
        return sscPeriod;
    }

    public void setSscPeriod(String sscPeriod) {
        this.sscPeriod = sscPeriod;
    }

    public String getBuyNumberCount() {
        return buyNumberCount;
    }

    public void setBuyNumberCount(String buyNumberCount) {
        this.buyNumberCount = buyNumberCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBuyUser() {
        return buyUser;
    }

    public void setBuyUser(String buyUser) {
        this.buyUser = buyUser;
    }

    public String getUserFace() {
        return userFace;
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShareStatus() {
        return shareStatus;
    }

    public void setShareStatus(String shareStatus) {
        this.shareStatus = shareStatus;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(productId);
        parcel.writeString(productName);
        parcel.writeString(productTitle);
        parcel.writeString(productPrice);
        parcel.writeString(productImg);
        parcel.writeString(productPeriod);
        parcel.writeString(userName);
        parcel.writeString(location);
        parcel.writeString(announcedTime);
        parcel.writeString(announcedType);
        parcel.writeString(buyTime);
        parcel.writeString(spellbuyRecordId);
        parcel.writeString(spellbuyProductId);
        parcel.writeString(randomNumber);
        parcel.writeString(dateSum);
        parcel.writeString(sscNumber);
        parcel.writeString(sscPeriod);
        parcel.writeString(buyNumberCount);
        parcel.writeString(userId);
        parcel.writeString(buyUser);
        parcel.writeString(userFace);
        parcel.writeString(status);
        parcel.writeString(shareStatus);
        parcel.writeString(shareId);
    }
}
