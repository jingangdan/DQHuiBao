package com.dq.huibao.adapter.college_go;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.HomePageInterface;
import com.dq.huibao.R;
import com.dq.huibao.adapter.index.AppimglistAdapter;
import com.dq.huibao.adapter.index.GoodsListAdapter;
import com.dq.huibao.adapter.index.IndexMoreGoodsListAdapter;
import com.dq.huibao.adapter.index.MenuAdapter;
import com.dq.huibao.bean.homepage.IndexMoreGoods;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.rollpagerview.ImageLoopAdapter;
import com.dq.huibao.rollpagerview.OnItemClickListener;
import com.dq.huibao.rollpagerview.RollPagerView;
import com.dq.huibao.ui.GoodsDetailsActivity;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 拼go首页
 * Created by jingang on 2018/02/01.
 */

public class CollegeGoHomeAdapter extends RecyclerView.Adapter {
    /**
     * 类型1：banner
     */
    public static final String TYPE_BANNER = "banner";
    /**
     * 类型2：菜单
     */
    public static final String TYPE_MENU = "menu";
    /**
     * 类型3：公告
     */
    public static final String TYPE_NOTICE = "notice";
    /**
     * 类型4：图片组
     * 需要判断childlist大小，宽度
     */
    public static final String TYPE_IMGLIST = "imglist";
    /**
     * 类型5：商品列表
     */
    public static final String TYPE_GOODSLIST = "glist";


    /**
     * 当前类型
     */
    public String currentType = TYPE_MENU;

    private final Context mContext;
    private List<Index.DataBean> dataList;

    private GridLayoutManager mManager;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;
    private HomePageInterface hpInterface;

    public CollegeGoHomeAdapter(Context mContext, List<Index.DataBean> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;

        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setHpInterface(HomePageInterface hpInterface) {
        this.hpInterface = hpInterface;
    }

    /**
     * 相当于getView创建ViewHolder布局
     *
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == dataList.size()){
            goodsListMoreViewHolder = new GoodsListMoreViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_goodslist, parent, false));
            return goodsListMoreViewHolder;
        }
        String type = dataList.get(viewType).getModule();
        if (TYPE_BANNER.equals(type)) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_banner, parent, false));
        } else if (TYPE_MENU.equals(type)) {
            return new MenuViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_menu, parent, false));
        }else if (TYPE_NOTICE.equals(type)) {
            return new NoticeViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_notice, parent, false));
        } else if (TYPE_IMGLIST.equals(type)) {
            return new ImgListViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_imglist, parent, false),dataList.get(viewType).getWidth());
        } else if (TYPE_GOODSLIST.equals(type)) {
            return new GoodsListViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_goodslist, parent, false));
        }
        return null;
    }


    /**
     * 相当于getView中的绑定数据模块
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //底部加载更多商品直接return
        if (position == dataList.size()){
            goodsListMoreViewHolder.setData();
            return;
        }

        String type = dataList.get(getItemViewType(position)).getModule();
        if (TYPE_BANNER.equals(type)) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(dataList.get(position).getChild());
        } else if (TYPE_MENU.equals(type)) {
            MenuViewHolder menuViewHolder = (MenuViewHolder) holder;
            menuViewHolder.setData(dataList.get(position).getChild());
        } else if (TYPE_NOTICE.equals(type)) {
            NoticeViewHolder noticeViewHolder = (NoticeViewHolder) holder;
            noticeViewHolder.setData(dataList.get(position).getChild());
        } else if (TYPE_IMGLIST.equals(type)) {
            ImgListViewHolder imgListViewHolder = (ImgListViewHolder) holder;
            imgListViewHolder.setData(dataList.get(position).getChild());
        } else if (TYPE_GOODSLIST.equals(type)) {
            GoodsListViewHolder goodsListViewHolder = (GoodsListViewHolder) holder;
            goodsListViewHolder.setData(dataList.get(position).getChild());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    /**
     * 总共有多少个item
     *
     * @return
     */
    @Override
    public int getItemCount() {
        //底部添加一个更多商品的列表模块
        return dataList.size() + 1;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        RollPagerView rollPagerView;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            rollPagerView = itemView.findViewById(R.id.rollPagerView);

            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
            int width = dm.widthPixels;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rollPagerView.getLayoutParams();
            params.height = width / 2;//宽高比 1:2
            rollPagerView.setLayoutParams(params);
        }

        public void setData(final List<Index.DataBean.ChildBean> bannerBeans) {
            rollPagerView.setAdapter(new ImageLoopAdapter(rollPagerView, mContext, bannerBeans));
            rollPagerView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    //暴露banner接口
                    hpInterface.doHomePage(
                            position,
                            bannerBeans.get(position).getTitle(),
                            bannerBeans.get(position).getType(),
                            bannerBeans.get(position).getContent()
                    );
                }
            });
        }

    }

    class MenuViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private RecyclerView recyclerView;

        public MenuViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_menu);

        }

        public void setData(final List<Index.DataBean.ChildBean> menuBeans) {
            mManager = new GridLayoutManager(mContext, 5, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mManager);
            final MenuAdapter menuAdapter = new MenuAdapter(mContext, menuBeans);
            recyclerView.setAdapter(menuAdapter);

            menuAdapter.setOnItemClickListener(new com.dq.huibao.Interface.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    hpInterface.doHomePage(
                            position,
                            menuBeans.get(position).getTitle(),
                            menuBeans.get(position).getType(),
                            menuBeans.get(position).getContent()
                    );
                }
            });

        }
    }
    class NoticeViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private TextView textView;

        public NoticeViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            textView =  itemView.findViewById(R.id.homepage_notice);

        }

        public void setData(final List<Index.DataBean.ChildBean> noticeBeans) {
            textView.setText(noticeBeans.get(0).getExplain());
        }
    }

    class ImgListViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private RecyclerView recyclerView;
        LinearLayout linearLayout;
        private List<ImageView> imageViews = new ArrayList<>();
        //imaList下布局类型
        /**
         * 是否是横着的布局
         * 100:正常列表-----
         * 1000：横向滑动
         * 50:流布局
         */
        private String widthPraent = "100";

        public ImgListViewHolder(Context mContext, View itemView, String type) {
            super(itemView);
            this.mContext = mContext;
            widthPraent = type;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_imglist);
            linearLayout = itemView.findViewById(R.id.imagelist_3_layout);
            imageViews.add((ImageView) itemView.findViewById(R.id.imagelist_3_1));
            imageViews.add((ImageView) itemView.findViewById(R.id.imagelist_3_2));
            imageViews.add((ImageView) itemView.findViewById(R.id.imagelist_3_3));
        }

        public void setData(final List<Index.DataBean.ChildBean> appimglistBeans) {
            int layoutId = R.layout.item_hp_picture;
            if (widthPraent.equals("100")) {//正常卡片布局
                mManager = new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false);
                mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        String imgwidth = appimglistBeans.get(position).getWidth();
                        if (imgwidth.equals("50")) {
                            return 2;
                        } else if (imgwidth.equals("100")) {
                            return 4;
                        }
                        return 1;
                    }
                });
                recyclerView.setLayoutManager(mManager);
            }else if (widthPraent.equals("50")){//特殊布局
                linearLayout.setVisibility(View.VISIBLE);
                for (int i = 0; i < appimglistBeans.size(); i++) {
                    ImageUtils.loadIntoUseFitWidths(mContext,HttpPath.NEW_HEADER + appimglistBeans.get(i).getThumb(),
                            R.mipmap.icon_empty003,imageViews.get(i));
                }
            }else if (widthPraent.equals("1000")){//横向布局
                layoutId = R.layout.item_hp_picture_horizontal;
//                mManager = new GridLayoutManager(mContext, 1, GridLayoutManager.HORIZONTAL, false);
//                mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                    @Override
//                    public int getSpanSize(int position) {
//                        String imgwidth = appimglistBeans.get(position).getWidth();
//                        if (imgwidth.equals("50")) {
//                            return 2;
//                        } else if (imgwidth.equals("100")) {
//                            return 4;
//                        }
//                        return 1;
//                    }
//                });
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            }
            final AppimglistAdapter appimglistAdapter = new AppimglistAdapter(mContext, appimglistBeans,layoutId);
            recyclerView.setAdapter(appimglistAdapter);

            appimglistAdapter.setOnItemClickListener(new com.dq.huibao.Interface.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        hpInterface.doHomePage(
                            position,
                            appimglistBeans.get(position).getTitle(),
                            appimglistBeans.get(position).getType(),
                            appimglistBeans.get(position).getContent()
                        );
                    }
                });

            }

    }

    class GoodsListViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private RecyclerView recyclerView;

        public GoodsListViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_goodslist);
        }

        public void setData(final List<Index.DataBean.ChildBean> glistBeans) {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
            final GoodsListAdapter goodsListAdapter = new GoodsListAdapter(mContext, glistBeans);
            recyclerView.setAdapter(goodsListAdapter);

            goodsListAdapter.setOnItemClickListener(new com.dq.huibao.Interface.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
                    intent.putExtra("gid", glistBeans.get(position).getId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    /**
     * 加载更多商品
     * @param glistBeans:
     */
    public void setMoreGoods(List<IndexMoreGoods.DataBean.ListBean> glistBeans){
        if (goodsListMoreViewHolder != null){
            goodsListMoreViewHolder.addData(glistBeans);
        }
    }

    /**
     * 刷新更多商品
     */
    public void refreshMoreGoods(){
        if (goodsListMoreViewHolder != null)
            goodsListMoreViewHolder.refresh();
    }
    /**
     * 更多商品
     */
    GoodsListMoreViewHolder goodsListMoreViewHolder = null;
    class GoodsListMoreViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private RecyclerView recyclerView;
        IndexMoreGoodsListAdapter goodsListAdapter;
        List<IndexMoreGoods.DataBean.ListBean> glistBeans = new ArrayList<>();
        public GoodsListMoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_goodslist);
        }

        public void setData() {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
            goodsListAdapter = new IndexMoreGoodsListAdapter(mContext, glistBeans);
            recyclerView.setAdapter(goodsListAdapter);

            goodsListAdapter.setOnItemClickListener(new com.dq.huibao.Interface.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
                    intent.putExtra("gid", glistBeans.get(position).getId());
                    mContext.startActivity(intent);
                }
            });

        }

        /**
         * 刷新数据
         */
        public void addData(List<IndexMoreGoods.DataBean.ListBean> list){
            glistBeans.addAll(list);
            goodsListAdapter.notifyDataSetChanged();
        }

        /**
         * 刷新数据
         */
        public void refresh(){
            glistBeans.clear();
            goodsListAdapter.notifyDataSetChanged();
        }
    }
}


