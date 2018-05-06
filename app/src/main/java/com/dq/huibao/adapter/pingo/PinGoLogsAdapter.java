package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.bean.pingo.PinGoIndexMoreGoods;
import com.dq.huibao.bean.pingo.PinGoLogsB;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.utils.CountDownUtil;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 同学拼go 记录
 * Created by d on 2018/4/3.
 */

public abstract class PinGoLogsAdapter extends ListBaseAdapter<PinGoLogsB.DataBean> {
//    int imageWidth = AppUtil.getWidth()/5*2;
    String type;
    public PinGoLogsAdapter(Context context,String type) {
        super(context);
        this.type = type;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_pingo_logs;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final PinGoLogsB.DataBean listBean = mDataList.get(position);

        LinearLayout ingLayout = holder.getView(R.id.item_pingo_ing_layout);
        RelativeLayout layout = holder.getView(R.id.item_pingo_layout);

        if (type.equals("1")){//已结束--+--已失败
            ingLayout.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
            ImageView imageView = holder.getView(R.id.item_pingo_image);
            TextView time = holder.getView(R.id.item_pingo_time);
            TextView delete = holder.getView(R.id.item_pingo_delete);
            TextView goodname = holder.getView(R.id.item_pingo_name);
            TextView price = holder.getView(R.id.item_pingo_pay_price);
            TextView fanxian = holder.getView(R.id.item_pingo_fanxian);
            //
            ImageUtils.loadIntoUseFitWidth2(mContext,listBean.getThumb(),R.mipmap.icon_empty002,imageView);
            goodname.setText(listBean.getGoodsname());
            price.setText("实付 ￥" + listBean.getPrice());
//            fanxian.setText(listBean.get);
            //
            time.setText(AppUtil.getDateToString("yyyy年MM月dd日",Long.parseLong(listBean.getAddtime()) * 1000));

            delete.setVisibility(View.GONE);
            //删除
//            delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    del(listBean.getOrderid());
//                }
//            });
        }else if (type.equals("0")){//进行中

            ingLayout.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
            TextView diqu = holder.getView(R.id.item_pingo_ing_diqu);
            TextView typeIng = holder.getView(R.id.item_pingo_ing_type);
            TextView timeIng = holder.getView(R.id.item_pingo_ing_timer);
            RecyclerView childList = holder.getView(R.id.item_pingo_ing_childlist);
            TextView priceIng = holder.getView(R.id.item_pingo_ing_price);

            diqu.setText(listBean.getRegname());
            typeIng.setText(listBean.getDistype().equals("jian")?"立减金额":"立打折扣");
            if (listBean.getTuicount().equals("0")){
                priceIng.setText("预计可返现 ￥" + listBean.getRemoney());
            }else {
                priceIng.setText("因 " + listBean.getTuicount() + "人退出 , 预计可返现 ￥" + listBean.getRemoney());
            }
            CountDownUtil countDownUtil = new CountDownUtil(Integer.parseInt(listBean.getRetime()) * 1000,timeIng);
            countDownUtil.countdown();
            //
            int maxcount = Integer.parseInt(listBean.getTuan().getMaxcount());
            int xcount = listBean.getUserlist().size();
            xcount = maxcount - xcount;
            for (int i = 0;i < xcount; i++){
                listBean.getUserlist().add(new PinGoLogsB.DataBean.UserlistBean());
            }
            PinGoLogUsersAdapter adapter = new PinGoLogUsersAdapter(mContext,listBean.getUserlist());
            childList.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            childList.setAdapter(adapter);
        }

    }

    public abstract void del(String orderid);
    /**
     * tuan.status:0-开团;1-满团;2-未满;3-完成结束;
     * @param str
     * @return
     */
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
