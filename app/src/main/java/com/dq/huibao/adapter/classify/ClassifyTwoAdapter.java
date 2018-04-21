package com.dq.huibao.adapter.classify;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.goods.CateChildren;
import com.dq.huibao.ui.GoodsListActivity;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * Description：分类下商品列表
 * Created by jingang on 2017/11/6.
 */

public class ClassifyTwoAdapter extends RecyclerView.Adapter<ClassifyTwoAdapter.MyViewHolder> {
    private Context mContext;
    private List<CateChildren.DataBean> childrenList;
    private OnItemClickListener mOnItemClickListener;
    private boolean sIsScrolling = false;

    public ClassifyTwoAdapter(Context mContext, List<CateChildren.DataBean> childrenList) {
        this.mContext = mContext;
        this.childrenList = childrenList;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_classifytwo, parent, false));

        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });
        }
        String img = childrenList.get(position).getAdvimg();
        if (!img.equals("")) {
            ImageUtils.loadIntoUseFitWidth(mContext,
                    img,
                    R.mipmap.icon_empty002,
                    R.mipmap.icon_error002,
                    holder.img);

        }

//        Glide.with(mContext)
//                .load(HttpPath.NEW_HEADER+img)
//                .asBitmap()
//                .into(holder.img);

        holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false));
        ClassifyThreeAdapter classifyThreeAdapter = new ClassifyThreeAdapter(mContext, childrenList.get(position).getChildren());


        holder.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    sIsScrolling = true;
                    Glide.with(mContext).pauseRequests();
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (sIsScrolling == true) {
                        Glide.with(mContext).resumeRequests();
                    }
                    sIsScrolling = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        holder.recyclerView.setAdapter(classifyThreeAdapter);

        classifyThreeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(mContext, GoodsListActivity.class);
                intent.putExtra("content", "cate=" + childrenList.get(position).getChildren().get(i).getId());
                intent.putExtra("catename", childrenList.get(position).getChildren().get(i).getCatename());
                intent.putExtra("keywords", "");
                mContext.startActivity(intent);
            }
        });

        holder.tv_catename.setText("" + childrenList.get(position).getCatename());

    }

    @Override
    public int getItemCount() {
        return childrenList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private TextView tv_catename;
        private RecyclerView recyclerView;
        private ImageView img;

        public MyViewHolder(View view) {
            super(view);
            tv_catename = (TextView) view.findViewById(R.id.tv_classifytwo_name);
            recyclerView = (RecyclerView) view.findViewById(R.id.rv_classifytwo);
            img = view.findViewById(R.id.iv_classifytwo_img);
        }
    }
}
