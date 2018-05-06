package com.dq.huibao.adapter.tixian;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.pingo.PinGoLogUsersAdapter;
import com.dq.huibao.bean.pingo.PinGoLogsB;
import com.dq.huibao.bean.tixian.TiXianLogsB;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.utils.CountDownUtil;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 提现记录
 * Created by d on 2018/4/3.
 */

public abstract class TiXianLogsAdapter extends ListBaseAdapter<TiXianLogsB.DataBean> {
    public TiXianLogsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tixian_logs;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final TiXianLogsB.DataBean listBean = mDataList.get(position);

        TextView account = holder.getView(R.id.item_tixian_account);
        TextView price = holder.getView(R.id.item_tixian_price);
        TextView status = holder.getView(R.id.item_tixian_status);
        TextView desc = holder.getView(R.id.item_tixian_desc);
        TextView time = holder.getView(R.id.item_tixian_time);

        account.setText(listBean.getAccount());
        price.setText(listBean.getMoney());
        status.setText(listBean.getFlag());
        desc.setText(listBean.getAccount_desc());
        time.setText(AppUtil.getDateToString("yyyy.MM.dd",Long.parseLong(listBean.getCtime()) * 1000));

        //Status 0 处理中 1 提现成功 2提现失败 -1 提现申请已取消
        if (listBean.getStatus().equals("0")){
            holder.getView(R.id.item_tixian_quxiao).setVisibility(View.VISIBLE);
            holder.getView(R.id.item_tixian_quxiao).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancle(listBean.getId());
                }
            });
        }
    }
    public abstract void cancle(String id);
}
