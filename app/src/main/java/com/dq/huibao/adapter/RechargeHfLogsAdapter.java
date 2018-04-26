package com.dq.huibao.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.bean.RechargeLogsB;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 话费充值记录
 * Created by d on 2018/4/3.
 */

public class RechargeHfLogsAdapter extends ListBaseAdapter<RechargeLogsB.DataBean> {
    public RechargeHfLogsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_recharge_hf;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final RechargeLogsB.DataBean listBean = mDataList.get(position);
        TextView phone = holder.getView(R.id.item_recharge_hf_phone);
        TextView price = holder.getView(R.id.item_recharge_hf_price);
        TextView status = holder.getView(R.id.item_recharge_hf_status);
        TextView time = holder.getView(R.id.item_recharge_hf_time);
        //
        phone.setText(listBean.getPay_mobile());
        price.setText("￥" + listBean.getPay_money());
        status.setText(listBean.getStatus() + "    "+ listBean.getPaytype());
        time.setText(AppUtil.getDateToString("yyyy-MM-dd HH:mm:ss",Long.parseLong(listBean.getCtime() != null?listBean.getCtime():"0") * 1000)
                + "    " + AppUtil.getDateToString("yyyy-MM-dd HH:mm:ss",Long.parseLong(listBean.getPaytime() != null?listBean.getPaytime():"0") * 1000));
    }
}
