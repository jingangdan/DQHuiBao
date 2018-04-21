package com.dq.huibao.ui.jifen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.dq.huibao.R;
import com.dq.huibao.adapter.jifen.JiFenLogsAdapter;
import com.dq.huibao.adapter.jifen.JiFenLogsUserAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.jifen.JiFenLogs;
import com.dq.huibao.bean.jifen.JiFenUserLogs;
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
 * 我的积分---使用记录
 * Created by d on 2018/4/2.
 */

public class MyJfChileSyFragment extends BaseFragment {
    View view = null;/*本地轻量型缓存*/
    @Bind(R.id.list_myjf_child)
    LRecyclerView lRecyclerView;
    private String uid = "", phone = "", token = "";
    int page = 1,pagesize = 20;

    JiFenLogsUserAdapter jiFenLogsAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    /*当前选择的是否是兑换*/
    boolean isDuiHuan = true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myjf_child, null);
        ButterKnife.bind(this, view);

        jiFenLogsAdapter = new JiFenLogsUserAdapter(getActivity());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(jiFenLogsAdapter);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        lRecyclerView.setPullRefreshEnabled(false);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(jiFenLogsAdapter);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page ++;
                getShiYongData();
            }
        });
        //
        getShiYongData();
        return view;
    }

    public static MyJfChileSyFragment newInstance(String uid, String phone, String token) {

        Bundle args = new Bundle();
        args.putString("uid",uid);
        args.putString("phone",phone);
        args.putString("token",token);
        MyJfChileSyFragment fragment = new MyJfChileSyFragment();
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
    /**
     * 积分使用记录
     */
    public void getShiYongData(){
        Map<String, String> map = new HashMap<>();
        map.put("mid",uid);
        map.put("page",page+"");
        map.put("pagesize",pagesize+"");
        //type:score积分 balance现金
        //recharge充值 exchange兑换 consump消费
        System.out.println("积分使用记录 = " + map.toString());
        HttpxUtils.Get(getActivity(),HttpPath.JIFEN_USERLOGS, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("积分使用记录 = " + result);
                        JiFenUserLogs jiFenUserLogs = GsonUtil.gsonIntance().gsonToBean(result, JiFenUserLogs.class);
                        jiFenLogsAdapter.addAll(jiFenUserLogs.getData());
                        //
                        if (jiFenUserLogs.getData() == null || jiFenUserLogs.getData().size() < pagesize){
                            lRecyclerView.setNoMore(true);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("积分使用记录 = 失败" + ex.toString());
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
