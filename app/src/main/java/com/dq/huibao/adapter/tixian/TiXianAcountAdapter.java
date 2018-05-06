package com.dq.huibao.adapter.tixian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.bean.tixian.TiXianIndexB;
import com.dq.huibao.bean.xstore.XstoreGoodsType;

import java.util.List;

/**
 * 提现账号选择
 * Created by d on 2018/4/4.
 */

public class TiXianAcountAdapter extends BaseAdapter {
    Context context;
    List<TiXianIndexB.DataBean.ListBean> listBeans;

    public TiXianAcountAdapter(Context context, List<TiXianIndexB.DataBean.ListBean> typeList) {
        this.context = context;
        this.listBeans = typeList;
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tixian_select_account,null);
            viewHolder = new ViewHolder();
            viewHolder.account = convertView.findViewById(R.id.item_tixian_select_account_account);
            viewHolder.type = convertView.findViewById(R.id.item_tixian_select_account_type);
            viewHolder.des = convertView.findViewById(R.id.item_tixian_select_account_des);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.account.setText(listBeans.get(position).getAccount());
        viewHolder.type.setText(listBeans.get(position).getType().equals("1")?"银行卡":"支付宝");
        viewHolder.des.setText(listBeans.get(position).getDesc());
        return convertView;
    }
    class ViewHolder{
        TextView account,type,des;
    }
}
