package com.rxjava_niupai_lqtj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2016/5/11.
 */
public class IndexImgList implements Parcelable{

    private String id;// 幻灯片id
    private String proImg;// 幻灯片图片地址
    private String proUrl;// 幻灯片点击跳转链接
    private String seq;// 显示顺序
    private String status;// 状态0-启用，1-禁用
    private String title;// 幻灯片标题

    public IndexImgList(){

    }


    protected IndexImgList(Parcel in) {
        id = in.readString();
        proImg = in.readString();
        proUrl = in.readString();
        seq = in.readString();
        status = in.readString();
        title = in.readString();
    }

    public static final Creator<IndexImgList> CREATOR = new Creator<IndexImgList>() {
        @Override
        public IndexImgList createFromParcel(Parcel in) {
            return new IndexImgList(in);
        }

        @Override
        public IndexImgList[] newArray(int size) {
            return new IndexImgList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(proImg);
        parcel.writeString(proUrl);
        parcel.writeString(seq);
        parcel.writeString(status);
        parcel.writeString(title);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String gproImg) {
        this.proImg = gproImg;
    }

    public String getProUrl() {
        return proUrl;
    }

    public void setProUrl(String proUrl) {
        this.proUrl = proUrl;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Creator<IndexImgList> getCREATOR() {
        return CREATOR;
    }
}
