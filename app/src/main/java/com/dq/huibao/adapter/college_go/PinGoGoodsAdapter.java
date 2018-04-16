package com.dq.huibao.adapter.college_go;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 同学拼go 商品列表adapter
 * Created by d on 2018/4/3.
 */

public class PinGoGoodsAdapter extends ListBaseAdapter<String> {

    public PinGoGoodsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_pingo_goods_list;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
//        JiFenFuLiGoods.DataBean listBean = mDataList.get(position);
        ImageView imageView = holder.getView(R.id.item_pingo_goods_list_image);
        TextView name = holder.getView(R.id.item_pingo_goods_list_name);
        TextView price = holder.getView(R.id.item_pingo_goods_list_price);
        TextView oldprice = holder.getView(R.id.item_pingo_goods_list_old_price);
        TextView youhui = holder.getView(R.id.item_pingo_goods_list_youhui);
        TextView number = holder.getView(R.id.item_pingo_goods_list_number);
        ProgressBar progressBar = holder.getView(R.id.item_pingo_goods_list_progress);
        //马上抢
        TextView qianggou = holder.getView(R.id.item_pingo_goods_list_msq);
//        Glide.with(mContext)
//                .load(HttpPath.NEW_HEADER + listBean.getThumb())
//                .placeholder(R.mipmap.icon_stub)
//                .into(imageView);

//        ImageUtils.loadIntoUseFitWidth2(mContext, HttpPath.NEW_HEADER + listBean.getThumb(), R.mipmap.icon_stub, imageView);
//
//        name.setText(listBean.getGoodsname());
//        price.setText("￥" + listBean.getPrice() +"  +  " + listBean.getScore() + "积分");
//
//        qianggou.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
