package com.witation.ktdemo.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.witation.ktdemo.R;


/**
 * recyclerView 分隔线
 * 可以使用默认的分割线，也可以自定义分割线
 * 可以设置是否显示最后一条分割线
 */
public class DividerDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    //设置指定分割线
    private Drawable mDivider;
    //列表滚动方向
    private int mOrientation;
    //左边间距
    private int mMarginLeft;
    private int mMarginRight;
    //是否显示最后一条分割线:默认不显示最后一条
    private boolean mIsShowLastLine = false;

    /**
     * 水平滚动列表：画竖线
     */
    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;

    /**
     * 垂直滚动列表：画横线
     */
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;

    /**
     * 不设置分割线，使用默认分割线
     */
    public DividerDecoration(Context context, int orientation) {
        this(context, orientation, R.drawable.shape_rv_divider, false);
    }

    /**
     * 不设置分割线，使用默认分割线,指定左边距（单位：dp）
     */
    public DividerDecoration(Context context, int orientation, int marginLeft, int marginRight) {
        this(context, orientation, R.drawable.shape_rv_divider, false);
        this.mMarginLeft = (int) DensityUtils.dp2px(context, marginLeft);
        this.mMarginRight = (int) DensityUtils.dp2px(context, marginRight);
    }

    /**
     * 使用指定分割线
     */
    public DividerDecoration(Context context, int orientation, int drawableResId) {
        this(context, orientation, drawableResId, false);
    }

    /**
     * 指定是否显示最后一条分割线
     */
    public DividerDecoration(Context context, int orientation, boolean mIsShowLastLine) {
        this(context, orientation, R.drawable.shape_rv_divider, mIsShowLastLine);
    }

    /**
     * 使用指定分割线
     * 设置是否显示最后一条分割线
     */
    public DividerDecoration(Context context, int orientation, int drawableResId, boolean mIsShowLastLine) {
        this.mContext = context;
        this.mIsShowLastLine = mIsShowLastLine;
        mDivider = mContext.getResources().getDrawable(drawableResId);
        setOrientation(orientation);
    }

    //设置屏幕的方向
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == HORIZONTAL) {
            drawVerticalLine(c, parent, state);
        } else {
            drawHorizontalLine(c, parent, state);
        }
    }

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    private void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft() + mMarginLeft;
//        Log.i("--->recyclerline", "drawHorizontalLine left: "+left);
//        Log.i("--->recyclerline", "drawHorizontalLine mMarginLeft: "+mMarginLeft);
        int right = parent.getWidth() - parent.getPaddingRight() - mMarginRight;
        int childCount = parent.getChildCount();
        if (!mIsShowLastLine) {
            childCount--;
        }
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    //画竖线
    private void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        if (!mIsShowLastLine) {
            childCount--;
        }
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    //由于Divider也有长宽高，每一个Item需要向下或者向右偏移
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL) {
            //画横线，就是往下偏移一个分割线的高度
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            //画竖线，就是往右偏移一个分割线的宽度
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}