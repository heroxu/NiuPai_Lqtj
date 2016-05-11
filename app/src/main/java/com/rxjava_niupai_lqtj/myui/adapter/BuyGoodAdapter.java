package com.rxjava_niupai_lqtj.myui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rxjava_niupai_lqtj.R;
import com.rxjava_niupai_lqtj.base.BaseRecyclerAdapter;
import com.rxjava_niupai_lqtj.bean.GoodsDetail;

import java.util.List;

/**
 * Created by qibin on 2015/11/7.
 */
public class BuyGoodAdapter extends BaseRecyclerAdapter<List<GoodsDetail>> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_buy_normal, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, List<GoodsDetail> data) {
        if(viewHolder instanceof MyHolder) {
         //   ((MyHolder) viewHolder).text.setText(data);
        }
    }

    class MyHolder extends Holder {
      //  TextView text;
        public MyHolder(View itemView) {
            super(itemView);
            SparseArray mSparseArray = new SparseArray();
         //   text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
