package com.dq.huibao.adapter.xstore;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dq.huibao.R;
import com.dq.huibao.bean.xstore.XStoreGoods;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 小店自选商品adapter
 * Created by d on 2018/4/3.
 */

public class XStoreIndexAdapter extends ListBaseAdapter<XStoreGoods.DataBean.ListBean> {

    public XStoreIndexAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_xstore_index_goods;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        XStoreGoods.DataBean.ListBean listBean = mDataList.get(position);
        ImageView imageView = holder.getView(R.id.item_xstore_goods_iamge);
        TextView name = holder.getView(R.id.item_xstore_goods_name);
        TextView price = holder.getView(R.id.item_xstore_goods_price);
        TextView oldPrice = holder.getView(R.id.item_xstore_goods_oldprice);
        oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //添加删除线
//        Glide.with(mContext)
//                .load(HttpPath.NEW_HEADER + listBean.getThumb())
//                .placeholder(R.mipmap.icon_stub)
//                .into(imageView);

        ImageUtils.loadIntoUseFitWidth2(mContext, listBean.getThumb(), R.mipmap.icon_stub, imageView);

        name.setText(listBean.getGoodsname());
        price.setText("￥ " + listBean.getMarketprice());
        oldPrice.setText("￥ " + listBean.getProductprice());
    }
}
