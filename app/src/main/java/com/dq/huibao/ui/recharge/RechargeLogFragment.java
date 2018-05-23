package com.dq.huibao.ui.recharge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.R;
import com.dq.huibao.adapter.RechargeHfLogsAdapter;
import com.dq.huibao.adapter.coupons.MyCouponsAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.RechargeLogsB;
import com.dq.huibao.bean.coupons.MyCouponsB;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 话费充值记录
 * Created by d on 2018/4/2.
 */

public class RechargeLogFragment extends BaseFragment {
    View view = null;
    @Bind(R.id.list_jifen_fuli)
    LRecyclerView lRecyclerView;
    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String uid = "", phone = "", token = "";

    private RechargeHfLogsAdapter logsAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<MyCouponsB.DataBean> couponsList = new ArrayList<>();

    /*页面跳转*/
    private Intent intent;

    int page = 1,pagesize = 10;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jifen_fuli, null);
        ButterKnife.bind(this, view);

        logsAdapter = new RechargeHfLogsAdapter(getActivity());
        lRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        lRecyclerViewAdapter = new LRecyclerViewAdapter(logsAdapter);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);

        lRecyclerViewAdapter.setOnItemClickListener(new com.github.jdsjlzx.interfaces.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                intent = new Intent(getActivity(), CouponsDetailActivity.class);
//                intent.putExtra("couponsid", couponsList.get(position).getCouponid());
//                intent.putExtra("index", "0");//0代表用户的优惠券
//                startActivity(intent);
            }
        });

        //
        lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                logsAdapter.clear();
                lRecyclerView.setNoMore(false);
                getDataList();
            }
        });
        lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page ++;
                getDataList();
            }
        });

        getDataList();
        return view;
    }

    public static RechargeLogFragment newInstance(String uid, String phone, String token) {

        Bundle args = new Bundle();
        args.putString("uid", uid);
        args.putString("phone", phone);
        args.putString("token", token);
        RechargeLogFragment fragment = new RechargeLogFragment();
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
    /**
     * 话费充值记录列表
     */
    public void getDataList() {
        Map<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("page",page + "");
        map.put("pagesize",pagesize + "");

        HttpxUtils.Get(getActivity(),HttpPath.RECHARGE_ORDER_LOG, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                RechargeLogsB rechargeLogsB = GsonUtil.gsonIntance().gsonToBean(result, RechargeLogsB.class);
                lRecyclerView.refreshComplete(pagesize);

                logsAdapter.addAll(rechargeLogsB.getData());

                if (rechargeLogsB.getData().size() < pagesize || rechargeLogsB.getData().size() == 0){
                    lRecyclerView.setNoMore(true);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
