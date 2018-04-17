package com.dq.huibao.ui.pingo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.jifen.JiFenFuLiTypeAdapter;
import com.dq.huibao.adapter.pingo.PinGoGoodsAdapter;
import com.dq.huibao.adapter.pingo.PinGoGoodsTopAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.jifen.JiFenFuLiGoods;
import com.dq.huibao.bean.pingo.GoodsListTop;
import com.dq.huibao.bean.pingo.PinGoIndexMoreGoods;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.SPUserInfo;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.Arrays;
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
    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String uid = "", phone = "", token = "";
    private int page = 1,pagesize = 20;
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
                page ++;
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

        addHeadView();
        getTopGoods();
        getListGoods();
    }

    //
    PinGoGoodsTopAdapter topAdapter;
    RecyclerView topLecyclerView;
    private void addHeadView() {
        headerView = LayoutInflater.from(this).inflate(R.layout.header_pingo_goods_layout,null);
        topLecyclerView = headerView.findViewById(R.id.header_pingo_goodsTop_list);
        topLecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        lRecyclerViewGoodsAdapter.addHeaderView(headerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    /**
     * 获取顶部goods
     */
    public void getTopGoods(){
        Map<String,String> map = new HashMap<>();
        map.put("page",page + "");
        map.put("pagesize",pagesize + "");
        map.put("isindex", "1");
        map.put("distype", goodsType);
        HttpxUtils.Get(this,HttpPath.PINGO_MORE_GOODS, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取顶部goods = " + result);
                        final GoodsListTop topGoods = GsonUtil.gsonIntance().gsonToBean(result, GoodsListTop.class);
                        topAdapter = new PinGoGoodsTopAdapter(PinGoGoodsActivity.this,topGoods.getData().getList());
                        topAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(PinGoGoodsActivity.this,PinGoDetailsActivity.class);
                                intent.putExtra("id",topGoods.getData().getList().get(position).getId());
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
     * 获取底部更多商品
     */
    public void getListGoods(){
        Map<String,String> map = new HashMap<>();
        map.put("page",page + "");
        map.put("pagesize",pagesize + "");
        map.put("distype", goodsType);
        HttpxUtils.Get(this,HttpPath.PINGO_MORE_GOODS, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取首页底部更多商品信息 = " + result);

                        PinGoIndexMoreGoods indexMoreGoods = GsonUtil.gsonIntance().gsonToBean(result, PinGoIndexMoreGoods.class);
                        pinGoListGoodsAdapter.addAll(indexMoreGoods.getData().getList());
                        lRecyclerViewGoodsAdapter.setOnItemClickListener(new com.github.jdsjlzx.interfaces.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(PinGoGoodsActivity.this,PinGoDetailsActivity.class);
                                intent.putExtra("id",pinGoListGoodsAdapter.getDataList().get(position).getId());
                                startActivity(intent);
                            }
                        });
                        goodsListView.refreshComplete(pagesize);
                        if (indexMoreGoods.getData().getList().size() == 0)
                            goodsListView.setNoMore(true);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("获取首页底部更多商品信息 = 失败" + ex.getMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }
}
