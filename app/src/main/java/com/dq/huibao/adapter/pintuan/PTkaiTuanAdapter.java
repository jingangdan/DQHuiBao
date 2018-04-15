package com.dq.huibao.adapter.pintuan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.bean.PinTuanDetails;
import com.dq.huibao.utils.HttpPath;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前已开团列表
 * Created by d on 2018/4/14.
 */

public class PTkaiTuanAdapter extends BaseAdapter{
    private Context context;
    private List<PinTuanDetails.ListBean> listBeanList = new ArrayList<>();

    public PTkaiTuanAdapter(Context context, List<PinTuanDetails.ListBean> listBeanList) {
        this.context = context;
        this.listBeanList = listBeanList;
    }

    @Override
    public int getCount() {
        return listBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PtViewHolder ptViewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pintuan_list,null);
            ptViewHolder = new PtViewHolder();
            ptViewHolder.image = convertView.findViewById(R.id.item_pt_list_image);
            ptViewHolder.textView = convertView.findViewById(R.id.item_pt_list_name);
            convertView.setTag(ptViewHolder);
        }
        ptViewHolder = (PtViewHolder) convertView.getTag();
        ptViewHolder.textView.setText(listBeanList.get(position).getNickname());
        Glide.with(context)
                .load(HttpPath.NEW_HEADER + listBeanList.get(position).getHeadimgurl())
                .placeholder(R.mipmap.icon_empty)
                .into(ptViewHolder.image);
        return convertView;
    }
    class PtViewHolder {
        private ImageView image;
        TextView textView;
    }
}
