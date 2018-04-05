package com.dq.huibao.adapter.xstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.bean.xstore.XstoreGoodsType;

import java.util.List;

/**
 * 小店商品分类
 * Created by d on 2018/4/4.
 */

public class XStoreTypeAdapter extends BaseAdapter {
    Context context;
    List<XstoreGoodsType.DataBean> typeList;

    public XStoreTypeAdapter(Context context, List<XstoreGoodsType.DataBean> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    @Override
    public int getCount() {
        return typeList.size();
    }

    @Override
    public Object getItem(int position) {
        return typeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_xstore_type,null);
        TextView textView = convertView.findViewById(R.id.item_xstore_type_leftTv);
        textView.setText(typeList.get(position).getCatename());
        return convertView;
    }
    class ViewHolder{
        TextView textView;
    }
}
