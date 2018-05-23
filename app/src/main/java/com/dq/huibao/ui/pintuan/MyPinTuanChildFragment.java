package com.dq.huibao.ui.pintuan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.R;
import com.dq.huibao.adapter.pintuan.MyPinTuanLogsAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.pintuan.MyPinTuanLogs;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的拼团---记录
 * Created by d on 2018/4/2.
 */

public class MyPinTuanChildFragment extends BaseFragment {
    public static final String TYPE_1 = "0";//拼团中
    public static final String TYPE_2 = "1";//组团成功
    public static final String TYPE_3 = "2";//失败
    View view = null;/*本地轻量型缓存*/
    @Bind(R.id.lrecyclerView)
    LRecyclerView lRecyclerView;
    private String uid = "", phone = "", token = "",type = "";
    int page = 1,pagesize = 20;

    MyPinTuanLogsAdapter pinTuanLogsAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    /*当前选择的是否是兑换*/
    boolean isDuiHuan = true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lrlist_layout, null);
        ButterKnife.bind(this, view);

        pinTuanLogsAdapter = new MyPinTuanLogsAdapter(getActivity());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(pinTuanLogsAdapter);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        lRecyclerView.setPullRefreshEnabled(false);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(pinTuanLogsAdapter);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page ++;
                getListData();
            }
        });
        //
        getListData();
        return view;
    }

    public static MyPinTuanChildFragment newInstance(String uid, String phone, String token,String type) {

        Bundle args = new Bundle();
        args.putString("uid",uid);
        args.putString("phone",phone);
        args.putString("token",token);
        args.putString("type",type);
        MyPinTuanChildFragment fragment = new MyPinTuanChildFragment();
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
            type = getArguments().getString("type");
        }
    }

    @Override
    protected void lazyLoad() {

    }
    /**
     * 获取数据
     */
    public void getListData(){
        Map<String, String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("status",type);
        map.put("page",page+"");
        map.put("pagesize",pagesize+"");
        //type:score积分 balance现金
        //recharge充值 exchange兑换 consump消费
        HttpxUtils.Get(getActivity(),HttpPath.PINTUAN_TUAN_HISTORY, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        MyPinTuanLogs myPinTuanLogs = GsonUtil.gsonIntance().gsonToBean(result, MyPinTuanLogs.class);
                        pinTuanLogsAdapter.addAll(myPinTuanLogs.getData().getList());

                        if (myPinTuanLogs.getData() == null || myPinTuanLogs.getData().getList().size() < pagesize){
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
