package com.dq.huibao.adapter.jifen;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.bean.jifen.JiFenLogs;
import com.dq.huibao.bean.jifen.JiFenUserLogs;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 积分使用记录
 * Created by d on 2018/4/3.
 */

public class JiFenLogsUserAdapter extends ListBaseAdapter<JiFenUserLogs.DataBean> {
    public JiFenLogsUserAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_jifen_shiyong_jl;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final JiFenUserLogs.DataBean listBean = mDataList.get(position);
        TextView name = holder.getView(R.id.item_jifen_shiyong_name);
        TextView price = holder.getView(R.id.item_jifen_shiyong_price);
        TextView score = holder.getView(R.id.item_jifen_shiyong_score);
        TextView time = holder.getView(R.id.item_jifen_shiyong_time);
        //
        if ("score".equals(listBean.getType())){
            price.setVisibility(View.GONE);
        }else {
            score.setVisibility(View.GONE);
        }
        name.setText("类型:" + listBean.getRemark());
        price.setText("价格: ￥" + listBean.getBalance());
        score.setText("积分:" + listBean.getScore());
//        long sss = 123654789000l;
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(Long.parseLong(listBean.getAddtime()) * 1000));
        time.setText(date);
    }
}
