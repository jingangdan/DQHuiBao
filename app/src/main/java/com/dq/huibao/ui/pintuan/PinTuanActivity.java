package com.dq.huibao.ui.pintuan;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.dq.huibao.adapter.pintuan.PinTuanIndexAdapter;
import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.pintuan.PinTuanIndex;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
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
 * 拼团首页
 * Created by d on 2018/4/11.
 */

public class PinTuanActivity extends BaseActivity {
    @Bind(R.id.list_pintuan)
    LRecyclerView lRecyclerView;
    //
    PinTuanIndex pinTuanIndex;
    PinTuanIndexAdapter listAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    //
    private int page = 1, pageSize = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintuan);
        ButterKnife.bind(this);
        setTitleName("拼团");
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                listAdapter.clear();
                page = 1;
                getListData();
            }
        });
        lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page ++;
                getListData();
            }
        });
        //
        listAdapter = new PinTuanIndexAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(listAdapter);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        //
        getListData();
    }

    /**
     * 获取拼团列表
     */
    public void getListData(){
        Map<String, String> map = new HashMap<>();
        map.put("page", "" + page);
        map.put("pagesize", "" + pageSize);
        HttpxUtils.Get(this, HttpPath.PINTUAN_INDEX, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取拼团信息 = " + result);
                        pinTuanIndex = GsonUtil.gsonIntance().gsonToBean(result, PinTuanIndex.class);
                        listAdapter.addAll(pinTuanIndex.getData().getList());
                        lRecyclerView.refreshComplete(pageSize);
                        if (pinTuanIndex.getData().getList().size() == 0){
                            lRecyclerView.setNoMore(true);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("获取拼团信息 = 失败" + ex.getMessage());
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
