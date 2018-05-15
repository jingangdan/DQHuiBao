package com.dq.huibao.fragment.memcen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.coupons.MyCouponsAdapter;
import com.dq.huibao.adapter.memcen.CouponsAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.bean.coupons.MyCouponsB;
import com.dq.huibao.bean.memcen.Coupons;
import com.dq.huibao.ui.coupons.CouponsDetailActivity;
import com.dq.huibao.ui.coupons.CouponsListActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：优惠券（已过期）
 * Created by jingang on 2017/11/1.
 */

public class FMCouponsPast extends BaseFragment {
    @Bind(R.id.lin_coupons_null)
    LinearLayout linCouponsNull;

    /*领取优惠券*/
    @Bind(R.id.but_tablayout)
    Button butTablayout;

    private View view;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String unionid = "",uid = "";

    @Bind(R.id.rv_order_all)
    LRecyclerView rvOrderAll;
    private MyCouponsAdapter couponsAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<MyCouponsB.DataBean> couponsList = new ArrayList<>();

    /*页面跳转*/
    private Intent intent;

    int page = 1,pagesize = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_coupons, null);
        ButterKnife.bind(this, view);

        couponsAdapter = new MyCouponsAdapter(getActivity());
        rvOrderAll.setLayoutManager(new LinearLayoutManager(getActivity()));
        lRecyclerViewAdapter = new LRecyclerViewAdapter(couponsAdapter);
        rvOrderAll.setAdapter(lRecyclerViewAdapter);

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
        rvOrderAll.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                couponsAdapter.clear();
                rvOrderAll.setNoMore(false);
                getCoupons();
            }
        });
        rvOrderAll.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page ++;
                getCoupons();
            }
        });

        initData();
        return view;
    }

    public static FMCouponsPast newInstance(String coupons) {
        Bundle bundle = new Bundle();
        bundle.putString("coupons", coupons);
        FMCouponsPast fragment = new FMCouponsPast();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            System.out.println("in 3");
            if (!unionid.equals("")) {
                couponsAdapter.clear();
                getCoupons();
            }


        } else {
            System.out.println("move 3");

        }

    }

    public void initData() {
        spUserInfo = new SPUserInfo(getActivity().getApplication());

        if (!(spUserInfo.getLoginReturn().equals(""))) {
            LoginBean loginBean = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), LoginBean.class);
            unionid = loginBean.getData().getUnionid() + "";
            uid = loginBean.getData().getUid();
            getCoupons();
        } else {
            toast("登录状态出错，请重新登录");
        }
    }

    /**
     * 优惠券列表
     */
    public void getCoupons() {
        Map<String,String> map = new HashMap<>();
        map.put("mid",uid);
        map.put("page",page + "");
        map.put("pagesize",pagesize + "");
        map.put("type","2");
        System.out.println("优惠券 未使用 = " + map.toString());
        HttpxUtils.Get(getActivity(),HttpPath.COUPONS_MY, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("优惠券 未使用= " + result);
                MyCouponsB coupons = GsonUtil.gsonIntance().gsonToBean(result, MyCouponsB.class);
                rvOrderAll.refreshComplete(pagesize);
                if (coupons.getData().size() < pagesize || coupons.getData().size() == 0){
                    rvOrderAll.setNoMore(true);
                }
                if (coupons.getData().size() > 0) {
                    linCouponsNull.setVisibility(View.GONE);
                    couponsList.clear();
                    couponsAdapter.addAll(coupons.getData());
                    couponsAdapter.notifyDataSetChanged();

                } else {
                    linCouponsNull.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                linCouponsNull.setVisibility(View.VISIBLE);
                System.out.println("优惠券 未使用=失败 " + ex.toString());
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

    @OnClick(R.id.but_tablayout)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_tablayout:
                //领取优惠券
                intent = new Intent(getActivity(), CouponsListActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
