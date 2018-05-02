package com.dq.huibao.ui.pingo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.pingo.PinGoLogInfoGoodsAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.pingo.PinGoLogInfoB;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;

import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 拼go记录详情
 * Created by d on 2018/5/2.
 */

public class PinGoLogInfoActivity extends BaseActivity {
    @Bind(R.id.pingo_log_info_diqu)
    TextView pingoLogInfoDiqu;
    @Bind(R.id.pingo_log_info_allprice)
    TextView pingoLogInfoAllprice;
    @Bind(R.id.pingo_log_info_pay_status)
    TextView pingoLogInfoPayStatus;
    @Bind(R.id.pingo_log_info_type)
    TextView pingoLogInfoType;
    @Bind(R.id.pingo_log_info_stauts)
    TextView pingoLogInfoStauts;
    @Bind(R.id.pingo_log_info_tips)
    TextView pingoLogInfoTips;
    @Bind(R.id.pingo_log_info_time)
    TextView pingoLogInfoTime;
    @Bind(R.id.pingo_log_info_nowpeople)
    TextView pingoLogInfoNowpeople;
    @Bind(R.id.pingo_log_info_maxpeople)
    TextView pingoLogInfoMaxpeople;
    @Bind(R.id.pingo_log_info_list)
    ListView pingoLogInfoList;


    private String uid = "", logid = "";
    PinGoLogInfoGoodsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingo_log_info);
        ButterKnife.bind(this);

        uid = getIntent().getStringExtra("uid");
        logid = getIntent().getStringExtra("logid");

        setTitleName("详情");
        getLogInfo();

    }

    /**
     * 获取拼go记录详情
     */
    public void getLogInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("mid", uid);
        map.put("logid", logid);
        HttpxUtils.Get(this, HttpPath.PINGO_LOG_INFO, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("获取拼go记录详情", "获取拼go记录详情 = " + result);

                PinGoLogInfoB logInfoB = GsonUtil.gsonIntance().gsonToBean(result, PinGoLogInfoB.class);
                updateUI(logInfoB);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("获取拼go记录详情", "获取拼go记录详情 失败= " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void updateUI(PinGoLogInfoB logInfoB) {

        pingoLogInfoDiqu.setText(logInfoB.getData().getRegname());
        pingoLogInfoAllprice.setText("￥" + logInfoB.getData().getPrice());
        pingoLogInfoPayStatus.setText(logInfoB.getData().getPaystatus().equals("1") ? "已支付" : "未支付");
        pingoLogInfoType.setText(logInfoB.getData().getDistype().equals("jian") ? "立减" : "折扣");
        pingoLogInfoStauts.setText(getStatus(logInfoB.getData().getTuan().getStatus()));
        pingoLogInfoTips.setText("备注:" + logInfoB.getData().getTips());
        pingoLogInfoTime.setText(AppUtil.getDateToString("yyyy-MM-dd HH:mm:ss", Long.parseLong(logInfoB.getData().getAddtime()) * 1000));
        pingoLogInfoNowpeople.setText("当前" + logInfoB.getData().getTuan().getNowcount() + "人");
        pingoLogInfoMaxpeople.setText("限" + logInfoB.getData().getTuan().getMaxcount() + "人");

        adapter = new PinGoLogInfoGoodsAdapter(this,logInfoB.getData().getGoodslist());
        pingoLogInfoList.setAdapter(adapter);
    }
    public String getStatus(String str){
        switch (str){
            case "0":
                return "开团";
            case "1":
                return "满团";
            case "2":
                return "未满";
            case "3":
                return "结束";
        }
        return "完成";
    }
}
