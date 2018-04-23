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
import com.dq.huibao.bean.pintuan.PinTuanDetails;
import com.dq.huibao.bean.pintuan.PinTuanTuanDetail;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 团员列表---团详情页
 * Created by jingang on 2018/1/10.
 */

public class PTTuanTuanChildListAdapter extends RecyclerView.Adapter<PTTuanTuanChildListAdapter.MyViewHolder> {
    private Context mContext;
    private List<PinTuanTuanDetail.DataBean.MemberBean> listBean;
    private OnItemClickListener onItemClickListener;
    private int layoutId;
    public PTTuanTuanChildListAdapter(Context mContext, List<PinTuanTuanDetail.DataBean.MemberBean> appimgList) {
        this.mContext = mContext;
        this.listBean = appimgList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_pintuan_tuan_list, viewGroup, false)
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
        Glide.with(mContext)
                .load(ImageUtils.getImagePath(listBean.get(i).getHeadimgurl()))
                .placeholder(R.mipmap.ic_header)
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
        private TextView name;

        public MyViewHolder(View view) {
            super(view);
            img =  view.findViewById(R.id.item_pt_tuan_list_image);
            name =  view.findViewById(R.id.item_pt_tuan_list_name);
        }
    }
}
