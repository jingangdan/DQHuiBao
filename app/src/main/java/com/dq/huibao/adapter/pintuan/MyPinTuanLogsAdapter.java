package com.dq.huibao.adapter.pintuan;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dq.huibao.R;
import com.dq.huibao.bean.jifen.JiFenLogs;
import com.dq.huibao.bean.pintuan.MyPinTuanLogs;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 我的拼团记录
 * Created by d on 2018/4/3.
 */

public class MyPinTuanLogsAdapter extends ListBaseAdapter<MyPinTuanLogs.DataBean.ListBean> {
    public MyPinTuanLogsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_my_pintuan;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final MyPinTuanLogs.DataBean.ListBean listBean = mDataList.get(position);
        ImageView imageView = holder.getView(R.id.item_my_tuan_image);
        TextView name = holder.getView(R.id.item_my_tuan_name);
        TextView status = holder.getView(R.id.item_my_tuan_status);
        TextView time = holder.getView(R.id.item_my_tuan_time);
        //
        Glide.with(mContext)
                .load(ImageUtils.getImagePath(listBean.getTuan().getThumb()))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.mipmap.icon_stub)
                .into(imageView);
        name.setText(listBean.getTuan().getTuanname());
        String str = "";
        switch (listBean.getStatus()){
            case "0"://进行中
                status.setTextColor(mContext.getResources().getColor(R.color.red_normal));
                status.setText("进行中");
                break;
            case "1"://已完成
                status.setTextColor(mContext.getResources().getColor(R.color.green_normal));
                status.setText("成功");
                break;
            case "2"://失败
                status.setTextColor(mContext.getResources().getColor(R.color.dark));
                status.setText("失败");
                break;
        }
//        status.setText();
        time.setText("时间:" + listBean.getCtime());
    }
}
