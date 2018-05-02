package com.dq.huibao.ui.tixian;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.memcen.RechargeActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.SPUserInfo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 余额首页
 * Created by d on 2018/4/28.
 */

public class BalanceActivity extends AppCompatActivity {
    @Bind(R.id.balance_number)
    TextView tixianBalanceNumber;
    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;

    private String phone = "", token = "", uid = "";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian);
        ButterKnife.bind(this);

        String balance = getIntent().getStringExtra("balance");

        tixianBalanceNumber.setText(balance == null ? "0.00" : balance);

        isLogin();
        titleTvTitle.setText("余额");
        titleTvRight.setText("领券");
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
                intent.putExtra("phone", phone);
                intent.putExtra("token", token);
                startActivityForResult(intent, CodeUtils.MEMBER);
                break;
            case R.id.balance_tixian://提现

                break;
            case R.id.balance_account://管理

                break;
            case R.id.title_tv_right://记录

                break;
        }
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
            }
        }
    }
}
