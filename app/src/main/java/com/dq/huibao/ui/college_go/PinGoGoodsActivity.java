package com.dq.huibao.ui.college_go;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.college_go.PinGoGoodsAdapter;
import com.dq.huibao.adapter.college_go.PinGoGoodsTopAdapter;
import com.dq.huibao.adapter.jifen.JiFenFuLiGoodsAdapter;
import com.dq.huibao.adapter.jifen.JiFenFuLiTypeAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.jifen.JiFenFuLiGoods;
import com.dq.huibao.bean.jifen.JiFenFuLiType;
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
    @Bind(R.id.list_jifen_fuli)
    LRecyclerView jifenFuliListView;
    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String uid = "", phone = "", token = "";
    private int page = 1,pagesieze = 20;
    //
    View headerView;
    //
    PinGoGoodsAdapter pinGoListGoodsAdapter;
    LRecyclerViewAdapter lRecyclerViewGoodsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_jifen_fuli);
        ButterKnife.bind(this, this);

        pinGoListGoodsAdapter = new PinGoGoodsAdapter(this);
        lRecyclerViewGoodsAdapter = new LRecyclerViewAdapter(pinGoListGoodsAdapter);
        jifenFuliListView.setLayoutManager(new LinearLayoutManager(this));
        jifenFuliListView.setAdapter(lRecyclerViewGoodsAdapter);

        jifenFuliListView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

            }
        });
        jifenFuliListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                pinGoListGoodsAdapter.clear();
                jifenFuliListView.setNoMore(false);
                page = 1;
                getListData();
            }
        });

        addHeadView();
        getListData();
        getGoods();
    }

    //
    PinGoGoodsTopAdapter topAdapter;
    RecyclerView typeLecyclerView;
    private void addHeadView() {
        headerView = LayoutInflater.from(this).inflate(R.layout.header_pingo_goods_layout,null);
        typeLecyclerView = headerView.findViewById(R.id.header_pingo_goodsTop_list);
        typeLecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        lRecyclerViewGoodsAdapter.addHeaderView(headerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    /**
     * 获取积分商品分类
     */
    public void getListData() {
        Map<String, String> map = new HashMap<>();
        map.put("id", uid);
        HttpxUtils.Get(this, HttpPath.JIFEN_FULI_TYPE, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
//                        jiFenFuLiType = GsonUtil.gsonIntance().gsonToBean(result, JiFenFuLiType.class);
//                        System.out.println("同学拼go顶部数据获取 = " + jiFenFuLiType.getData().toString());
                        topAdapter = new PinGoGoodsTopAdapter(PinGoGoodsActivity.this,new ArrayList<String>(Arrays.asList(new String[6])));
                        topAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                            }
                        });
                        typeLecyclerView.setAdapter(topAdapter);

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("同学拼go顶部数据获取 = 失败" + ex.getMessage());
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
     * 获取商品列表
     */
    public void getGoods(){
        Map<String, String> map = new HashMap<>();
        map.put("id", "2");
        HttpxUtils.Get(this, HttpPath.JIFEN_FULI_GOODS, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        JiFenFuLiGoods goods = GsonUtil.gsonIntance().gsonToBean(result, JiFenFuLiGoods.class);
                        System.out.println("获取积分兑换商品 = " + goods.getData().toString());
                        pinGoListGoodsAdapter.addAll(new ArrayList<String>(Arrays.asList(new String[10])));
                        jifenFuliListView.refreshComplete(pagesieze);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("获取积分兑换商品 = 失败" + ex.getMessage());
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
