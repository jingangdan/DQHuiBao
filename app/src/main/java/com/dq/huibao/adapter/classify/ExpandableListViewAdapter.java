package com.dq.huibao.adapter.classify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.goods.CateChildren;
import com.dq.huibao.ui.GoodsListActivity;
import com.dq.huibao.utils.GlideUtils;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * Created by jingang on 2018/3/24.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<CateChildren.DataBean> dataBeans;
    private OnItemClickListener onItemClickListener;

    public ExpandableListViewAdapter(Context mContext, List<CateChildren.DataBean> dataBeans) {
        this.mContext = mContext;
        this.dataBeans = dataBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //获取子元素对象
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    //获取子元素Id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //加载子元素并显示
    @SuppressLint("WrongConstant")
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View view = null;
        ChildHolder childholder = null;
        if (convertView != null) {
            view = convertView;
            childholder = (ChildHolder) view.getTag();
        } else {
            view = View.inflate(mContext, R.layout.item_classifythree, null);
            childholder = new ChildHolder();

            childholder.tv_neme = (TextView) view.findViewById(R.id.tv_item_classifythree_name);
            childholder.img = (ImageView) view.findViewById(R.id.iv_item_classifythree_img);
            childholder.tv_name2 = (TextView) view.findViewById(R.id.tv_item_classifythree_name2);
            childholder.img2 = (ImageView) view.findViewById(R.id.iv_item_classifythree_img2);
            childholder.tv_name3 = (TextView) view.findViewById(R.id.tv_item_classifythree_name3);
            childholder.img3 = (ImageView) view.findViewById(R.id.iv_item_classifythree_img3);

            view.setTag(childholder);
        }

//        GlideUtils.loadImageView(mContext, HttpPath.IMG_HEADER + dataBeans.get(groupPosition).getChildren().get(childPosition).getThumb(), childholder.img);
//        childholder.tv_neme.setText("" + dataBeans.get(groupPosition).getChildren().get(childPosition).getCatename());

        String name = dataBeans.get(groupPosition).getChildren().get(childPosition*3).getCatename();
        String name2 = "";
        String name3 = "";

        String img = dataBeans.get(groupPosition).getChildren().get(childPosition*3).getThumb();
        String img2  = "";
        String img3 = "";

        if(dataBeans.get(groupPosition).getChildren().size()>(childPosition*3+1)){
            name2 = dataBeans.get(groupPosition).getChildren().get(childPosition*3+1).getCatename();
            img2 = dataBeans.get(groupPosition).getChildren().get(childPosition*3+1).getThumb();
        }
        if(dataBeans.get(groupPosition).getChildren().size()>(childPosition*3+2)){
            name3 = dataBeans.get(groupPosition).getChildren().get(childPosition*3+2).getCatename();
            img3 = dataBeans.get(groupPosition).getChildren().get(childPosition*3+2).getThumb();

        }
        childholder.tv_neme.setText(name);
        GlideUtils.loadImageView(mContext, ImageUtils.getImagePath(img), childholder.img);

        childholder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, GoodsListActivity.class);
                intent.putExtra("content", "cate=" + dataBeans.get(groupPosition).getChildren().get(childPosition*3).getId());
                intent.putExtra("catename", dataBeans.get(groupPosition).getChildren().get(childPosition*3).getCatename());
                intent.putExtra("keywords", "");
                mContext.startActivity(intent);
            }
        });

        if(name2!=null&&!"".equals(name2)){
            childholder.tv_name2.setVisibility(View.VISIBLE);
            childholder.img2.setVisibility(View.VISIBLE);
            childholder.tv_name2.setText(name2);
            GlideUtils.loadImageView(mContext, ImageUtils.getImagePath(img2), childholder.img2);

            childholder.img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, GoodsListActivity.class);
                    intent.putExtra("content", "cate=" + dataBeans.get(groupPosition).getChildren().get(childPosition*3+1).getId());
                    intent.putExtra("catename", dataBeans.get(groupPosition).getChildren().get(childPosition*3+1).getCatename());
                    intent.putExtra("keywords", "");
                    mContext.startActivity(intent);
                }
            });
        }else{
            childholder.tv_name2.setVisibility(View.GONE);
            childholder.img2.setVisibility(View.GONE);
        }
        if(name3!=null&&!"".equals(name3)){
            childholder.tv_name3.setVisibility(View.VISIBLE);
            childholder.img3.setVisibility(View.VISIBLE);
            childholder.tv_name3.setText(name3);
            GlideUtils.loadImageView(mContext, ImageUtils.getImagePath(img3), childholder.img3);
            childholder.img3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, GoodsListActivity.class);
                    intent.putExtra("content", "cate=" + dataBeans.get(groupPosition).getChildren().get(childPosition*3+2).getId());
                    intent.putExtra("catename", dataBeans.get(groupPosition).getChildren().get(childPosition*3+2).getCatename());
                    intent.putExtra("keywords", "");
                    mContext.startActivity(intent);
                }
            });
        }else{
            childholder.tv_name3.setVisibility(View.GONE);
            childholder.img3.setVisibility(View.GONE);
        }

        return view;
    }

    //获取子元素数目
    @Override
    public int getChildrenCount(int groupPosition) {
        //return dataBeans.get(groupPosition).getChildren().size();
        return (int) Math.ceil(dataBeans.get(groupPosition).getChildren().size()*1.00/3);
    }

    //获取组元素对象
    @Override
    public Object getGroup(int groupPosition) {
        return dataBeans.get(groupPosition);
    }

    //获取组元素数目
    @Override
    public int getGroupCount() {
        return dataBeans.size();
    }

    //获取组元素Id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //加载并显示组元素
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        View view = null;
        GroupHolder groupholder = null;
        if (convertView != null) {
            view = convertView;
            groupholder = (GroupHolder) view.getTag();
        } else {
            view = View.inflate(mContext, R.layout.item_classifytwo, null);
            groupholder = new GroupHolder();
            groupholder.tv_catename = view.findViewById(R.id.tv_classifytwo_name);
            groupholder.recyclerView = view.findViewById(R.id.rv_classifytwo);
            groupholder.img = view.findViewById(R.id.iv_classifytwo_img);

            view.setTag(groupholder);
        }

        String img = dataBeans.get(groupPosition).getAdvimg();
        if (!img.equals("")) {
            ImageUtils.loadIntoUseFitWidth(mContext,
                    img,
                    R.mipmap.icon_empty002,
                    R.mipmap.icon_error002,
                    groupholder.img);

        }

        groupholder.tv_catename.setText("" + dataBeans.get(groupPosition).getCatename());
        return view;
    }

    @Override
    public boolean hasStableIds() {

        return false;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }


    static class GroupHolder {
        private TextView tv_catename;
        private RecyclerView recyclerView;
        private ImageView img;
    }

    static class ChildHolder {
        private TextView tv_neme, tv_name2, tv_name3;
        private ImageView img, img2, img3;

    }
}
