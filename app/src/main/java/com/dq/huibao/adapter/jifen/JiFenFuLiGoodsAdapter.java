package com.dq.huibao.adapter.jifen;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.bean.jifen.JiFenFuLiGoods;
import com.dq.huibao.bean.xstore.XStoreGoods;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 积分福利--可兑换商品adapter
 * Created by d on 2018/4/3.
 */

public class JiFenFuLiGoodsAdapter extends ListBaseAdapter<JiFenFuLiGoods.DataBean> {

    public JiFenFuLiGoodsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_jifen_goods;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        JiFenFuLiGoods.DataBean listBean = mDataList.get(position);
        ImageView imageView = holder.getView(R.id.item_jifen_goods_imageView);
        TextView name = holder.getView(R.id.item_jifen_goods_name);
        TextView price = holder.getView(R.id.item_jifen_goods_price);
        TextView qianggou = holder.getView(R.id.item_jifen_goods_qg);
//        Glide.with(mContext)
//                .load(HttpPath.NEW_HEADER + listBean.getThumb())
//                .placeholder(R.mipmap.icon_stub)
//                .into(imageView);

        ImageUtils.loadIntoUseFitWidth2(mContext, HttpPath.NEW_HEADER + listBean.getThumb(), R.mipmap.icon_stub, imageView);

        name.setText(listBean.getGoodsname());
        price.setText("￥" + listBean.getPrice() +"  +  " + listBean.getScore() + "积分");

        qianggou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
