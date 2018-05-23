package com.dq.huibao.ui.tixian;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.dq.huibao.R;
import com.dq.huibao.adapter.pingo.PinGoLogsAdapter;
import com.dq.huibao.adapter.tixian.TiXianLogsAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.pingo.PinGoLogsB;
import com.dq.huibao.bean.tixian.TiXianLogsB;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.ShowUtils;
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
 * Created by d on 2018/4/29.
 */

public class BalanceLogsActivity extends BaseActivity {
    @Bind(R.id.lrecyclerView)
    LRecyclerView lrecyclerView;

    TiXianLogsAdapter logsAdapter;
    LRecyclerViewAdapter lRecyclerViewGoodsAdapter;
    private int page = 1, pagesize = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lrlist_layout);
        ButterKnife.bind(this);
        logsAdapter = new TiXianLogsAdapter(this) {
            @Override
            public void cancle(final String id) {
                ShowUtils.showDialog(BalanceLogsActivity.this, "提示", "是否取消","确定", new ShowUtils.OnDialogListener() {
                    @Override
                    public void confirm() {
                        quxiao(id);
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };
        lRecyclerViewGoodsAdapter = new LRecyclerViewAdapter(logsAdapter);
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
                refresh();
            }
        });

        setTitleName("提现记录");
        getLogs();
    }

    public void refresh(){
        logsAdapter.clear();
        lrecyclerView.setNoMore(false);
        page = 1;
        getLogs();
    }
    /**
     * 获取余额使用记录
     */
    public void getLogs(){
        Map<String,String> map = new HashMap<>();
        map.put("uid",uidBase);
        map.put("page","" + page);
        map.put("pagesize","" + pagesize);
        HttpxUtils.Get(this,HttpPath.TIXIAN_LOGS, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                TiXianLogsB logsB = GsonUtil.gsonIntance().gsonToBean(result, TiXianLogsB.class);
                logsAdapter.addAll(logsB.getData());
                lrecyclerView.refreshComplete(pagesize);
                if (logsB.getData().size() < pagesize){
                    lrecyclerView.setNoMore(true);
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
    /**
     * 取消提现申请
     */
    public void quxiao(String id){
        Map<String,String> map = new HashMap<>();
        map.put("accountid",id);
        HttpxUtils.Get(this,HttpPath.TIXIAN_CANCEL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                if (addrReturn.getStatus() == 1){
                    toast("取消成功");
                    refresh();
                }else {
                    toast(addrReturn.getData());
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
}
