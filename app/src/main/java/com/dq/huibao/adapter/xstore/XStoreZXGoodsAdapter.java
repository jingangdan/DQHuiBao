package com.dq.huibao.adapter.xstore;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dq.huibao.R;
import com.dq.huibao.bean.xstore.XStoreGoods;
import com.dq.huibao.bean.xstore.XStoreZXGoods;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 小店自选商品adapter
 * Created by d on 2018/4/3.
 */

public abstract class XStoreZXGoodsAdapter extends ListBaseAdapter<XStoreZXGoods.DataBean.ListBean> {
    Map<String,String> goodsIdSets;
    public XStoreZXGoodsAdapter(Context context,Map<String,String> goodsIdSets) {
        super(context);
        this.goodsIdSets = goodsIdSets;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_xstore_zx_goods;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final XStoreZXGoods.DataBean.ListBean listBean = mDataList.get(position);
        ImageView imageView = holder.getView(R.id.item_xstore_zx_goods_iamge);
        TextView name = holder.getView(R.id.item_xstore_zx_goods_name);
        TextView price = holder.getView(R.id.item_xstore_zx_goods_price);
        final CheckBox checkBox = holder.getView(R.id.item_xstore_zx_goods_checked);
        //
        Glide.with(mContext)
                .load(HttpPath.NEW_HEADER + listBean.getThumb())
                .placeholder(R.mipmap.icon_stub)
                .into(imageView);
        name.setText(listBean.getGoodsname());
        price.setText("价格: ￥" + listBean.getMarketprice());
        checkBox.setChecked(listBean.getId().equals(goodsIdSets.get(listBean.getId())));
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGoods(checkBox.isChecked(),listBean);
                if (checkBox.isChecked()){
                    goodsIdSets.put(listBean.getId(),listBean.getId());
                }else {
                    goodsIdSets.remove(listBean.getId());
                }
            }
        });
    }
    public abstract void addGoods(boolean isAdd,XStoreZXGoods.DataBean.ListBean bean);
}
