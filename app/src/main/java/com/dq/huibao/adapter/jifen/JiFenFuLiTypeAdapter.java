package com.dq.huibao.adapter.jifen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.bean.jifen.JiFenFuLiType;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 积分福利--可兑换商品类型adapter
 * Created by jingang on 2018/1/10.
 */

public class JiFenFuLiTypeAdapter extends RecyclerView.Adapter<JiFenFuLiTypeAdapter.MyViewHolder> {
    private Context mContext;
    private List<JiFenFuLiType.DataBean> listBean;
    private OnItemClickListener onItemClickListener;
    private int layoutId;
    public JiFenFuLiTypeAdapter(Context mContext, List<JiFenFuLiType.DataBean> appimgList) {
        this.mContext = mContext;
        this.listBean = appimgList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_jifen_fuli_type, viewGroup, false)
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
        holder.type.setText(listBean.get(i).getTname());
        ImageUtils.loadIntoUseFitWidth2(mContext, listBean.get(i).getThumb(), R.mipmap.icon_stub, holder.img);
    }

    @Override
    public int getItemCount() {
        return listBean.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;
        private TextView type;

        public MyViewHolder(View view) {
            super(view);
            img =  view.findViewById(R.id.item_jifen_fuli_type_image);
            type =  view.findViewById(R.id.item_jifen_fuli_type_name);
        }
    }
}
