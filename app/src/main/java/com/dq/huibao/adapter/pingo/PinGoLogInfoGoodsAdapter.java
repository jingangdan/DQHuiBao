package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.bean.pingo.PinGoLogInfoB;

import java.util.ArrayList;
import java.util.List;

/**
 * 拼go 记录详情页商品列表
 * Created by d on 2018/5/2.
 */

public class PinGoLogInfoGoodsAdapter extends BaseAdapter{
    List<PinGoLogInfoB.DataBean.GoodslistBean> list = new ArrayList<>();
    Context context;

    public PinGoLogInfoGoodsAdapter(Context context,List<PinGoLogInfoB.DataBean.GoodslistBean> list) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_pingo_loginfo_goods,null);
        ImageView imageView = convertView.findViewById(R.id.item_pingo_loginfo_image);
        TextView name = convertView.findViewById(R.id.item_pingo_loginfo_goodname);
        TextView price = convertView.findViewById(R.id.item_pingo_loginfo_goodprice);
        TextView option_num = convertView.findViewById(R.id.item_pingo_loginfo_option_num);

        name.setText(list.get(position).getGoodsname());
        price.setText("￥" + list.get(position).getPrice());
        option_num.setText("规格:" + list.get(position).getOptionname() + "    数量:" + list.get(position).getBuycount());
        return convertView;
    }
}
