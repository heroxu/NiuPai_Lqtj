package com.rxjava_niupai_lqtj.inteface;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by laucherish on 16/3/4.
 */
public abstract class AutoLoadOnScrollListener extends RecyclerView.OnScrollListener {
    private int previousTotal = 0;
    private boolean loading = false;
    int totalItemCount, lastVisibleItem;

    private int currentPage = 0;

    private LinearLayoutManager mLinearLayoutManager;

    public AutoLoadOnScrollListener(
            LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        totalItemCount = mLinearLayoutManager.getItemCount();
        lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();

//        if (loading && (totalItemCount > previousTotal)) {
//            loading = false;
//            previousTotal = totalItemCount;
//        }

        if (!loading && (lastVisibleItem > totalItemCount - 3) && dy > 0) {
            currentPage++;
            onLoadMore(currentPage);
            loading = false;
        }
    }

    public abstract void onLoadMore(int currentPage);

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
