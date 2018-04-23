package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.bean.pingo.PinGoIndexMoreGoods;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 同学拼go 商品列表adapter
 * Created by d on 2018/4/3.
 */

public class PinGoGoodsAdapter extends ListBaseAdapter<PinGoIndexMoreGoods.DataBean.ListBean> {

    public PinGoGoodsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_pingo_goods_list;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        PinGoIndexMoreGoods.DataBean.ListBean listBean = mDataList.get(position);
        ImageView imageView = holder.getView(R.id.item_pingo_goods_list_image);
        TextView name = holder.getView(R.id.item_pingo_goods_list_name);
        TextView price = holder.getView(R.id.item_pingo_goods_list_price);
        TextView oldprice = holder.getView(R.id.item_pingo_goods_list_old_price);
        TextView youhui = holder.getView(R.id.item_pingo_goods_list_youhui);
        TextView number = holder.getView(R.id.item_pingo_goods_list_number);
        TextView baifenbi = holder.getView(R.id.item_pingo_goods_list_progress_text);//百分比
        ProgressBar progressBar = holder.getView(R.id.item_pingo_goods_list_progress);
        //马上抢
        TextView qianggou = holder.getView(R.id.item_pingo_goods_list_msq);
//        Glide.with(mContext)
//                .load(HttpPath.NEW_HEADER + listBean.getThumb())
//                .placeholder(R.mipmap.icon_stub)
//                .into(imageView);

        ImageUtils.loadIntoUseFitWidth2(mContext, listBean.getThumb(), R.mipmap.icon_empty002, imageView);

        name.setText(listBean.getGoodsname());
        price.setText("￥" + listBean.getMarketprice());
//        oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        oldprice.setText("￥" + listBean.getMarketprice());
        youhui.setVisibility(View.GONE);

        number.setText(listBean.getSalecount() + "已抢");
        progressBar.setMax(Integer.parseInt(listBean.getStock()));
        progressBar.setProgress(Integer.parseInt(listBean.getSalecount()));
        baifenbi.setText(Integer.parseInt(listBean.getSalecount()) * 100 / Integer.parseInt(listBean.getStock())+"%");
        qianggou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
