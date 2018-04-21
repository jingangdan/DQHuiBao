package com.dq.huibao.adapter.xstore;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.bean.xstore.XStoreZXGoods;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

import java.util.Map;

/**
 * 小店自选商品adapter(已选)
 * Created by d on 2018/4/3.
 */

public abstract class XStoreZXYXGoodsAdapter extends ListBaseAdapter<XStoreZXGoods.DataBean.ListBean> {
    public XStoreZXYXGoodsAdapter(Context context) {
        super(context);
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
                .load(ImageUtils.getImagePath(listBean.getThumb()))
                .placeholder(R.mipmap.icon_stub)
                .into(imageView);
        name.setText(listBean.getGoodsname());
        price.setText("价格: ￥" + listBean.getMarketprice());
        checkBox.setChecked(true);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position,listBean.getId());
            }
        });
    }
    public abstract void remove(int postion,String id);
}
