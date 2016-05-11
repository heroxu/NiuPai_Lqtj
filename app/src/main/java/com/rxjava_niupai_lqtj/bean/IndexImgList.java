package com.rxjava_niupai_lqtj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2016/5/11.
 */
public class IndexImgList implements Parcelable{

    private String Stringid;// 幻灯片id
    private String StringproImg;// 幻灯片图片地址
    private String StringproUrl;// 幻灯片点击跳转链接
    private String Stringseq;// 显示顺序
    private String Stringstatus;// 状态0-启用，1-禁用
    private String Stringtitle;// 幻灯片标题

    public IndexImgList(){

    }

    protected IndexImgList(Parcel in) {
        Stringid = in.readString();
        StringproImg = in.readString();
        StringproUrl = in.readString();
        Stringseq = in.readString();
        Stringstatus = in.readString();
        Stringtitle = in.readString();
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
        parcel.writeString(Stringid);
        parcel.writeString(StringproImg);
        parcel.writeString(StringproUrl);
        parcel.writeString(Stringseq);
        parcel.writeString(Stringstatus);
        parcel.writeString(Stringtitle);
    }
}
