package com.dq.huibao.adapter.pingo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dq.huibao.Interface.CheckInterface;
import com.dq.huibao.Interface.ModifyCountInterface;
import com.dq.huibao.Interface.ModifyPinGoCountInterface;
import com.dq.huibao.R;
import com.dq.huibao.bean.cart.Cart;
import com.dq.huibao.bean.pingo.PinGoCartList;
import com.dq.huibao.utils.HttpPath;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 购物车数据适配器
 */
public class PinGoShopCartAdapter extends BaseExpandableListAdapter {

    private List<String> groups;
    private List<PinGoCartList.DataBean.ListBean> children;
    private Context context;
    private CheckInterface checkInterface;
    private ModifyPinGoCountInterface modifyCountInterface;

    /**
     * 构造函数
     *
     * @param groups   组元素列表
     * @param children 子元素列表
     * @param context
     */
    public PinGoShopCartAdapter(List<String> groups, List<PinGoCartList.DataBean.ListBean> children, Context context) {
        this.groups = groups;
        this.children = children;
        this.context = context;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyPinGoCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (groups.size() > 0) {
            return groups.get(groupPosition);
        }
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //只有一个，所有直接返回数据列表
        return children;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final GroupViewHolder gholder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shop, null);
            gholder = new GroupViewHolder(convertView);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupViewHolder) convertView.getTag();
        }
//        final Cart.DataBean.CartBean group = (Cart.DataBean.CartBean) getGroup(groupPosition);

//        if(group != null){
            gholder.tvSourceName.setText(groups.get(groupPosition));
//            gholder.determineChekbox.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    group.setChoosed(((CheckBox) v).isChecked());
//                    checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());
//                    // 暴露组选接口
//                }
//            });
//            gholder.determineChekbox.setChecked(group.isChoosed());
        gholder.determineChekbox.setVisibility(View.GONE);
//        }
        return convertView;
    }

    @SuppressLint("WrongConstant")
    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {

        final ChildViewHolder cholder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopcar, null);

            cholder = new ChildViewHolder(convertView);
            convertView.setTag(cholder);
        } else {
            cholder = (ChildViewHolder) convertView.getTag();
        }
        final PinGoCartList.DataBean.ListBean goodsInfo = children.get(childPosition);

        Glide.with(context)
                .load(HttpPath.IMG_HEADER + goodsInfo.getThumb())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(cholder.ivAdapterListPic);

        cholder.tvIntro.setText("" + goodsInfo.getGoodsname());
        if (goodsInfo.getOptiontitle() == null) {
            cholder.tvColorSize.setText("");
        } else {
            cholder.tvColorSize.setText("规格：" + goodsInfo.getOptiontitle());
        }

        cholder.tvPrice.setText("¥" + goodsInfo.getMarketprice());
        cholder.tvProductprice.setTextColor(context.getResources().getColor(R.color.green_normal));
        cholder.tvProductprice.setText("zhe".equals(goodsInfo.getDistype())?"折":"减");
        cholder.tvProductprice.setVisibility(View.VISIBLE);
        cholder.etNum.setText("x " + goodsInfo.getCount());

        cholder.checkBox.setChecked(goodsInfo.isChoosed());
        cholder.checkBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsInfo.setChoosed(((CheckBox) v).isChecked());
                cholder.checkBox.setChecked(((CheckBox) v).isChecked());
                checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
            }
        });
        cholder.btAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(groupPosition, childPosition, cholder.etNum, cholder.checkBox.isChecked(),
                        goodsInfo.getId(), goodsInfo.getGoodsid(), goodsInfo.getCount());
                // 暴露增加接口
            }
        });
        cholder.btReduce.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(groupPosition, childPosition, cholder.etNum, cholder.checkBox.isChecked(),
                        goodsInfo.getId(), goodsInfo.getGoodsid(), goodsInfo.getCount());
                // 暴露删减接口
            }
        });
        //删除 购物车
        cholder.tvDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                modifyCountInterface.childDelete(groupPosition, childPosition);

                            }
                        });
                alert.show();

            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;

    }

    /**
     * 组元素绑定器
     */
    static class GroupViewHolder {
        @Bind(R.id.item_ck_shop)
        CheckBox determineChekbox;
        @Bind(R.id.item_tv_shopname)
        TextView tvSourceName;
        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 子元素绑定器
     */
    static class ChildViewHolder {
        /*选择*/
        @Bind(R.id.ck_chose)
        CheckBox checkBox;

        /*图片*/
        @Bind(R.id.iv_show_pic)
        ImageView ivAdapterListPic;

        /*名称*/
        @Bind(R.id.tv_commodity_name)
        TextView tvIntro;

        /*规格*/
        @Bind(R.id.tv_cart_option)
        TextView tvColorSize;

        /*售价*/
        @Bind(R.id.tv_price)
        TextView tvPrice;

        /*原价*/
        @Bind(R.id.tv_shopcart_productprice)
        TextView tvProductprice;

        @Bind(R.id.lin_shopcart_sub)
        LinearLayout btReduce;
        @Bind(R.id.tv_show_num)
        TextView etNum;
        @Bind(R.id.lin_shopcart_add)
        LinearLayout btAdd;
        @Bind(R.id.tv_delete)
        TextView tvDelete;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
