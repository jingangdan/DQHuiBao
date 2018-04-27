package com.dq.huibao.ui.pingo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.pingo.PinGoGoodsAdapter;
import com.dq.huibao.adapter.pingo.PinGoGoodsTopAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.pingo.GoodsListTop;
import com.dq.huibao.bean.pingo.PinGoIndexMoreGoods;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.SPUserInfo;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 同学拼go -商品列表页
 * Created by d on 2018/4/2.
 */

public class PinGoGoodsActivity extends BaseActivity {
    View view = null;
    @Bind(R.id.list_pingo_goods)
    LRecyclerView goodsListView;
    @Bind(R.id.pingo_ms_top_tv)
    TextView pingoMsTopTv;
    @Bind(R.id.pingo_ms_top_time_tv)
    TextView pingoMsTopTimeTv;
    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String uid = "", phone = "", token = "";
    private String isms = "", istm = "";
    private int page = 1, pagesize = 10;
    //
    View headerView;
    //
    PinGoGoodsAdapter pinGoListGoodsAdapter;
    LRecyclerViewAdapter lRecyclerViewGoodsAdapter;
    String goodsType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinggo_goods);
        ButterKnife.bind(this, this);

        pinGoListGoodsAdapter = new PinGoGoodsAdapter(this);
        lRecyclerViewGoodsAdapter = new LRecyclerViewAdapter(pinGoListGoodsAdapter);
        goodsListView.setLayoutManager(new LinearLayoutManager(this));
        goodsListView.setAdapter(lRecyclerViewGoodsAdapter);

        goodsListView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                getListGoods();
            }
        });
        goodsListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                pinGoListGoodsAdapter.clear();
                goodsListView.setNoMore(false);
                page = 1;
                getTopGoods();
                getListGoods();
            }
        });

        goodsType = getIntent().getStringExtra("goodsType");
        isms = getIntent().getStringExtra("isms");
        istm = getIntent().getStringExtra("istm");
        isms = (isms == null ? "0" : isms);
        istm = (istm == null ? "0" : istm);
        if (istm.equals("1") || isms.equals("1")) {

        } else {
            addHeadView();
            getTopGoods();
        }
        getListGoods();
    }

    //
    PinGoGoodsTopAdapter topAdapter;
    RecyclerView topLecyclerView;

    private void addHeadView() {
        headerView = LayoutInflater.from(this).inflate(R.layout.header_pingo_goods_layout, null);
        topLecyclerView = headerView.findViewById(R.id.header_pingo_goodsTop_list);
        topLecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        lRecyclerViewGoodsAdapter.addHeaderView(headerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);

        pinGoListGoodsAdapter.clear();
    }

    /**
     * 获取顶部goods
     */
    public void getTopGoods() {
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("pagesize", pagesize + "");
        map.put("isindex", "1");
        map.put("distype", goodsType);
        HttpxUtils.Get(this, HttpPath.PINGO_MORE_GOODS, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取顶部goods = " + result);
                        final GoodsListTop topGoods = GsonUtil.gsonIntance().gsonToBean(result, GoodsListTop.class);
                        topAdapter = new PinGoGoodsTopAdapter(PinGoGoodsActivity.this, topGoods.getData().getList());
                        topAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(PinGoGoodsActivity.this, PinGoDetailsActivity.class);
                                intent.putExtra("gid", topGoods.getData().getList().get(position).getId());
                                startActivity(intent);
                            }
                        });
                        topLecyclerView.setAdapter(topAdapter);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("获取顶部goods = 失败" + ex.getMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    /**
     * 拼go获取底部更多商品
     */
    public void getListGoods() {
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("pagesize", pagesize + "");
        map.put("distype", goodsType);
        map.put("istm", istm);
        map.put("isms", isms);
        Log.d("拼go获取底部更多商品", "" + map.toString());
        HttpxUtils.Get(this, HttpPath.PINGO_MORE_GOODS, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("拼go获取底部更多商品 = " + result);

                        PinGoIndexMoreGoods indexMoreGoods = GsonUtil.gsonIntance().gsonToBean(result, PinGoIndexMoreGoods.class);
                        //周五秒杀
                        if (isms.equals("1")) {
                            pinGoListGoodsAdapter.setMs(1);
                            updateUI(indexMoreGoods);
                        }

                        pinGoListGoodsAdapter.addAll(indexMoreGoods.getData().getList());
                        lRecyclerViewGoodsAdapter.setOnItemClickListener(new com.github.jdsjlzx.interfaces.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(PinGoGoodsActivity.this, PinGoDetailsActivity.class);
                                intent.putExtra("gid", pinGoListGoodsAdapter.getDataList().get(position).getId());
                                startActivity(intent);
                            }
                        });
                        goodsListView.refreshComplete(pagesize);
                        if (indexMoreGoods.getData().getList() == null || indexMoreGoods.getData().getList().size() < pagesize)
                            goodsListView.setNoMore(true);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("拼go获取底部更多商品 = 失败" + ex.getMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    /**
     * 周五秒杀更新
     * @param goods
     */
    public void updateUI(PinGoIndexMoreGoods goods) {
        String status = goods.getData().getStatus();
        if (status.equals("0")) {//非当前天
            pingoMsTopTv.setText("未开始");
            pingoMsTopTimeTv.setText("开始时间:" + AppUtil.getDateToString("yyyy-MM-dd HH:mm", Long.parseLong(goods.getData().getStstr()) * 1000));
        } else if (status.equals("1")) {//未开始
            pingoMsTopTv.setText("未开始");
            pingoMsTopTimeTv.setText("开始时间:" + AppUtil.getDateToString("yyyy-MM-dd HH:mm", Long.parseLong(goods.getData().getStstr()) * 1000));
        } else if (status.equals("2")) {//进行中
            pingoMsTopTv.setText("进行中");
            pingoMsTopTv.setTextColor(getResources().getColor(R.color.red_normal));
            pingoMsTopTimeTv.setText(AppUtil.getDateToString("yyyy-HH:mm:ss", Long.parseLong(goods.getData().getStstr()) * 1000)
                    + "-" + AppUtil.getDateToString("yyyy-HH:mm:ss", Long.parseLong(goods.getData().getEtstr()) * 1000));
        } else if (status.equals("3")) {//已结束
            pingoMsTopTv.setText("已结束");
            pingoMsTopTimeTv.setText("结束时间:" + AppUtil.getDateToString("yyyy-MM-dd HH:mm", Long.parseLong(goods.getData().getEtstr()) * 1000));
        }
    }
}
