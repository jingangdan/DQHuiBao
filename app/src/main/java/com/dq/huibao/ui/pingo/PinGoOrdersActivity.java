package com.dq.huibao.ui.pingo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.pingo.PinGoGoodsAdapter;
import com.dq.huibao.adapter.pingo.PinGoGoodsTopAdapter;
import com.dq.huibao.base.BaseActivity;
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

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 同学拼go -订单列表
 * Created by d on 2018/4/2.
 */

public class PinGoOrdersActivity extends BaseActivity {
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
                getListOrder();
            }
        });
        goodsListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                pinGoListGoodsAdapter.clear();
                goodsListView.setNoMore(false);
                page = 1;
                getListOrder();
            }
        });

        getListOrder();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    /**
     * 获取订单列表
     */
    public void getListOrder(){
        Map<String,String> map = new HashMap<>();
        map.put("mid",uid);
        map.put("page",page + "");
        map.put("pagesize",pagesize + "");
        Log.d("我的拼go订单",""+map.toString());
        HttpxUtils.Get(this,HttpPath.PINGO_MYORDER, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("我的拼go订单 = " + result);

                        PinGoIndexMoreGoods indexMoreGoods = GsonUtil.gsonIntance().gsonToBean(result, PinGoIndexMoreGoods.class);
                        pinGoListGoodsAdapter.addAll(indexMoreGoods.getData().getList());
                        lRecyclerViewGoodsAdapter.setOnItemClickListener(new com.github.jdsjlzx.interfaces.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(PinGoOrdersActivity.this,PinGoDetailsActivity.class);
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
                        System.out.println("我的拼go订单 = 失败" + ex.getMessage());
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
