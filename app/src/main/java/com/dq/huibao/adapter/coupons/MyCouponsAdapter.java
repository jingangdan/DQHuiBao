package com.dq.huibao.adapter.coupons;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.bean.coupons.CouponsGetListB;
import com.dq.huibao.bean.coupons.MyCouponsB;
import com.dq.huibao.ui.GoodsListActivity;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.view.CouponsLayout;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 我的优惠券
 * Created by d on 2018/4/3.
 */

public class MyCouponsAdapter extends ListBaseAdapter<MyCouponsB.DataBean> {

    public MyCouponsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_coupons;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        MyCouponsB.DataBean listBean = mDataList.get(position);
//        ImageView imageView = holder.getView(R.id.item_jifen_goods_imageView);
        TextView name = holder.getView(R.id.tv_item_coupons_couponname);
        CouponsLayout couponsLayout = holder.getView(R.id.coupons_layout);
        TextView tiaojian = holder.getView(R.id.tv_item_coupons_tiaojian);
        TextView time = holder.getView(R.id.tv_item_coupons_time);
        TextView go = holder.getView(R.id.item_coupons_go);


        name.setText(listBean.getTitle());
        name.setMaxLines(1);
        name.setTextSize(16);
//        if (listBean.getBacktype().equals("0")){
//            name.setText(listBean.getDeduct());
//        }else {
//            name.setText(listBean.getDiscount() + "折");
//        }

        if (listBean.getStatus().equals("0")){//未使用
            go.setText("使用");
            couponsLayout.setmPaintColor(mContext.getResources().getColor(R.color.color_ff4a57));
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, GoodsListActivity.class);
//                    mContext.startActivity(intent);
                }
            });
        }else if (listBean.getStatus().equals("1")){//已使用
            go.setText("已使用");
            couponsLayout.setmPaintColor(mContext.getResources().getColor(R.color.dark));
        }else if (listBean.getStatus().equals("2")){//已过期
            go.setText("已过期");
            couponsLayout.setmPaintColor(mContext.getResources().getColor(R.color.dark));
        }


        time.setText(AppUtil.getDateToString("yyyy-MM-dd",Long.parseLong(listBean.getGettime() != null?listBean.getGettime():listBean.getStarttime()) * 1000)
                + "-" + AppUtil.getDateToString("yyyy-MM-dd",Long.parseLong(listBean.getEndtime()) * 1000));

//        tiaojian.setText("订单金额满" + listBean.getEnough() + "可使用");

    }
}
