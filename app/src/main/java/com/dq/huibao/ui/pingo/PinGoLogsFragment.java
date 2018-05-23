package com.dq.huibao.ui.pingo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.R;
import com.dq.huibao.adapter.jifen.JiFenLogsUserAdapter;
import com.dq.huibao.adapter.pingo.PinGoLogsAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.jifen.JiFenUserLogs;
import com.dq.huibao.bean.pingo.PinGoLogsB;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.utils.DiaLogUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.MD5Util;
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
 * 我的拼go记录
 * Created by d on 2018/4/2.
 */

public class PinGoLogsFragment extends BaseFragment {
    View view;
    @Bind(R.id.lrecyclerView)
    LRecyclerView lrecyclerView;
    //
    PinGoLogsAdapter logsAdapter;
    LRecyclerViewAdapter lRecyclerViewGoodsAdapter;
    private int page = 1, pagesize = 10;
    private String typeTitle = "", uid = "",token = "",phone = "";
    Intent intent ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lrlist_layout, null);
        ButterKnife.bind(this, view);

        logsAdapter = new PinGoLogsAdapter(getActivity(), typeTitle) {
            @Override
            public void del(final String orderid) {
                ShowUtils.showDialog(getActivity(), "提示", "是否删除该订单","删除", new ShowUtils.OnDialogListener() {
                    @Override
                    public void confirm() {
                        delLogs(orderid);
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };
        lRecyclerViewGoodsAdapter = new LRecyclerViewAdapter(logsAdapter);
//        lRecyclerViewGoodsAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                intent = new Intent(PinGoLogsActivity.this,PinGoLogInfoActivity.class);
//                intent.putExtra("uid",uid);
//                intent.putExtra("logid",logsAdapter.getDataList().get(position).getId());
//                startActivity(intent);
//            }
//        });
        lrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                refreash();
            }
        });

        getLogs();
        return view;
    }

    public static PinGoLogsFragment newInstance(String uid,String phone,String token, String type) {

        Bundle args = new Bundle();
        args.putString("uid",uid);
        args.putString("phone",phone);
        args.putString("token",token);
        args.putString("typeTitle",type);
        PinGoLogsFragment fragment = new PinGoLogsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 刷新
     */
    public void refreash(){
        logsAdapter.clear();
        lrecyclerView.setNoMore(false);
        page = 1;
        getLogs();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            uid = getArguments().getString("uid");
            token = getArguments().getString("token");
            phone = getArguments().getString("phone");
            typeTitle = getArguments().getString("typeTitle");
        }
    }

    @Override
    protected void lazyLoad() {

    }
    /**
     * 获取拼go记录列表
     */
    public void getLogs(){
        Map<String,String> map = new HashMap<>();
        map.put("mid",uid);
        map.put("page",page + "");
        map.put("pagesize",pagesize + "");
        map.put("type", typeTitle);
        HttpxUtils.Get(getActivity(),HttpPath.PINGO_LOGS, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                PinGoLogsB logsB = GsonUtil.gsonIntance().gsonToBean(result, PinGoLogsB.class);
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
     * 删除拼go记录列表
     */
    public void delLogs(String orderid){
        String MD5_PATH = "id=" + orderid + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + "&type=del";

//        MD5_PATH = "id=" + id + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + "&type=" + type;
        String PATH = HttpPath.ORDER_EDIT + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);

        HttpxUtils.Post(getActivity(),PATH, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                if (addrReturn.getStatus() == 1) {
                    toast("" + addrReturn.getData());
                    refreash();
                } else {
                    toast("操作失败");
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("删除拼go记录列表","删除拼go记录列表 失败= " + ex.toString());
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
