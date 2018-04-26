package com.dq.huibao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dq.huibao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 充值金额
 * Created by d on 2018/4/26.
 */

public class BillAdapter extends BaseAdapter{
    List<Integer> list = new ArrayList<>();
    Context context;

    public BillAdapter( Context context,List<Integer> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_recharge_hf_bill,null);
        TextView textView = convertView.findViewById(R.id.item_recharge_hf_tv);
        textView.setText(list.get(position) + "元");
        return convertView;
    }
}
