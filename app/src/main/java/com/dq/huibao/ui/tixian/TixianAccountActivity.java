package com.dq.huibao.ui.tixian;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.tixian.TiXianAcountAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.tixian.TiXianIndexB;
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
 * 账号列表
 * Created by d on 2018/4/29.
 */

public class TixianAccountActivity extends BaseActivity {
    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;
    @Bind(R.id.listview_account)
    ListView listviewAccount;

    TiXianAcountAdapter acountAdapter;
    TiXianIndexB tiXianIndexB;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_account);
        ButterKnife.bind(this);

        listviewAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(TixianAccountActivity.this,TiXianAccountInfoActivity.class);
                intent.putExtra("accountid",tiXianIndexB.getData().getList().get(position).getId());
                startActivity(intent);
            }
        });
        titleTvTitle.setText("账号");
        titleTvRight.setText("添加");
        getData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }

    @OnClick({R.id.title_iv_back,R.id.title_tv_right})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_right:
                intent = new Intent(this,TiXianAccountInfoActivity.class);
                intent.putExtra("isAdd",true);
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
        HttpxUtils.Get(this,HttpPath.TIXIAN_INDEX, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("获取提现页面数据", "获取提现页面数据 = " + result);

                tiXianIndexB = GsonUtil.gsonIntance().gsonToBean(result, TiXianIndexB.class);
                acountAdapter = new TiXianAcountAdapter(TixianAccountActivity.this,tiXianIndexB.getData().getList());

                listviewAccount.setAdapter(acountAdapter);
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
