package com.dq.huibao.adapter.coupons;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.coupons.CouponsB;
import com.dq.huibao.bean.pingo.PinGoiQuSelect;
import com.dq.huibao.utils.BaseRecyclerViewHolder;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 优惠券选择adapter
 * Created by jingang on 2018/1/10.
 */

public class CouponSelectAdapter extends RecyclerView.Adapter<CouponSelectAdapter.MyViewHolder> {
    private Context mContext;
    private List<CouponsB.DataBean> listBeans;
    private OnItemClickListener onItemClickListener;
    public CouponSelectAdapter(Context mContext, List<CouponsB.DataBean> listBeans) {
        this.mContext = mContext;
        this.listBeans = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder vh = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_coupons_select, viewGroup, false)
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
        holder.name.setText("￥" + listBeans.get(i).getYouhui());
        holder.title.setText(listBeans.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }
    public class MyViewHolder extends BaseRecyclerViewHolder {
        TextView name,title;
        public MyViewHolder(View view) {
            super(view);
            name =  view.findViewById(R.id.item_coupon_select);
            title =  view.findViewById(R.id.item_coupon_select_title);
        }
    }
}
