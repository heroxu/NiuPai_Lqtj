package com.rxjava_niupai_lqtj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZXY on 2016/5/9.
 */
public class ShareImage implements Parcelable {
   private String uid;//": 图片id
    private String shareInfoId;//": 所属晒单id
    private String images;//": 图片文件名

public ShareImage(){}

    protected ShareImage(Parcel in) {
        uid = in.readString();
        shareInfoId = in.readString();
        images = in.readString();
    }

    public static final Creator<ShareImage> CREATOR = new Creator<ShareImage>() {
        @Override
        public ShareImage createFromParcel(Parcel in) {
            return new ShareImage(in);
        }

        @Override
        public ShareImage[] newArray(int size) {
            return new ShareImage[size];
        }
    };

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }


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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(shareInfoId);
        dest.writeString(images);
    }
}
