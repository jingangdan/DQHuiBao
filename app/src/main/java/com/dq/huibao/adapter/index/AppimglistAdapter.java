package com.dq.huibao.adapter.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 首页 图片组 适配器
 * Created by jingang on 2018/1/10.
 */

public class AppimglistAdapter extends RecyclerView.Adapter<AppimglistAdapter.MyViewHolder> {
    private Context mContext;
    private List<Index.DataBean.ChildBean> appimgList;
    private OnItemClickListener onItemClickListener;
    private int layoutId;
    public AppimglistAdapter(Context mContext, List<Index.DataBean.ChildBean> appimgList,int layout) {
        this.mContext = mContext;
        this.appimgList = appimgList;
        layoutId = layout;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false)
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
        if (layoutId == R.layout.item_hp_picture_horizontal){
            ViewGroup.LayoutParams layoutParams = holder.img.getLayoutParams();
            if (appimgList.get(i).getWidth().equals("33"))
                layoutParams.height = 550;
            layoutParams.width = 360;
            if (appimgList.get(i).getWidth().equals("25")){
                layoutParams.width = 400;
                layoutParams.height = 500;
            }

            holder.img.setLayoutParams(layoutParams);
            Glide.with(mContext)
                    .load(HttpPath.NEW_HEADER + appimgList.get(i).getThumb())
                    .placeholder(R.mipmap.icon_empty002)
                    .into(holder.img);
            return;
        }
        ImageUtils.loadIntoUseFitWidths(mContext,
                HttpPath.NEW_HEADER + appimgList.get(i).getThumb(),
                R.mipmap.icon_empty002,
                holder.img);


    }

    @Override
    public int getItemCount() {
        return appimgList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.iv_hp_picture);
        }
    }
}
