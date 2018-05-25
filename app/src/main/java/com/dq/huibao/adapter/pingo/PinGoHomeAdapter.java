package com.dq.huibao.adapter.pingo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dq.huibao.Interface.HomePageInterface;
import com.dq.huibao.R;
import com.dq.huibao.adapter.index.AppimglistAdapter;
import com.dq.huibao.adapter.index.GoodsListAdapter;
import com.dq.huibao.adapter.index.IndexMoreGoodsListAdapter;
import com.dq.huibao.adapter.index.MenuAdapter;
import com.dq.huibao.bean.homepage.IndexMoreGoods;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.bean.pingo.PinGoCenterTuan;
import com.dq.huibao.bean.pingo.PinGoIndex;
import com.dq.huibao.bean.pingo.PinGoIndexMoreGoods;
import com.dq.huibao.rollpagerview.ImageLoopAdapter;
import com.dq.huibao.rollpagerview.OnItemClickListener;
import com.dq.huibao.rollpagerview.RollPagerView;
import com.dq.huibao.ui.GoodsDetailsActivity;
import com.dq.huibao.ui.GoodsListActivity;
import com.dq.huibao.ui.pingo.PinGoActivity;
import com.dq.huibao.ui.pingo.PinGoGoodsActivity;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.utils.CountDownUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 拼go首页
 * Created by jingang on 2018/02/01.
 */

public class PinGoHomeAdapter extends RecyclerView.Adapter {
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
    public static final String TYPE_PINGO = "dxspg";
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

    private List<PinGoIndex.DataBean> dataList;

    private GridLayoutManager mManager;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;
    private HomePageInterface hpInterface;

    public PinGoHomeAdapter(Context mContext, List<PinGoIndex.DataBean> dataList) {
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
        if (viewType == dataList.size()) {
            goodsListMoreViewHolder = new GoodsListMoreViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_goodslist, parent, false));
            return goodsListMoreViewHolder;
        }
        String type = dataList.get(viewType).getModule();
        if (TYPE_BANNER.equals(type)) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_banner, parent, false));
        } else if (TYPE_IMGLIST.equals(type)) {
            return new ImgListViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_imglist, parent, false), dataList.get(viewType).getWidth());
        } else if (TYPE_PINGO.equals(type)) {
            pinGoeViewHolder = new PinGoeViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_pingo, parent, false));
            return pinGoeViewHolder;
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
        if (position == dataList.size()) {
            goodsListMoreViewHolder.setData();
            return;
        }

        String type = dataList.get(getItemViewType(position)).getModule();
        if (TYPE_BANNER.equals(type)) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(dataList.get(position).getChild());
        }  else if (TYPE_IMGLIST.equals(type)) {
            ImgListViewHolder imgListViewHolder = (ImgListViewHolder) holder;
            imgListViewHolder.setData(dataList.get(position).getChild());
        } else if (TYPE_PINGO.equals(type)) {
            PinGoeViewHolder pinGoeViewHolder = (PinGoeViewHolder) holder;
            pinGoeViewHolder.setData();
        } else if (TYPE_GOODSLIST.equals(type)) {
            GoodsListViewHolder goodsListViewHolder = (GoodsListViewHolder) holder;
            goodsListViewHolder.setData(dataList.get(position));
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
        }

        public void setData(final List<PinGoIndex.DataBean.ChildBean> bannerBeans) {
            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
            int width = dm.widthPixels;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rollPagerView.getLayoutParams();
            params.height = width / 2;//宽高比 1:2
            rollPagerView.setLayoutParams(params);
            //计算图片高度并设置高度
            ImageUtils.setImageParamsHeight(mContext,bannerBeans.get(0).getThumb(),rollPagerView,params);

            rollPagerView.setAdapter(new PGImageLoopAdapter(rollPagerView, mContext, bannerBeans));
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

    /**
     * 拼go
     */
    class PinGoeViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        /*左侧：listview*/
        RecyclerView lijianLeftList;
        /*时间*/
        TextView lijianTime,zhekouTime;
        /*右侧：剩余单数，提示(xx发起..)，优惠(下单优惠多少)*/
        TextView lijianRightShengyudanshu,lijianRightTishi,lijianRightYouhui;
        /*右侧：listview*/
        RecyclerView lijianRightList;
        /*区分立减还是折扣:0-立减，1-折扣*/
        /**顺序立减左侧-立减右侧-折扣左侧-折扣右侧*/
        /*地区名称*/
        List<TextView> pingodiquName = new ArrayList<>();
        /*提示剩余单数*/
        List<TextView> pingosyds = new ArrayList<>();
        /*最近一个加入拼团的人*/
        List<TextView> newPeople = new ArrayList<>();
        /*优惠方式或者额度*/
        List<TextView> youhui = new ArrayList<>();
        /*参团人员列表*/
        List<RecyclerView> recycleViews = new ArrayList<>();
        List<PinGoIndexChildAdapter> adapters = new ArrayList<>();
        /*数据*/
        List<PinGoCenterTuan.DataBean.ListBeanX> dataList = new ArrayList<>();
        public PinGoeViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            /*地区名称*/
            pingodiquName.add((TextView) itemView.findViewById(R.id.pingo_lijian_left_name));
            pingodiquName.add((TextView) itemView.findViewById(R.id.pingo_lijian_right_name));
            pingodiquName.add((TextView) itemView.findViewById(R.id.pingo_zhekou_left_name));
            pingodiquName.add((TextView) itemView.findViewById(R.id.pingo_zhekou_right_name));
            /*剩余单数提示*/
            pingosyds.add((TextView) itemView.findViewById(R.id.pingo_lijian_left_shengyudanshu));
            pingosyds.add((TextView) itemView.findViewById(R.id.pingo_lijian_right_shengyudanshu));
            pingosyds.add((TextView) itemView.findViewById(R.id.pingo_zhekou_left_shengyudanshu));
            pingosyds.add((TextView) itemView.findViewById(R.id.pingo_zhekou_right_shengyudanshu));
            /*最近一个加入拼团的人*/
            newPeople.add((TextView) itemView.findViewById(R.id.pingo_lijian_left_tishi));
            newPeople.add((TextView) itemView.findViewById(R.id.pingo_lijian_right_tishi));
            newPeople.add((TextView) itemView.findViewById(R.id.pingo_zhekou_left_tishi));
            newPeople.add((TextView) itemView.findViewById(R.id.pingo_zhekou_right_tishi));
            /*优惠方式或者额度*/
            youhui.add((TextView) itemView.findViewById(R.id.pingo_lijian_left_youhui));
            youhui.add((TextView) itemView.findViewById(R.id.pingo_lijian_right_youhui));
            youhui.add((TextView) itemView.findViewById(R.id.pingo_zhekou_left_youhui));
            youhui.add((TextView) itemView.findViewById(R.id.pingo_zhekou_right_youhui));
            /*参团人员列表*/
            recycleViews.add((RecyclerView) itemView.findViewById(R.id.pingo_lijian_left_list));
            recycleViews.add((RecyclerView) itemView.findViewById(R.id.pingo_lijian_right_list));
            recycleViews.add((RecyclerView) itemView.findViewById(R.id.pingo_zhekou_left_list));
            recycleViews.add((RecyclerView) itemView.findViewById(R.id.pingo_zhekou_right_list));
            /*时间*/
            lijianTime = itemView.findViewById(R.id.pingo_lijian_center_time);
            zhekouTime = itemView.findViewById(R.id.pingo_zhekou_center_time);
            //
        }

        public void setData() {
            dataList.add(new PinGoCenterTuan.DataBean.ListBeanX());
            dataList.add(new PinGoCenterTuan.DataBean.ListBeanX());
            dataList.add(new PinGoCenterTuan.DataBean.ListBeanX());
            dataList.add(new PinGoCenterTuan.DataBean.ListBeanX());
            for (int i = 0; i < 4; i++) {
                recycleViews.get(i).setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            }
        }

        /**
         * 刷新数据
         * @param dataBeans
         */
        public void refreshData(PinGoCenterTuan.DataBean dataBeans){
            Long time = (Long.parseLong((dataBeans.getEndtime()+"")) - System.currentTimeMillis()/1000);
            CountDownUtil countDownUtil = new CountDownUtil(time * 1000,lijianTime,zhekouTime);
            countDownUtil.countdown();
            //设置倒计时
            dataList.clear();
            adapters.clear();

            dataList.add(dataBeans.getList().get(0).get(0));
            dataList.add(dataBeans.getList().get(0).get(1));
            dataList.add(dataBeans.getList().get(1).get(0));
            dataList.add(dataBeans.getList().get(1).get(1));
            for (int i = 0; i < dataList.size(); i++) {
                //地区名称
                pingodiquName.get(i).setText(dataList.get(i).getRegname());
                //还剩几单
                pingosyds.get(i).setText("余" + (Integer.parseInt(dataList.get(i).getMaxcount()) - Integer.parseInt(dataList.get(i).getNowcount())) + "单");

                //数据有问题，暂时先这样
                try {
                    //最新加入的人员
                    if (dataList.get(i).getList().size() != 0){
                        newPeople.get(i).setText(dataList.get(i).getList().get(dataList.get(i).getList().size()-1).getRname() + " 刚刚加入团");
                    }
                    //优惠
                    youhui.get(i).setText("加入立"+ (i < 2 ? "减"+dataList.get(i).getJian()+"元" : "打"+dataList.get(i).getZhe()+"折"));
                    //设置参团人员
                }catch (Exception e){

                }
                //最后一个默认添加
                dataList.get(i).getList().add(0,new PinGoCenterTuan.DataBean.ListBeanX.ListBean());
                adapters.add(new PinGoIndexChildAdapter(mContext,dataList.get(i).getList(),dataList.get(i).getDistype()));
                adapters.get(i).setOnItemClickListener(new PinGoIndexChildAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String type) {
                        hpInterface.doHomePage(0, "","goodsList", type);
                    }
                });
                recycleViews.get(i).setAdapter(adapters.get(i));
            }

        }
    }
    PinGoeViewHolder pinGoeViewHolder = null;
    /**
     * 刷新拼go信息
     * @param dataBeans
     */
    public void refreshPinGo(PinGoCenterTuan.DataBean dataBeans){
        if (pinGoeViewHolder != null){
            pinGoeViewHolder.refreshData(dataBeans);
        }
    }

    /**
     * imageList
     */
    class ImgListViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private RecyclerView recyclerView;
        public ImgListViewHolder(Context mContext, View itemView, String type) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_imglist);
        }

        public void setData(final List<PinGoIndex.DataBean.ChildBean> listBeans) {
            int layoutid = R.layout.item_pingo_image;
            if (listBeans.get(0).getType().equals("other") && !"#".equals(listBeans.get(0).getContent())){
                layoutid = R.layout.item_pingo_image_text;
            }
            GridLayoutManager manager = new GridLayoutManager(mContext,5, GridLayoutManager.VERTICAL, false);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (listBeans.size() == 1) {
                        return 5;
                    }
                    return 1;
                }
            });
            recyclerView.setLayoutManager(manager);
            final PinGoimglistAdapter appimglistAdapter = new PinGoimglistAdapter(mContext, listBeans,layoutid);
            recyclerView.setAdapter(appimglistAdapter);

            appimglistAdapter.setOnItemClickListener(new com.dq.huibao.Interface.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    hpInterface.doHomePage(
                            position,
                            listBeans.get(position).getTitle(),
                            listBeans.get(position).getType(),
                            listBeans.get(position).getContent()
                    );
                }
            });

        }

    }

    class GoodsListViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private RecyclerView recyclerView;
        private LinearLayout titleLayout;
        private ImageView titleImagetm,titleImagems;
        private TextView title,more;
        String type = "";
        public GoodsListViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = itemView.findViewById(R.id.rv_goodslist);
            titleLayout = itemView.findViewById(R.id.goods_title_layout);
            titleImagetm = itemView.findViewById(R.id.goods_title_image_tm);
            titleImagems = itemView.findViewById(R.id.goods_title_image_ms);
            title = itemView.findViewById(R.id.goods_title_title);
            more = itemView.findViewById(R.id.goods_title_more);
        }

        public void setData(final PinGoIndex.DataBean glistBeans) {
            if (glistBeans.getTitle().indexOf(PinGoActivity.TYPE_MIAOSHA) !=-1 ){
                type = PinGoActivity.TYPE_MIAOSHA;
                titleLayout.setVisibility(View.VISIBLE);
                titleImagems.setVisibility(View.VISIBLE);
            }else if (glistBeans.getTitle().indexOf(PinGoActivity.TYPE_TEMAI) !=-1 ){
                type = PinGoActivity.TYPE_TEMAI;
                titleLayout.setVisibility(View.VISIBLE);
                titleImagems.setVisibility(View.VISIBLE);
            }
            title.setText(type);
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hpInterface.doHomePage(0, "", "other",type);
                }
            });

            recyclerView.setLayoutManager(new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false));
            final PGGoodsListAdapter goodsListAdapter = new PGGoodsListAdapter(mContext, glistBeans.getChild());
            recyclerView.setAdapter(goodsListAdapter);

            goodsListAdapter.setOnItemClickListener(new com.dq.huibao.Interface.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    hpInterface.doHomePage(
                            position,
                            glistBeans.getChild().get(position).getTitle(),
                            "goods",
                            glistBeans.getChild().get(position).getId()
                    );
                }
            });
        }
    }

    /**
     * 加载更多商品
     *
     * @param glistBeans:
     */
    public void setMoreGoods(List<PinGoIndexMoreGoods.DataBean.ListBean> glistBeans) {
        if (goodsListMoreViewHolder != null) {
            goodsListMoreViewHolder.addData(glistBeans);
        }
    }

    /**
     * 刷新更多商品
     */
    public void refreshMoreGoods() {
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
        PinGoMoreGoodsAdapter goodsListAdapter;
        private TextView textView;
        List<PinGoIndexMoreGoods.DataBean.ListBean> glistBeans = new ArrayList<>();

        public GoodsListMoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_goodslist);
            textView = itemView.findViewById(R.id.index_more);
        }

        public void setData() {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
            goodsListAdapter = new PinGoMoreGoodsAdapter(mContext, glistBeans);
            recyclerView.setAdapter(goodsListAdapter);

            goodsListAdapter.setOnItemClickListener(new com.dq.huibao.Interface.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    hpInterface.doHomePage(
                            position,
                            glistBeans.get(position).getGoodsname(),
                            "goods",
                            glistBeans.get(position).getId()
                    );
                }
            });
            textView.setVisibility(View.VISIBLE);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PinGoGoodsActivity.class);
                    intent.putExtra("isms", "0");
                    intent.putExtra("istm", "0");
                    intent.putExtra("title", "拼go商品");
                    intent.putExtra("goodsType", "");
                    mContext.startActivity(intent);
                }
            });
        }

        /**
         * 刷新数据
         */
        public void addData(List<PinGoIndexMoreGoods.DataBean.ListBean> list) {
            glistBeans.addAll(list);
            goodsListAdapter.notifyDataSetChanged();
        }

        /**
         * 刷新数据
         */
        public void refresh() {
            glistBeans.clear();
            goodsListAdapter.notifyDataSetChanged();
        }
    }
}


