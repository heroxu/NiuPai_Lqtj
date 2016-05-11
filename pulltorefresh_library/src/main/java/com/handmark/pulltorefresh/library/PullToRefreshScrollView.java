/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;


public class PullToRefreshScrollView extends PullToRefreshBase<ScrollView> {

    public PullToRefreshScrollView(Context context) {
        super(context);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshScrollView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshScrollView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }

    @Override
    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected ScrollView createRefreshableView(Context context, AttributeSet attrs) {
        ScrollView scrollView;
        if (VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD) {
            scrollView = new InternalScrollViewSDK9(context, attrs);
        } else {
            scrollView = new ScrollView(context, attrs);
        }

        scrollView.setId(R.id.scrollview);
        return scrollView;
    }

    @Override
    protected boolean isReadyForPullStart() {
        return mRefreshableView.getScrollY() == 0;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        View scrollViewChild = mRefreshableView.getChildAt(0);
        if (null != scrollViewChild) {
            return mRefreshableView.getScrollY() >= (scrollViewChild.getHeight() - getHeight());
        }
        return false;
    }

    @TargetApi(9)
    final class InternalScrollViewSDK9 extends ScrollView {

        public InternalScrollViewSDK9(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                                       int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

            final boolean returnValue = super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                    scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
//            Log.e("onSlideUp", deltaX + "------" + deltaY + "------" + scrollX + "------" + scrollY + "------" + scrollRangeX + "------" +
//                    scrollRangeY + "------" + maxOverScrollX + "------" + maxOverScrollY + "------" + isTouchEvent);
            if(mOnTouchEventMoveListenre!=null){
                mOnTouchEventMoveListenre.onSlide(scrollY);
            }
            // Does all of the hard work...
            OverscrollHelper.overScrollBy(PullToRefreshScrollView.this, deltaX, scrollX, deltaY, scrollY,
                    getScrollRange(), isTouchEvent);

            return returnValue;
        }

        /**
         * Taken from the AOSP ScrollView source
         */
        private int getScrollRange() {
            int scrollRange = 0;
            if (getChildCount() > 0) {
                View child = getChildAt(0);
                scrollRange = Math.max(0, child.getHeight() - (getHeight() - getPaddingBottom() - getPaddingTop()));
            }
            return scrollRange;
        }
    }

    private ScrollViewListener scrollViewListener = null;

    public interface OnTouchEventMoveListenre {

        public void onSlideUp(int mOriginalHeaderHeight, int mHeaderHeight);

        public void onSlideDwon(int mOriginalHeaderHeight, int mHeaderHeight);

        public void onSlide(int alpha);
    }

    private OnTouchEventMoveListenre mOnTouchEventMoveListenre;

    public void setOnTouchEventMoveListenre(OnTouchEventMoveListenre l) {
        mOnTouchEventMoveListenre = l;
    }

    private int mAlpha = 0;
    private static final float MAX_ALPHA = 255.00000f;

    private int mHeaderHeight;
    private int lastTranslationY;
    private int deltaTranslationY;

    private ViewGroup mHeader;
    private View mHeaderChild;
    private View mContent;
    private int range;
    private float lastY = -1;
    private float deltaY = -1;

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setVerticalScrollBarEnabled(false);
        initView();
    }

    private void initView() {
//        mHeader = (ViewGroup) findViewById(R.id.rl_top);
//        mHeaderChild = mHeader.getChildAt(0);
//        mContent = findViewById(R.id.ll_content);
        range = (int) getContext().getResources().getDimension(R.dimen.rl_top);
        mHeaderHeight = range;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
        if (y > range) {
            return;
        }
        float percent = animateScroll(y);

//        animateUpSlide(y);
        deltaTranslationY = y - lastTranslationY;
        lastTranslationY = y;

        scrollListen(percent);
    }

    private float animateScroll(int t) {
        float percent = (float) t / range;
        if (percent > 1) {
            percent = 1;
        }
//        ViewHelper.setTranslationY(mHeader, t);
        return percent;

    }

    public interface ScrollViewListener {

        void onScrollChanged(PullToRefreshScrollView scrollView, int x, int y, int oldx, int oldy);

    }

    /**
     * 监听滚动事件的相关逻辑
     */
    private void scrollListen(float percent) {
        mHeaderHeight -= deltaTranslationY;

        if (mOnTouchEventMoveListenre != null) {

            mAlpha = (int) (percent * MAX_ALPHA);

            if (deltaTranslationY < 0) {
                // 下滑
                mOnTouchEventMoveListenre.onSlideDwon(
                        range, mHeaderHeight);
            } else if (deltaTranslationY > 0) {
                // 上滑
                mOnTouchEventMoveListenre.onSlideUp(
                        range, mHeaderHeight);
            }

            if (mHeaderHeight == range) {
                mAlpha = 0;
            }
            if (mAlpha > 255) {
                mAlpha = 255;
            }
            if (mAlpha < 0) {
                mAlpha = 0;
            }

//            mOnTouchEventMoveListenre.onSlide(mAlpha);

        }
    }
}
