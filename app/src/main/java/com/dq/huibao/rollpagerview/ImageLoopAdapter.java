package com.dq.huibao.rollpagerview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dq.huibao.R;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * Created by asus on 2018/2/2.
 */

public class ImageLoopAdapter extends LoopPagerAdapter {
    private Context mContext;
    private List<Index.DataBean.ChildBean> bannerBeans;

    public ImageLoopAdapter(RollPagerView viewPager, Context mContext, List<Index.DataBean.ChildBean> bannerBeans) {
        super(viewPager);
        this.mContext = mContext;
        this.bannerBeans = bannerBeans;
    }

    @Override
    public View getView(ViewGroup container, int position) {

        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ImageUtils.loadIntoUseFitWidths(mContext,bannerBeans.get(position).getThumb(),
                R.mipmap.icon_empty002,
                view);

        return view;
    }

    @Override
    public int getRealCount() {
        return bannerBeans.size();
    }
}
