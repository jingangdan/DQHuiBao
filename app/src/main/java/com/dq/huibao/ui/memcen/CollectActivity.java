package com.dq.huibao.ui.memcen;

import android.annotation.SuppressLint;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.CollectAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.memcen.Collect;
import com.dq.huibao.ui.GoodsDetailsActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.MD5Util;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：我的收藏
 * Created by jingang on 2017/11/1.
 */

public class CollectActivity extends BaseActivity implements CollectAdapter.CollectInterface {
    @Bind(R.id.rv_collect)
    RecyclerView rvCollect;

    /*接收页面传值*/
    private Intent intent;

    /*借口地址*/
    private String MD5_PATH = "", PATH = "";
    private RequestParams params = null;
    private int page = 1;

    private CollectAdapter collectAdapter;
    private List<Collect.DataBean.ListBean> collectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);

        collectAdapter = new CollectAdapter(this, collectList);
        rvCollect.setLayoutManager(new LinearLayoutManager(this));
        rvCollect.setAdapter(collectAdapter);

        collectAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(CollectActivity.this, GoodsDetailsActivity.class);
                intent.putExtra("gid", collectList.get(position).getId());
                startActivity(intent);
            }
        });

        collectAdapter.setCollectInterface(this);

        intent = getIntent();
        getRecordList("collect", page);

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("我的收藏");
    }

    /**
     * 获取收藏列表
     *
     * @param type  类型--- browse  浏览历史  collect收藏商品   collect_shop收藏店铺（暂无）
     * @param page
     */
    public void getRecordList(String type, int page) {
        MD5_PATH = "page=" + page + "&phone=" + phoneBase + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + tokenBase + "&type=" + type;
        PATH = HttpPath.MEM_RECORDLIST + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);

        HttpxUtils.Get(this,PATH, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Collect collect = GsonUtil.gsonIntance().gsonToBean(result, Collect.class);

                collectList.clear();
                collectList.addAll(collect.getData().getList());
                collectAdapter.notifyDataSetChanged();
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
    public void doD(String type, String id, int position) {
        dialog(type, id, position);
    }

    /*弹出框*/
    protected void dialog(final String type, final String id, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认取消收藏吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                setDelRecord(type, id, position);

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }

    /**
     * 取消收藏
     *
     * @param type  收藏类型--- collect收藏商品   collect_shop收藏店铺（暂无）
     * @param id    收藏的商品id 或者店铺id
     */
    public void setDelRecord(String type, String id, final int position) {
        MD5_PATH = "id=" + id + "&phone=" + phoneBase + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + tokenBase + "&type=" + type;
        PATH = HttpPath.MEM_DELRECORD + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);

        HttpxUtils.Post(this,PATH, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                if (addrReturn.getStatus() == 1) {
                    toast("" + addrReturn.getData());
                    collectList.remove(position);
                    collectAdapter.notifyDataSetChanged();

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
