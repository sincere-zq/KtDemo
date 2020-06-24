package com.witation.ktdemo.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.witation.ktdemo.R;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * 为TabLayout设置动画
 */

public class TabLayoutAnimUtils {

    /**
     * 设置每个TabLayout的自定义View
     * 注意：TabLayout和Viewpager配合使用的时候必须先mViewPager.setAdapter（），再初始化该方法，然后addOnTabSelectedListener；因为adapter刷新会让mCustomViewView空，
     */
    public static void setCustomViews(Context context, TabLayout tabLayout, List<String> titleList) {
        int mSelectedTabPosition = tabLayout.getSelectedTabPosition();
        for (int i = 0; i < titleList.size(); i++) {
            TabLayout.Tab mTab = Objects.requireNonNull(tabLayout.getTabAt(i)).setCustomView(getTabView(context, titleList, i, mSelectedTabPosition));
            if (i == mSelectedTabPosition) {
                changeTabSelect(mTab);
            } else {
                changeTabNormal(mTab);
            }
        }
    }

    /**
     * 提供TabLayout的View
     * 根据index返回不同的View
     * 主意：默认选中的View要返回选中状态的样式
     */
    private static View getTabView(Context context, List<String> titleList, int index, int mSelectedTabPosition) {
        //自定义View布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_place, null);
        TextView title = view.findViewById(R.id.title);
        title.setText(titleList.get(index));
        title.setSelected(index == mSelectedTabPosition);
        return view;
    }

    /**
     * 改变TabLayout的View到选中状态
     * 使用属性动画改编Tab中View的状态
     */
    public static void changeTabSelect(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        if (view == null) {
            view = tab.view;
        }
        ObjectAnimator anim = ObjectAnimator
                .ofFloat(view, "", 1.0F, 1.3F)
                .setDuration(200);
        anim.start();
        View finalView = view;
        anim.addUpdateListener(animation -> {
            float cVal = (Float) animation.getAnimatedValue();
            finalView.setAlpha(0.5f + (cVal - 1f) * (0.5f / 0.1f));
            finalView.setScaleX(cVal);
            finalView.setScaleY(cVal);
        });
    }

    /**
     * 改变TabLayout的View到未选中状态
     */
    public static void changeTabNormal(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        if (view == null) {
            view = tab.view;
        }
        ObjectAnimator anim = ObjectAnimator
                .ofFloat(view, "", 1.3F, 1.0F)
                .setDuration(200);
        anim.start();
        View finalView = view;
        anim.addUpdateListener(animation -> {
            float cVal = (Float) animation.getAnimatedValue();
            finalView.setAlpha(1f - (1f - cVal) * (0.5f / 0.1f));
            finalView.setScaleX(cVal);
            finalView.setScaleY(cVal);
        });
    }

    /**
     * 改变tablayout指示器的宽度
     *
     * @param tabLayout
     * @param margin
     */
    public static void changeTabIndicatorWidth(final TabLayout tabLayout, final int margin) {
        tabLayout.post(() -> {
            try {
                Field mTabStripField = tabLayout.getClass().getDeclaredField("mTabStrip");
                mTabStripField.setAccessible(true);

                LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(tabLayout);

                int dp10 = margin == 0 ? 50 : margin;

                assert mTabStrip != null;
                for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                    View tabView = mTabStrip.getChildAt(i);

                    Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                    mTextViewField.setAccessible(true);

                    TextView mTextView = (TextView) mTextViewField.get(tabView);

                    tabView.setPadding(0, 0, 0, 0);

                    int width = 0;
                    assert mTextView != null;
                    width = mTextView.getWidth();
                    if (width == 0) {
                        mTextView.measure(0, 0);
                        width = mTextView.getMeasuredWidth();
                    }

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                    params.width = width;
                    params.leftMargin = dp10;
                    params.rightMargin = dp10;
                    tabView.setLayoutParams(params);

                    tabView.invalidate();
                }

            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
    }

    public static void changeTabIndicatorWidth(TabLayout tabLayout) {
        changeTabIndicatorWidth(tabLayout, 0);
    }

    /**
     * 设置tablayout指示器的宽度
     *
     * @param tabs
     * @param leftDip
     * @param rightDip
     */
    public static void setIndicatorWidth(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        assert tabStrip != null;
        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        assert llTab != null;
        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}


