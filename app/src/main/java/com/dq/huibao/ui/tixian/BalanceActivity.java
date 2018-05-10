package com.dq.huibao.ui.tixian;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.memcen.RechargeActivity;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.tixian.TiXianIndexB;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;

import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 余额首页
 * Created by d on 2018/4/28.
 */

public class BalanceActivity extends BaseActivity {
    @Bind(R.id.balance_number)
    TextView tixianBalanceNumber;
    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);

        titleTvTitle.setText("余额");
        titleTvRight.setText("记录");
        getData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }

    @OnClick({R.id.title_iv_back, R.id.balance_chonghzi, R.id.balance_tixian,
            R.id.balance_account, R.id.title_tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.balance_chonghzi://充值
                intent = new Intent(this, RechargeActivity.class);
                intent.putExtra("phone", phoneBase);
                intent.putExtra("token", tokenBase);
                startActivityForResult(intent, CodeUtils.MEMBER);
                break;
            case R.id.balance_tixian://提现
                intent = new Intent(this, TiXianActivity.class);
                startActivity(intent);
                break;
            case R.id.balance_account://管理
                intent = new Intent(this, TixianAccountActivity.class);
                startActivity(intent);
                break;
            case R.id.title_tv_right://记录
                intent = new Intent(this, BalanceLogsActivity.class);
                startActivity(intent);
                break;
        }
    }
    /**
     * 获取提现页面数据
     */
    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uidBase);
        HttpxUtils.Get(this, HttpPath.TIXIAN_INDEX, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("获取提现页面数据", "获取提现页面数据 = " + result);

                TiXianIndexB tiXianIndexB = GsonUtil.gsonIntance().gsonToBean(result, TiXianIndexB.class);

                tixianBalanceNumber.setText(tiXianIndexB.getData().getUinfo().getBalance());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("获取提现页面数据", "获取提现页面数据 失败= " + ex.toString());
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
