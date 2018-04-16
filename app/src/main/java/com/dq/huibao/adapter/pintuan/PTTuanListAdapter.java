package com.dq.huibao.adapter.pintuan;

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
import com.dq.huibao.bean.PinTuanDetails;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpPath;

import java.util.List;

/**
 * 同学拼go上边横向的商品列表adapter
 * Created by jingang on 2018/1/10.
 */

public class PTTuanListAdapter extends RecyclerView.Adapter<PTTuanListAdapter.MyViewHolder> {
    private Context mContext;
    private List<PinTuanDetails.ListBean> listBean;
    private OnItemClickListener onItemClickListener;
    private int layoutId;
    public PTTuanListAdapter(Context mContext, List<PinTuanDetails.ListBean> appimgList) {
        this.mContext = mContext;
        this.listBean = appimgList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_pintuan_list, viewGroup, false)
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
        holder.name.setText(listBean.get(i).getNickname());
        holder.num.setText("剩余名额:" + listBean.get(i).getDiffnum());
        Glide.with(mContext)
                .load(HttpPath.NEW_HEADER + listBean.get(i).getHeadimgurl())
                .placeholder(R.mipmap.icon_empty)
                .into(holder.img);
//        holder.type.setText(listBean.get(i).getTname());
//        ImageUtils.loadIntoUseFitWidth2(mContext, HttpPath.NEW_HEADER + listBean.get(i).getThumb(), R.mipmap.icon_stub, holder.img);
    }

    @Override
    public int getItemCount() {
        return listBean.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;
        /*昵称，名额数*/
        private TextView name,num;

        public MyViewHolder(View view) {
            super(view);
            img =  view.findViewById(R.id.item_pt_list_image);
            name =  view.findViewById(R.id.item_pt_list_name);
            num =  view.findViewById(R.id.item_pt_list_peopleNum);
        }
    }
}
