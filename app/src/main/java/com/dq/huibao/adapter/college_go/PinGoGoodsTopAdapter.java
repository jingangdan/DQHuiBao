package com.dq.huibao.adapter.college_go;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.jifen.JiFenFuLiType;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 同学拼go上边横向的商品列表adapter
 * Created by jingang on 2018/1/10.
 */

public class PinGoGoodsTopAdapter extends RecyclerView.Adapter<PinGoGoodsTopAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> listBean;
    private OnItemClickListener onItemClickListener;
    private int layoutId;
    public PinGoGoodsTopAdapter(Context mContext, List<String> appimgList) {
        this.mContext = mContext;
        this.listBean = appimgList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_pinggo_goods_top, viewGroup, false)
        );
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int i) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition(); // 1
                    onItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });

        }
//        holder.type.setText(listBean.get(i).getTname());
//        ImageUtils.loadIntoUseFitWidth2(mContext, HttpPath.NEW_HEADER + listBean.get(i).getThumb(), R.mipmap.icon_stub, holder.img);
    }

    @Override
    public int getItemCount() {
        return listBean.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;
        private TextView price,oldPrice;

        public MyViewHolder(View view) {
            super(view);
            img =  view.findViewById(R.id.item_pingo_goods_top_image);
            price =  view.findViewById(R.id.item_pingo_goods_top_price);
            oldPrice =  view.findViewById(R.id.item_pingo_goods_top_oldprice);
        }
    }
}
