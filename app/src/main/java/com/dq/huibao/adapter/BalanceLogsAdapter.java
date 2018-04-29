package com.dq.huibao.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.bean.jifen.JiFenLogs;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 积分兑换商品记录
 * Created by d on 2018/4/3.
 */

public class BalanceLogsAdapter extends ListBaseAdapter<JiFenLogs.DataBean> {
    public BalanceLogsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_balance_logs;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final JiFenLogs.DataBean listBean = mDataList.get(position);
        TextView name = holder.getView(R.id.item_jifen_duihuan_name);
        TextView price = holder.getView(R.id.item_jifen_duihuan_price);
        TextView youfei = holder.getView(R.id.item_jifen_duihuan_youfei);
        //
        name.setText(listBean.getGoodsname());
        price.setText("-￥" + listBean.getPrice() + "     积分-"+listBean.getScore());
//        youfei.setText("运费: ￥" + listBean.getExpprice());
    }
}
