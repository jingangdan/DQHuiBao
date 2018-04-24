package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.bean.pingo.PinGoIndex;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 首页 商品列表适配器
 * Created by jingang on 2018/1/10.
 */

public class PGGoodsListAdapter extends RecyclerView.Adapter<PGGoodsListAdapter.MyViewHolder>{
    private Context mContext;
    private List<PinGoIndex.DataBean.ChildBean> goodsList;
    private OnItemClickListener onItemClickListener;

    public PGGoodsListAdapter(Context mContext, List<PinGoIndex.DataBean.ChildBean> goodsList) {
        this.mContext = mContext;
        this.goodsList = goodsList;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_pingo_index_goods_heng, viewGroup, false)
        );
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition(); // 1
                    onItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });

        }
        ViewGroup.LayoutParams layoutParams = holder.img.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = AppUtil.getWidth()/3;

        holder.img.setLayoutParams(layoutParams);

        Glide.with(mContext).load(ImageUtils.getImagePath(goodsList.get(position).getThumb()))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.mipmap.icon_empty002)
                .into(holder.img);

        holder.tv_name.setText("" + goodsList.get(position).getGoodsname());

        holder.tv_pricenow.setText("¥" + goodsList.get(position).getMarketprice());
//        holder.tv_priceold.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        holder.tv_priceold.setText("¥" + goodsList.get(position).getProductprice());
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder{
        private ImageView img;
        private TextView tv_name, tv_pricenow, tv_priceold;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.item_pingo_index_goods_heng_image);
            tv_name = (TextView) view.findViewById(R.id.item_pingo_index_goods_heng_name);
            tv_pricenow = (TextView) view.findViewById(R.id.item_pingo_index_goods_heng_price);
        }
    }
}
