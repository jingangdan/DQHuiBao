package com.dq.huibao.adapter.pingo;

import android.content.Context;
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
import com.dq.huibao.bean.pingo.PinGoLogsB;
import com.dq.huibao.bean.pintuan.PinTuanDetails;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 拼go-记录-用户列表
 * Created by jingang on 2018/1/10.
 */

public class PinGoLogUsersAdapter extends RecyclerView.Adapter<PinGoLogUsersAdapter.MyViewHolder> {
    private Context mContext;
    private List<PinGoLogsB.DataBean.UserlistBean> listBean;
    private OnItemClickListener onItemClickListener;
    private int layoutId;
    public PinGoLogUsersAdapter(Context mContext, List<PinGoLogsB.DataBean.UserlistBean> appimgList) {
        this.mContext = mContext;
        this.listBean = appimgList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_pingo_log_users, viewGroup, false)
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
        Glide.with(mContext)
                .load(ImageUtils.getImagePath(listBean.get(i).getHeadimgurl()))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.mipmap.ic_header)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return listBean.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;

        public MyViewHolder(View view) {
            super(view);
            img =  view.findViewById(R.id.item_pingo__log_userimage);
        }
    }
}
