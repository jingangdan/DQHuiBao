package com.dq.huibao.adapter.jifen;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.bean.jifen.JiFenLogs;
import com.dq.huibao.bean.xstore.XStoreZXGoods;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 积分兑换商品记录
 * Created by d on 2018/4/3.
 */

public class JiFenLogsAdapter extends ListBaseAdapter<JiFenLogs.DataBean> {
    public JiFenLogsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_jifen_duihuan_jl;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final JiFenLogs.DataBean listBean = mDataList.get(position);
        ImageView imageView = holder.getView(R.id.item_jifen_duihuan_image);
        TextView name = holder.getView(R.id.item_jifen_duihuan_name);
        TextView price = holder.getView(R.id.item_jifen_duihuan_price);
        TextView youfei = holder.getView(R.id.item_jifen_duihuan_youfei);
        //
        Glide.with(mContext)
                .load(ImageUtils.getImagePath(listBean.getThumb()))
                .placeholder(R.mipmap.icon_stub)
                .into(imageView);
        name.setText(listBean.getGoodsname());
        price.setText("-￥" + listBean.getPrice() + "     积分-"+listBean.getScore());
//        youfei.setText("运费: ￥" + listBean.getExpprice());
    }
}
