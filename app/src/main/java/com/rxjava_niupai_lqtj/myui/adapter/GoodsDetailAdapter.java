package com.rxjava_niupai_lqtj.myui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rxjava_niupai_lqtj.R;
import com.rxjava_niupai_lqtj.base.BaseRecyclerAdapter;

/**
 * Created by xuxiarong on 2016/5/9.
 */
public class GoodsDetailAdapter extends BaseRecyclerAdapter<String> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_buy_frist, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, String data) {
        if(viewHolder instanceof MyHolder) {
         //   ((MyHolder) viewHolder).text.setText(data);
        }
    }

    class MyHolder extends Holder {
      //  TextView text;
        public MyHolder(View itemView) {
            super(itemView);
         //   text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
