package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.bean.pingo.PinGoIndex;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 拼go首页 图片组 适配器
 * Created by jingang on 2018/1/10.
 */

public class PinGoimglistAdapter extends RecyclerView.Adapter<PinGoimglistAdapter.MyViewHolder> {
    private Context mContext;
    private List<PinGoIndex.DataBean.ChildBean> appimgList;
    private OnItemClickListener onItemClickListener;
    int layoutId;
    public PinGoimglistAdapter(Context mContext, List<PinGoIndex.DataBean.ChildBean> appimgList,int layout) {
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
        if (appimgList.get(i).getType().equals("other") && !"#".equals(appimgList.get(i).getContent())){
            holder.name.setText(appimgList.get(i).getContent());
        }
        ImageUtils.loadIntoUseFitWidths(mContext,
                appimgList.get(i).getThumb(),
                R.mipmap.icon_empty002,
                holder.img);


    }

    @Override
    public int getItemCount() {
        return appimgList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;
        TextView name;
        public MyViewHolder(View view) {
            super(view);
            img =  view.findViewById(R.id.item_pingo_image_text_img);
            name =  view.findViewById(R.id.item_pingo_image_text_text);
        }
    }
}
