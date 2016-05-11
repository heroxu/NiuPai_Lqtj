package com.rxjava_niupai_lqtj.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ZXY on 2016/5/9.
 */
public class ShowBill implements Parcelable {

/*
    "uid": 晒单id
    "shareTitle": 晒单标题
    "shareContent": 晒单内容（支持HTML）
            "shareimageList": [	//晒单图片（JSON数组）
    {
        "uid": 图片id
        "shareInfoId": 所属晒单id
        "images": 图片文件名
    },
    {
        "uid": 图片id
        "shareInfoId": 所属晒单id
        "images": 图片文件名
    }
    ],
            "shareImages": 晒单首图文件名
    "upCount": 顶贴数量
    "replyCount": 回复数量
    "reward": 奖励福分数
    "shareDate": 晒单时间
    "announcedTime": 商品揭晓时间
    "userName": 获奖者昵称
    "userFace": 获奖者头像地址
    "userId": 获奖者id
    "status": null
*/

    private String uid; //晒单id
    private String shareTitle;//": 晒单标题
    private String shareContent;//": 晒单内容（支持HTML）
    private List<ShareImage> shareimageList;//": [	//晒单图片列表
    private String shareImages;//": 晒单首图文件名
    private String upCount;//": 顶贴数量
    private String replyCount;//": 回复数量
    private String reward;//": 奖励福分数
    private String shareDate;//": 晒单时间
    private String announcedTime;//": 商品揭晓时间
    private String userName;//": 获奖者昵称
    private String userFace;//": 获奖者头像地址
    private String userId;//": 获奖者id
    private String status;//": null

    public ShowBill() {
    }

    protected ShowBill(Parcel in) {
        uid = in.readString();
        shareTitle = in.readString();
        shareContent = in.readString();
        shareImages = in.readString();
        upCount = in.readString();
        replyCount = in.readString();
        reward = in.readString();
        shareDate = in.readString();
        announcedTime = in.readString();
        userName = in.readString();
        userFace = in.readString();
        userId = in.readString();
        status = in.readString();
    }

    public static final Creator<ShowBill> CREATOR = new Creator<ShowBill>() {
        @Override
        public ShowBill createFromParcel(Parcel in) {
            return new ShowBill(in);
        }

        @Override
        public ShowBill[] newArray(int size) {
            return new ShowBill[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public List<ShareImage> getShareimageList() {
        return shareimageList;
    }

    public void setShareimageList(List<ShareImage> shareimageList) {
        this.shareimageList = shareimageList;
    }

    public static Creator<ShowBill> getCREATOR() {
        return CREATOR;
    }

    public String getShareImages() {
        return shareImages;
    }

    public void setShareImages(String shareImages) {
        this.shareImages = shareImages;
    }

    public String getUpCount() {
        return upCount;
    }

    public void setUpCount(String upCount) {
        this.upCount = upCount;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getShareDate() {
        return shareDate;
    }

    public void setShareDate(String shareDate) {
        this.shareDate = shareDate;
    }

    public String getAnnouncedTime() {
        return announcedTime;
    }

    public void setAnnouncedTime(String announcedTime) {
        this.announcedTime = announcedTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFace() {
        return userFace;
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(shareTitle);
        dest.writeString(shareContent);
        dest.writeString(shareImages);
        dest.writeString(upCount);
        dest.writeString(replyCount);
        dest.writeString(reward);
        dest.writeString(shareDate);
        dest.writeString(announcedTime);
        dest.writeString(userName);
        dest.writeString(userFace);
        dest.writeString(userId);
        dest.writeString(status);
    }
}


