package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.bean.pingo.PinGoIndexMoreGoods;
import com.dq.huibao.bean.pingo.PinGoLogsB;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 同学拼go 记录
 * Created by d on 2018/4/3.
 */

public class PinGoLogsAdapter extends ListBaseAdapter<PinGoLogsB.DataBean> {
    int imageWidth = AppUtil.getWidth()/5*2;

    public PinGoLogsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_pingo_logs;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        PinGoLogsB.DataBean listBean = mDataList.get(position);
        TextView diqu = holder.getView(R.id.item_pingo_quyu);
        TextView type = holder.getView(R.id.item_pingo_type);
        TextView status = holder.getView(R.id.item_pingo_status);
        TextView price = holder.getView(R.id.item_pingo_pay_price);
        TextView payStatus = holder.getView(R.id.item_pingo_pay_status);
        TextView time = holder.getView(R.id.item_pingo_time);

        diqu.setText(listBean.getRegname());
        type.setText(listBean.getDistype().equals("jian")?"立减":"折扣");
        price.setText("￥" + listBean.getPrice());
        // tuan.status:0-开团;1-满团;2-未满;3-完成结束;
        status.setText(getStatus(listBean.getTuan().getStatus()));
        payStatus.setText("未支付");
        if (listBean.getPaystatus().equals("1")){
            payStatus.setTextColor(mContext.getResources().getColor(R.color.green_normal));
            payStatus.setText("已支付");
        }

        time.setText(AppUtil.getDateToString("yyyy-MM-dd HH:mm:ss",Long.parseLong(listBean.getAddtime()) * 1000));
    }
    public String getStatus(String str){
        switch (str){
            case "0":
                return "开团";
                case "1":
                    return "满团";
                case "2":
                    return "未满";
                case "3":
                    return "结束";
        }
        return "完成";
    }
}
