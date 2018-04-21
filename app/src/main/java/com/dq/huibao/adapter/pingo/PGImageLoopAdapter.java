package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dq.huibao.R;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.bean.pingo.PinGoIndex;
import com.dq.huibao.rollpagerview.LoopPagerAdapter;
import com.dq.huibao.rollpagerview.RollPagerView;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * Created by asus on 2018/2/2.
 */

public class PGImageLoopAdapter extends LoopPagerAdapter {
    private Context mContext;
    private List<PinGoIndex.DataBean.ChildBean> bannerBeans;

    public PGImageLoopAdapter(RollPagerView viewPager, Context mContext, List<PinGoIndex.DataBean.ChildBean> bannerBeans) {
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
