package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.pingo.PinGoCartList;
import com.dq.huibao.bean.pingo.PinGoIndex;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * 拼go购物车列表
 * Created by jingang on 2018/1/10.
 */

public class PinGoCartAdapter extends RecyclerView.Adapter<PinGoCartAdapter.MyViewHolder> {
    private Context mContext;
    private List<PinGoCartList.DataBean.ListBean> appimgList;
    private OnItemClickListener onItemClickListener;
    public PinGoCartAdapter(Context mContext, List<PinGoCartList.DataBean.ListBean> appimgList) {
        this.mContext = mContext;
        this.appimgList = appimgList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_pingo_cart, viewGroup, false)
        );
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int i) {


        holder.name.setText("id=" + appimgList.get(i).getId()+""+
                "数量=" + appimgList.get(i).getCount()+"");
        holder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null){
                    onItemClickListener.jian(i);
                }
            }
        });
        holder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null){
                    onItemClickListener.jia(i);
                }
            }
        });
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null){
                    onItemClickListener.del(i);
                }
            }
        });
//        ImageUtils.loadIntoUseFitWidths(mContext,
//                HttpPath.NEW_HEADER + appimgList.get(i).getThumb(),
//                R.mipmap.icon_empty002,
//                holder.img);


    }

    @Override
    public int getItemCount() {
        return appimgList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;
        TextView name;
        Button jian,jia,del;
        public MyViewHolder(View view) {
            super(view);
//            img =  view.findViewById(R.id.item_pingo_cart_jian);
            name =  view.findViewById(R.id.item_pingo_cart_name);
            jian =  view.findViewById(R.id.item_pingo_cart_jian);
            jia =  view.findViewById(R.id.item_pingo_cart_jia);
            del =  view.findViewById(R.id.item_pingo_cart_del);
        }
    }

    public interface OnItemClickListener{
        void jian(int postion);
        void jia(int postion);
        void del(int postion);
    }
}
