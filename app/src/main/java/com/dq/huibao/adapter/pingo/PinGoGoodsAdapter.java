package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.content.Intent;
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
import com.dq.huibao.ui.pingo.PinGoDetailsActivity;
import com.dq.huibao.ui.pingo.PinGoGoodsActivity;
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
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final PinGoIndexMoreGoods.DataBean.ListBean listBean = mDataList.get(position);
        ImageView imageView = holder.getView(R.id.item_pingo_goods_list_image);
        TextView name = holder.getView(R.id.item_pingo_goods_list_name);
        TextView price = holder.getView(R.id.item_pingo_goods_list_price);
        TextView oldprice = holder.getView(R.id.item_pingo_goods_list_old_price);
//        RelativeLayout jindu = holder.getView(R.id.item_pingo_goods_list_pro_layout);
        TextView baifenbi = holder.getView(R.id.item_pingo_goods_list_progress_text);//百分比
        ProgressBar progressBar = holder.getView(R.id.item_pingo_goods_list_progress);

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
        price.setText(listBean.getMarketprice());
        oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        oldprice.setText("￥" + listBean.getProductprice());
        progressBar.setMax(Integer.parseInt(listBean.getStock()));
        progressBar.setProgress(Integer.parseInt(listBean.getSalecount()));
        baifenbi.setText("已售" + listBean.getSalecount());
        if (!listBean.getStock().equals("0")){
            baifenbi.setText("已售" + Integer.parseInt(listBean.getSalecount()) * 100 / Integer.parseInt(listBean.getStock()) + "%");
        }
        if (type == 1){
            //周五秒杀
//            TextView countTv = holder.getView(R.id.item_pingo_goods_list_ms_count);
//            TextView maxnunTv = holder.getView(R.id.item_pingo_goods_list_ms_maxnum);
//            holder.getView(R.id.item_pingo_goods_list_isms).setVisibility(View.VISIBLE);
//            countTv.setText("限购   " + listBean.getMsonecount() + "/" +listBean.getMsmaxcount());
//            maxnunTv.setText("总限购:");
        }else {
            //正常下
            TextView youhui = holder.getView(R.id.item_pingo_goods_list_youhui);
            TextView number = holder.getView(R.id.item_pingo_goods_list_number);
            //进度条
//        oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        oldprice.setText("￥" + listBean.getMarketprice());

            youhui.setVisibility(View.GONE);
            number.setText(listBean.getSalecount() + "已抢");

        }


        qianggou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PinGoDetailsActivity.class);
                intent.putExtra("gid", listBean.getId());
                mContext.startActivity(intent);
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
