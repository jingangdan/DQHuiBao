package com.dq.huibao.adapter.pintuan;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.bean.pintuan.PinTuanIndex;
import com.dq.huibao.ui.pintuan.PinTuanDetailsActivity;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 拼团首页adapter
 * Created by d on 2018/4/3.
 */

public class PinTuanIndexAdapter extends ListBaseAdapter<PinTuanIndex.DataBean.ListBean> {

    public PinTuanIndexAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_list_pintuan;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final PinTuanIndex.DataBean.ListBean listBean = mDataList.get(position);
        ImageView imageView = holder.getView(R.id.item_pintuan_img);
        TextView ptName = holder.getView(R.id.item_pintuan_ptName);
        TextView goodName = holder.getView(R.id.item_pintuan_goodName);
        TextView count = holder.getView(R.id.item_pintuan_count);
        TextView type = holder.getView(R.id.item_pintuan_type);
        TextView go = holder.getView(R.id.item_pintuan_go);

        ImageUtils.loadIntoUseFitWidth2(mContext, listBean.getThumb(), R.mipmap.icon_empty001, imageView);

        ptName.setText(listBean.getTuanname());
        goodName.setText(listBean.getGoodsname());
        count.setText(listBean.getMincount() + "人团");
        type.setText(listBean.getSaletype().equals("0")?"￥" +listBean.getTuanprice():listBean.getDiscount() + "折");
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,PinTuanDetailsActivity.class);
                intent.putExtra("gid",listBean.getGoodsid());
                intent.putExtra("tuanId",listBean.getId());
                mContext.startActivity(intent);
            }
        });
    }
}
