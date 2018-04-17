package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.pingo.PinGoCenterTuan;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 拼go首页模块内参团人员list 适配器
 * Created by jingang on 2018/1/10.
 */

public class PinGoIndexChildAdapter extends RecyclerView.Adapter<PinGoIndexChildAdapter.MyViewHolder> {
    private Context mContext;
    private List<PinGoCenterTuan.DataBean.ListBeanX.ListBean> dataList;
    String type = "";
    OnItemClickListener onItemClickListener;
    public PinGoIndexChildAdapter(Context mContext, List<PinGoCenterTuan.DataBean.ListBeanX.ListBean> list,String string) {
        this.mContext = mContext;
        this.dataList = list;
        type = string;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_pingo_index_child, viewGroup, false)
        );
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int i) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(type);
                }
            });

        }
        if (i == dataList.size()){
            Glide.with(mContext).load(R.mipmap.icon_pingo_list_peopeleadd).into(holder.img);
            return;
        }
        ImageUtils.loadIntoUseFitWidths(mContext,
                HttpPath.NEW_HEADER + dataList.get(i).getHeadimgurl(),
                R.mipmap.ic_header,
                holder.img);


    }

    @Override
    public int getItemCount() {
        //添加
        return dataList.size() + 1;
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.item_pingo_index_child_image);
        }
    }

    public interface OnItemClickListener {
        //类型.立减还是折扣
        void onItemClick(String type);
    }
}
