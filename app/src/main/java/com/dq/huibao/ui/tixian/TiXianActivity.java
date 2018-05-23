package com.dq.huibao.ui.tixian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.pingo.PinGoQuYuAdapter;
import com.dq.huibao.adapter.tixian.TiXianAcountAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.tixian.TiXianIndexB;
import com.dq.huibao.ui.addr.AddAddressActivity;
import com.dq.huibao.ui.addr.AddrListActivity;
import com.dq.huibao.ui.jifen.JiFenGoodDetailActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现
 * Created by d on 2018/5/5.
 */

public class TiXianActivity extends BaseActivity {
    /**
     * 输入框小数的位数
     */
    private static final int DECIMAL_DIGITS = 2;

    @Bind(R.id.item_tixian_select_account_account)
    TextView itemTixianSelectAccountZccount;
    @Bind(R.id.item_tixian_select_account_type)
    TextView itemTixianSelectAccountType;
    @Bind(R.id.item_tixian_select_account_des)
    TextView itemTixianSelectAccountDes;
    @Bind(R.id.tixian_money)
    EditText tixianMoney;
    @Bind(R.id.tixian_balance)
    TextView tixianBalance;

    private double balance = 0.00;
    private TiXianIndexB.DataBean.ListBean account;
    private List<TiXianIndexB.DataBean.ListBean> accountList = new ArrayList<>();
    TiXianIndexB tiXianIndexB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian);
        ButterKnife.bind(this);

        //限制小数点位数
        //
        tixianMoney.addTextChangedListener(new TextWatcher() {
            boolean deleteLastChar;// 是否需要删除末尾

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    // 如果点后面有超过三位数值,则删掉最后一位
                    int length = s.length() - s.toString().lastIndexOf(".");
                    // 说明后面有三位数值
                    deleteLastChar = length >= 4;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null) {
                    return;
                }
                if (deleteLastChar) {
                    // 设置新的截取的字符串
                    tixianMoney.setText(s.toString().substring(0, s.toString().length() - 1));
                    // 光标强制到末尾
                    tixianMoney.setSelection(tixianMoney.getText().length());
                }
                // 以小数点开头，前面自动加上 "0"
                if (s.toString().startsWith(".")) {
                    tixianMoney.setText("0" + s);
                    tixianMoney.setSelection(tixianMoney.getText().length());
                }
                //
                if (s.toString().startsWith("0") && s.toString().indexOf(".") != 1) {
                    tixianMoney.setText(s.toString().substring(1));
                    tixianMoney.setSelection(tixianMoney.getText().length());
                }
                if (s.toString().length() > 0 && Double.parseDouble(s.toString()) > balance){
                    tixianBalance.setText("金额超过可提现余额");
                    tixianBalance.setTextColor(getResources().getColor(R.color.red_pressed));
                }else {
                    tixianBalance.setText(balance + "");
                    tixianBalance.setTextColor(getResources().getColor(R.color.dark));
                }
            }
        });

        getData();

        setTitleName("提现");
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

                tiXianIndexB = GsonUtil.gsonIntance().gsonToBean(result, TiXianIndexB.class);

                balance = Double.parseDouble(tiXianIndexB.getData().getUinfo().getBalance());
                if (tiXianIndexB.getData().getList().size() > 0) {
                    accountList.addAll(tiXianIndexB.getData().getList());
                    account = tiXianIndexB.getData().getList().get(0);
                    updateUI();
                    createDialog();
                }

                tixianBalance.setText(tiXianIndexB.getData().getUinfo().getBalance());
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
     * 发起申请
     */
    public void toTiXian() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uidBase);
        map.put("accountid", account.getId());
        map.put("money", tixianMoney.getText().toString());
        HttpxUtils.Get(this,HttpPath.TIXIAN_APPLYCASH, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                if (addrReturn.getStatus() == 1){
                    toast("成功");
                    finish();
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
    @OnClick({R.id.tixian_ok,R.id.item_tixian_select_account_layout})
    public void onclick(View view) {
        switch (view.getId()){
            case R.id.tixian_ok://确认提现
                    toTiXian();
                break;
            case R.id.item_tixian_select_account_layout://选择账号
                    if (alertDialog != null){
                        alertDialog.show();
                    }else if (tiXianIndexB != null){
                        //添加
                        toast("去添加账号");
                    }else {
                        getData();
                    }
                break;
        }
    }

    /**
     * 更新界面
     */
    public void updateUI() {
        if (account != null) {
            itemTixianSelectAccountZccount.setText(account.getAccount());
            itemTixianSelectAccountType.setText(account.getType().equals(1) ? "银行卡" : "支付宝");
            itemTixianSelectAccountDes.setText(account.getDesc());
        } else {

        }
//        tixianMoney.setText();
    }

    TiXianAcountAdapter acountAdapter;
    AlertDialog alertDialog = null;
    View popview;

    public void createDialog() {
        if (accountList.size() == 0){
            return;
        }
        popview = View.inflate(this, R.layout.alert_tixian_account,
                null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popview);
        alertDialog = builder.create();
        ListView recyclerView = popview.findViewById(R.id.alert_tixian_acountList);
        acountAdapter = new TiXianAcountAdapter(this,accountList);
        recyclerView.setAdapter(acountAdapter);
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                account = accountList.get(position);
                updateUI();
                alertDialog.dismiss();
            }
        });
    }
}
