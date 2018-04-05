package com.dq.huibao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 解决嵌套滑动的高度问题
 * Created by d on 2018/4/3.
 */

public class XStroeGridView extends GridView{
    public XStroeGridView(Context context) {
        super(context);
    }

    public XStroeGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XStroeGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
