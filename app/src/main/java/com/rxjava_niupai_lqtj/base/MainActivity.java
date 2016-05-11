package com.rxjava_niupai_lqtj.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.rxjava_niupai_lqtj.R;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private View mHeadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
//        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager = new GridLayoutManager(this, 2);
//        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new GridItemDecoration(this, true));

        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addDatas(generateData());
        setHeader(mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(int position, String data) {
                Toast.makeText(MainActivity.this, position + "," + data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setHeader(RecyclerView view) {
//        View header = LayoutInflater.from(this).inflate(R.layout.item_main_washgod_frist, view, false);
//        mHeadView = header;
//        ButterKnife.bind(this, mHeadView);
//        mKanner = (Kanner) mHeadView.findViewById(R.id.main_kanner);
//        String [] Images = new String[] {
//                "http://img04.muzhiwan.com/2015/06/16/upload_557fd293326f5.jpg",
//                "http://img03.muzhiwan.com/2015/06/05/upload_557165f4850cf.png",
//                "http://img02.muzhiwan.com/2015/06/11/upload_557903dc0f165.jpg",
//                "http://img04.muzhiwan.com/2015/06/05/upload_5571659957d90.png",
//                "http://img03.muzhiwan.com/2015/06/16/upload_557fd2a8da7a3.jpg" };
//        mKanner.setImagesUrl(Images);
//        mAdapter.setHeaderView(header);
    }

    private ArrayList<String> generateData() {
        ArrayList<String> data = new ArrayList<String>() {
            {
                for(int i=0;i<=21;i++) add("数据" + i);
            }
        };
        return data;
    }
}
