package com.dq.huibao.ui.pingo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.dq.huibao.R;
import com.dq.huibao.adapter.pingo.PinGoGoodsAdapter;
import com.dq.huibao.adapter.pingo.PinGoLogsAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.pingo.PinGoLogsB;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.SPUserInfo;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
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
 *
 * 拼go记录-非订单
 * Created by d on 2018/5/2.
 */

public class PinGoLogsActivity extends BaseActivity{
    @Bind(R.id.lrecyclerView)
    LRecyclerView lrecyclerView;
    //
    PinGoLogsAdapter logsAdapter;
    LRecyclerViewAdapter lRecyclerViewGoodsAdapter;
    private int page = 1, pagesize = 10;
    private String phone = "", token = "", uid = "";
    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lrlist_layout);
        ButterKnife.bind(this);
        //
        logsAdapter = new PinGoLogsAdapter(this);
        lRecyclerViewGoodsAdapter = new LRecyclerViewAdapter(logsAdapter);
        lRecyclerViewGoodsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(PinGoLogsActivity.this,PinGoLogInfoActivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("logid",logsAdapter.getDataList().get(position).getId());
                startActivity(intent);
            }
        });
        lrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lrecyclerView.setAdapter(lRecyclerViewGoodsAdapter);

        lrecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                getLogs();
            }
        });
        lrecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                logsAdapter.clear();
                lrecyclerView.setNoMore(false);
                page = 1;
                getLogs();
            }
        });
        setTitleName("拼go记录");
        isLogin();
    }
    /*
          * 判断登录状态
          *  */
    @SuppressLint("WrongConstant")
    public void isLogin() {
        SPUserInfo spUserInfo = new SPUserInfo(this.getApplication());

        if (spUserInfo.getLogin().equals("1")) {

            if (!(spUserInfo.getLoginReturn().equals(""))) {
                Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
                phone = login.getData().getPhone();
                token = login.getData().getToken();
                uid = login.getData().getUid();
                getLogs();
            }
        }
    }
    /**
     * 获取拼go记录列表
     */
    public void getLogs(){
        Map<String,String> map = new HashMap<>();
        map.put("mid",uid);
        map.put("page",page + "");
        map.put("pagesize",pagesize + "");
        HttpxUtils.Get(this, HttpPath.PINGO_LOGS, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("获取拼go记录列表","获取拼go记录列表 = " + result);

                PinGoLogsB logsB = GsonUtil.gsonIntance().gsonToBean(result, PinGoLogsB.class);
                logsAdapter.addAll(logsB.getData());
                lrecyclerView.refreshComplete(pagesize);
                if (logsB.getData().size() < pagesize){
                    lrecyclerView.setNoMore(true);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("获取拼go记录列表","获取拼go记录列表 失败= " + ex.toString());
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
