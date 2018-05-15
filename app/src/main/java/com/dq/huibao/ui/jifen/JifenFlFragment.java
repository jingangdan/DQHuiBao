package com.dq.huibao.ui.jifen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.jifen.JiFenFuLiGoodsAdapter;
import com.dq.huibao.adapter.jifen.JiFenFuLiTypeAdapter;
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

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 积分福利--兑换商品
 * Created by d on 2018/4/2.
 */

public class JifenFlFragment extends BaseFragment {
    View view = null;
    @Bind(R.id.list_jifen_fuli)
    LRecyclerView jifenFuliListView;
    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String uid = "", phone = "", token = "";
    private int page = 1,pagesieze = 20;
    //
    JiFenFuLiType jiFenFuLiType;
    View headerView;
    //
    JiFenFuLiGoodsAdapter jiFenFuLiGoodsAdapter;
    LRecyclerViewAdapter lRecyclerViewGoodsAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jifen_fuli, null);
        ButterKnife.bind(this, view);

        jiFenFuLiGoodsAdapter = new JiFenFuLiGoodsAdapter(getActivity());
        lRecyclerViewGoodsAdapter = new LRecyclerViewAdapter(jiFenFuLiGoodsAdapter);
        jifenFuliListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        jifenFuliListView.setAdapter(lRecyclerViewGoodsAdapter);
        lRecyclerViewGoodsAdapter.setOnItemClickListener(new com.github.jdsjlzx.interfaces.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),JiFenGoodDetailActivity.class);
                intent.putExtra("goodid",jiFenFuLiGoodsAdapter.getDataList().get(position).getId());
                intent.putExtra("phone",phone);
                intent.putExtra("token",token);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }
        });
        jifenFuliListView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
//                getGoods();
            }
        });
        jifenFuliListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                jiFenFuLiGoodsAdapter.clear();
                jiFenFuLiType.getData().clear();
                jifenFuliListView.setNoMore(false);
                page = 1;
                getListData();
            }
        });

        addHeadView();
        getListData();
        return view;
    }
    //
    JiFenFuLiTypeAdapter typeAdapter;
    RecyclerView typeLecyclerView;
    private void addHeadView() {
        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_jifen_fuli,null);
        typeLecyclerView = headerView.findViewById(R.id.list_jifen_fuli_type);
        typeLecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        lRecyclerViewGoodsAdapter.addHeaderView(headerView);

    }

    public static JifenFlFragment newInstance(String uid, String phone, String token) {

        Bundle args = new Bundle();
        args.putString("uid", uid);
        args.putString("phone", phone);
        args.putString("token", token);
        JifenFlFragment fragment = new JifenFlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            uid = getArguments().getString("uid");
            phone = getArguments().getString("phone");
            token = getArguments().getString("token");
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    /**
     * 获取积分商品分类
     */
    public void getListData() {
        HttpxUtils.Get(getActivity(),HttpPath.JIFEN_FULI_TYPE, null,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        jiFenFuLiType = GsonUtil.gsonIntance().gsonToBean(result, JiFenFuLiType.class);
                        System.out.println("获取积分兑换商品分类 = " + jiFenFuLiType.getData().toString());
                        typeAdapter = new JiFenFuLiTypeAdapter(getActivity(),jiFenFuLiType.getData());
                        typeAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                getGoods(jiFenFuLiType.getData().get(position).getId());
                            }
                        });
                        typeLecyclerView.setAdapter(typeAdapter);
                        getGoods(jiFenFuLiType.getData().get(0).getId());
                        page = 1;
                        jiFenFuLiGoodsAdapter.clear();
                        jifenFuliListView.setNoMore(true);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("获取积分兑换商品分类 = 失败" + ex.getMessage());
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
    public void getGoods(String id){
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        HttpxUtils.Get(getActivity(),HttpPath.JIFEN_FULI_GOODS, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        JiFenFuLiGoods goods = GsonUtil.gsonIntance().gsonToBean(result, JiFenFuLiGoods.class);
                        System.out.println("获取积分兑换商品 = " + goods.getData().toString());
                        jiFenFuLiGoodsAdapter.addAll(goods.getData());
                        jifenFuliListView.refreshComplete(pagesieze);
                        if (goods.getData() == null || goods.getData().size() < pagesieze){
                            jifenFuliListView.setNoMore(true);
                        }
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
