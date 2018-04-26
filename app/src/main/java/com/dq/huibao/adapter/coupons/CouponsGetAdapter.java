package com.dq.huibao.adapter.coupons;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.bean.coupons.CouponsGetListB;
import com.dq.huibao.ui.GoodsListActivity;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.view.CouponsLayout;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 领卷中心
 * Created by d on 2018/4/3.
 */

public abstract class CouponsGetAdapter extends ListBaseAdapter<CouponsGetListB.DataBean> {

    public CouponsGetAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_coupons;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final CouponsGetListB.DataBean listBean = mDataList.get(position);
//        ImageView imageView = holder.getView(R.id.item_jifen_goods_imageView);
        TextView name = holder.getView(R.id.tv_item_coupons_couponname);
        CouponsLayout couponsLayout = holder.getView(R.id.coupons_layout);
        TextView tiaojian = holder.getView(R.id.tv_item_coupons_tiaojian);
        TextView time = holder.getView(R.id.tv_item_coupons_time);
        TextView go = holder.getView(R.id.item_coupons_go);


        couponsLayout.setmPaintColor(mContext.getResources().getColor(R.color.green_normal));

        if (listBean.getBacktype().equals("0")){
            name.setText(listBean.getDeduct());
        }else {
            name.setText(listBean.getDiscount() + "折");
        }

        tiaojian.setText("订单金额满" + listBean.getEnough() + "可使用");
        if (listBean.getTimetype().equals("0")){//天数
            time.setText("有效期" + AppUtil.getDateToString("yyyy-MM-dd",Long.parseLong(listBean.getTimestart()) * 1000)
                        + "-" + AppUtil.getDateToString("yyyy-MM-dd",Long.parseLong(listBean.getTimeend()) * 1000));
        }else {//范围

            time.setText("有效期" + listBean.getEnough());
        }

        if (listBean.getStatus().equals("0")){
            go.setText("领券");
        }else {
            couponsLayout.setmPaintColor(mContext.getResources().getColor(R.color.dark));
            go.setText("已领");
        }

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCoupon(listBean.getId());
            }
        });
    }
    public abstract void getCoupon(String couponid);
}
