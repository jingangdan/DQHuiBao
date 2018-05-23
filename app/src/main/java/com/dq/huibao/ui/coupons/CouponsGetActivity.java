package com.dq.huibao.ui.coupons;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.dq.huibao.R;
import com.dq.huibao.adapter.coupons.CouponsGetAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.Addr;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.coupons.CouponsGetListB;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.jifen.JiFenGoodDetailActivity;
import com.dq.huibao.utils.CodeUtils;
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
 * Description：领券中心
 * Created by jingang on 2017/11/1.
 */

public class CouponsGetActivity extends BaseActivity {
    @Bind(R.id.coupons_list)
    LRecyclerView lRecyclerView;

    CouponsGetAdapter couponsGetAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;

    int page = 1,pagesize = 10;

    Intent intent = new Intent();
    //
    private String phone = "", token = "", uid = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons_get);
        ButterKnife.bind(this);
        setTitleName("领卷中心");


        couponsGetAdapter = new CouponsGetAdapter(this) {
            @Override
            public void getCoupon(String couponid) {
                isLogin(couponid);
            }
        };
        lRecyclerViewAdapter = new LRecyclerViewAdapter(couponsGetAdapter);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                getDataList();
            }
        });
        lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                couponsGetAdapter.clear();
                lRecyclerView.setNoMore(false);
                getDataList();
            }
        });

        getDataList();
    }

    /**
     * 获取优惠券数据
     *
     */
    public void getDataList(){
        Map<String,String> map = new HashMap<>();
        map.put("page",page + "");
        map.put("pagesize",pagesize + "");
        map.put("status","0");//status=优惠券状态 0有效 1无效 ----领卷中心使用
        HttpxUtils.Get(this,HttpPath.COUPONS_GET_LIST, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                CouponsGetListB getList = GsonUtil.gsonIntance().gsonToBean(result, CouponsGetListB.class);
                couponsGetAdapter.addAll(getList.getData());
                lRecyclerView.refreshComplete(pagesize);
                if (getList.getData().size() < pagesize){
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

    public void getCoupon(String couponid){
        Map<String,String> map = new HashMap<>();
        map.put("mid",uid);
        map.put("couponid",couponid);
        HttpxUtils.Get(this,HttpPath.COUPONS_GET_COUPONS, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                if (addrReturn.getStatus() == 1){
                    getDataList();
                }
                toast(addrReturn.getData());
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


    @SuppressLint("WrongConstant")
    public void isLogin(String id) {
        SPUserInfo spUserInfo = new SPUserInfo(getApplication());

        if (!(spUserInfo.getLoginReturn().equals(""))) {
            Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
            phone = login.getData().getPhone();
            token = login.getData().getToken();
            uid = login.getData().getUid();
            getCoupon(id);
        } else {
            dialog();
        }

    }
    /*弹出框*/
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定登录？");
        builder.setTitle("提示：未登录");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                intent = new Intent(CouponsGetActivity.this, LoginActivity.class);
                startActivityForResult(intent, CodeUtils.GDTAILD);
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
}
