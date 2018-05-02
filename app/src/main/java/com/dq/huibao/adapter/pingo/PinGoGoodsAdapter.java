package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.bean.pingo.PinGoIndexMoreGoods;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

/**
 * 同学拼go 商品列表adapter
 * Created by d on 2018/4/3.
 */

public class PinGoGoodsAdapter extends ListBaseAdapter<PinGoIndexMoreGoods.DataBean.ListBean> {
    int imageWidth = AppUtil.getWidth()/5*2;

    public PinGoGoodsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_pingo_goods_list;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        PinGoIndexMoreGoods.DataBean.ListBean listBean = mDataList.get(position);
        ImageView imageView = holder.getView(R.id.item_pingo_goods_list_image);
        TextView name = holder.getView(R.id.item_pingo_goods_list_name);
        TextView price = holder.getView(R.id.item_pingo_goods_list_price);
        TextView oldprice = holder.getView(R.id.item_pingo_goods_list_old_price);
        RelativeLayout jindu = holder.getView(R.id.item_pingo_goods_list_pro_layout);

        //马上抢
        TextView qianggou = holder.getView(R.id.item_pingo_goods_list_msq);

        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = imageWidth;
        imageView.setLayoutParams(layoutParams);

//        Glide.with(mContext)
//                .load(HttpPath.NEW_HEADER + listBean.getThumb())
//                .placeholder(R.mipmap.icon_stub)
//                .into(imageView);

        ImageUtils.loadIntoUseFitWidth2(mContext, listBean.getThumb(), R.mipmap.icon_empty, imageView);

        name.setText(listBean.getGoodsname());
        price.setText("￥" + listBean.getMarketprice());

        if (type == 1){
            //周五秒杀
            jindu.setVisibility(View.GONE);
            TextView countTv = holder.getView(R.id.item_pingo_goods_list_ms_count);
            TextView maxnunTv = holder.getView(R.id.item_pingo_goods_list_ms_maxnum);
            holder.getView(R.id.item_pingo_goods_list_isms).setVisibility(View.VISIBLE);
            oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            oldprice.setText("￥" + listBean.getProductprice());
            countTv.setText("限购:" + listBean.getMsonecount());
            maxnunTv.setText("总限购:" + listBean.getMsmaxcount());
        }else {
            //正常下
            TextView youhui = holder.getView(R.id.item_pingo_goods_list_youhui);
            TextView number = holder.getView(R.id.item_pingo_goods_list_number);
            TextView baifenbi = holder.getView(R.id.item_pingo_goods_list_progress_text);//百分比
            ProgressBar progressBar = holder.getView(R.id.item_pingo_goods_list_progress);
            //进度条
//        oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        oldprice.setText("￥" + listBean.getMarketprice());

            youhui.setVisibility(View.GONE);
            number.setText(listBean.getSalecount() + "已抢");
            progressBar.setMax(Integer.parseInt(listBean.getStock()));
            progressBar.setProgress(Integer.parseInt(listBean.getSalecount()));
            baifenbi.setText(Integer.parseInt(listBean.getSalecount()) * 100 / Integer.parseInt(listBean.getStock())+"%");
        }


        qianggou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * type:0--普通的;1--周五秒杀;2--;;;
     */
    int type = 0;
    /**
     * 周五秒杀设置
     * @param type:type:0--普通的;1--周五秒杀;2--;;;
     */
    public void setMs(int type){
        this.type = type;
    }
}
