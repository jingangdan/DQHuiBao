package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.Interface.OnItemLongClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.pingo.PinGoOrderConfirm;
import com.dq.huibao.utils.BaseRecyclerViewHolder;

import java.util.List;

/**
 * 拼go--确认订单
 * Description：
 * Created by jingang on 2017/10/30.
 */
public class PinGoSubmitOrderAdapter extends RecyclerView.Adapter<PinGoSubmitOrderAdapter.MyViewHolder> {
    private Context mContext;
    private List<PinGoOrderConfirm.DataBean> shopList;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    private PinGoSoGoodsAdapter soGoodsAdapter;

    public PinGoSubmitOrderAdapter(Context mContext, List<PinGoOrderConfirm.DataBean> shopList) {
        this.mContext = mContext;
        this.shopList = shopList;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_submitorder, parent, false));

        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView, position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }
        shopList.get(position).setCommet("");
        holder.shopname.setText("惠宝商城");
        holder.num.setText("" + shopList.get(position).getAllcount());
        holder.money.setText("" + shopList.get(position).getAllprice());
//        holder.dispatch.setText("" + shopList.get(position).getDispatch_all());//运费没有
//        holder.discount_all.setText("" + shopList.get(position).getDiscount_all());//会员优惠也没有

        holder.comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                shopList.get(position).setCommet("" + editable);
            }
        });

        holder.pay_all.setText("" + (shopList.get(position).getAllprice()));

        soGoodsAdapter = new PinGoSoGoodsAdapter(mContext, shopList.get(position).getList());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recyclerView.setAdapter(soGoodsAdapter);

    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private TextView shopname, num, money, dispatch, money_all, discount_all, pay_all;
        private EditText comment;
        RecyclerView recyclerView;

        public MyViewHolder(View view) {
            super(view);
            shopname = view.findViewById(R.id.tv_checkorder_shopname);
            num = view.findViewById(R.id.tv_checkorder_num);
            money = view.findViewById(R.id.tv_checkorder_money);
            dispatch = view.findViewById(R.id.tv_checkorder_dispatch);
            money_all = view.findViewById(R.id.tv_checkorder_money_all);
            discount_all = view.findViewById(R.id.tv_item_co_discount_all);
            pay_all = view.findViewById(R.id.tv_item_co_pay_all);

            comment = view.findViewById(R.id.et_checkorder_comment);

            recyclerView = (RecyclerView) view.findViewById(R.id.rv_so_goods);
        }
    }

}
