package com.rxjava_niupai_lqtj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2016/5/6.
 */
public class Comment implements Parcelable{
//            "uid": 1,
//            "shareInfoId": 208,
//            "reCommentId": null,
//            "userId": 1001639798,
//            "userName": "冯浩",
//            "userFace": "http://wx.qlogo.cn/mmopen/Of1lx0WxraEk7UGjYhLNibkSj53ZvuthiaZuVFf8sR51XSV7C8CxPlicc4MeGKwDcMJia2X4j5qOIzxVGd7icThaCovln6ZbDW0MG/0",
//            "content": "我也要 ",
//            "createDate": "2016-04-14 13:52:52",
//            "reCount": 0
    String uid;
    String shareInfoId;
    String reCommentId;
    String userId;
    String userName;
    String userFace;
    String content;
    String createDate;
    String reCount;

    public Comment(){

    }

    protected Comment(Parcel in) {
        uid = in.readString();
        shareInfoId = in.readString();
        reCommentId = in.readString();
        userId = in.readString();
        userName = in.readString();
        userFace = in.readString();
        content = in.readString();
        createDate = in.readString();
        reCount = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getShareInfoId() {
        return shareInfoId;
    }

    public void setShareInfoId(String shareInfoId) {
        this.shareInfoId = shareInfoId;
    }

    public String getReCommentId() {
        return reCommentId;
    }

    public void setReCommentId(String reCommentId) {
        this.reCommentId = reCommentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getReCount() {
        return reCount;
    }

    public void setReCount(String reCount) {
        this.reCount = reCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(uid);
        parcel.writeString(shareInfoId);
        parcel.writeString(reCommentId);
        parcel.writeString(userId);
        parcel.writeString(userName);
        parcel.writeString(userFace);
        parcel.writeString(content);
        parcel.writeString(createDate);
        parcel.writeString(reCount);
    }
}
