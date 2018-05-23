package com.dq.huibao.ui.tixian;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.tixian.TiXianAccountInfoB;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.ShowUtils;

import org.xutils.common.Callback;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现账号详情
 * Created by d on 2018/5/6.
 */

public class TiXianAccountInfoActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private final String ACTION_ADD = "add";
//    private final String ACTION_EDIT = "edit";
    private final String ACTION_DEL = "del";
    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;
    @Bind(R.id.tixian_acountinfo_acount)
    EditText tixianAcountinfoAcount;
    @Bind(R.id.tixian_acountinfo_radiogroup)
    RadioGroup tixianAcountinfoRadiogroup;
    @Bind(R.id.tixian_acountinfo_desc)
    EditText tixianAcountinfoDesc;
    @Bind(R.id.tixian_acountinfo_time)
    TextView tixianAcountinfoTime;
    @Bind(R.id.tixian_acountinfo_button)
    Button tixianAcountinfoButton;
    @Bind(R.id.tixian_acountinfo_yinhangka)
    RadioButton tixianAcountinfoYinhangka;
    @Bind(R.id.tixian_acountinfo_zhifubao)
    RadioButton tixianAcountinfoZhifubao;
    //默认添加，暂时不需要编辑
    private boolean isAdd = true;
    private String accountid = "";
    //账号类型 1 银行卡 2 支付宝
    private String type = "1";

    private int accountTag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_account_info);
        ButterKnife.bind(this);

        titleTvTitle.setText("账号");
        titleTvRight.setText("删除");
        titleTvRight.setVisibility(View.GONE);

        isAdd = getIntent().getBooleanExtra("isAdd", false);
        accountid = getIntent().getStringExtra("accountid");
        accountid = accountid == null ? "" : accountid;
        if (isAdd) {
            titleTvRight.setVisibility(View.GONE);
            tixianAcountinfoButton.setText("添加");
        }else {
            getAccount();
            tixianAcountinfoAcount.setFocusable(false);
            tixianAcountinfoDesc.setFocusable(false);
            tixianAcountinfoButton.setText("删除");
        }
        tixianAcountinfoRadiogroup.setOnCheckedChangeListener(this);
        tixianAcountinfoRadiogroup.setEnabled(false);

        ///
        tixianAcountinfoAcount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (accountTag == 4){
//                    accountTag = 0;
//                    s.append(" ");
//                    Log.e("ffffffffffffffffff","========aaaa==="+s.toString());
//                    tixianAcountinfoAcount.setText(s.toString());
//                    tixianAcountinfoAcount.setSelection(s.length());
//                }
//                accountTag ++;
            }
        });
    }

    /**
     * radiogroup
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.tixian_acountinfo_yinhangka://银行卡
                type = "1";
                break;
            case R.id.tixian_acountinfo_zhifubao://支付宝
                type = "2";
                break;
        }
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.title_iv_back, R.id.title_tv_right,R.id.tixian_acountinfo_button})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_right:

                break;
            case R.id.tixian_acountinfo_button:
                if (isAdd){
                    editAccount(ACTION_ADD);
                }else {
                    ShowUtils.showDialog(this, "提示", "是否删除","删除", new ShowUtils.OnDialogListener() {
                        @Override
                        public void confirm() {
                            editAccount(ACTION_DEL);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                }
                break;
        }
    }
    /**
     * 获取提现账号信息
     */
    public void getAccount() {
        Map<String, String> map = new HashMap<>();
        map.put("accountid", accountid);
        HttpxUtils.Get(this,HttpPath.TIXIAN_GET_ACCOUNTINFO, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                TiXianAccountInfoB accountInfoB = GsonUtil.gsonIntance().gsonToBean(result, TiXianAccountInfoB.class);

                tixianAcountinfoAcount.setText(accountInfoB.getData().getAccount());
                tixianAcountinfoDesc.setText(accountInfoB.getData().getDesc());
                if (accountInfoB.getData().getType().equals("1")){
                    tixianAcountinfoYinhangka.setChecked(true);
                    tixianAcountinfoZhifubao.setVisibility(View.GONE);
                }else {
                    tixianAcountinfoYinhangka.setVisibility(View.GONE);
                    tixianAcountinfoZhifubao.setChecked(true);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("获取提现账号信息", "获取提现账号信息 失败= " + ex.toString());
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
     * 修改提现账号信息
     *  action     //类型 add添加 edit编辑 del 删除
     * -------------------------------------
     * add添加(action uid type desc account)
     * edit编辑(action  desc account accountid  )
     * del 删除(action accountid )

     */
    public void editAccount(String action) {
        Map<String, String> map = new HashMap<>();
        map.put("action", action);
        map.put("uid", uidBase);
        if (action.equals(ACTION_DEL)){
            map.put("accountid", accountid);
        }if (action.equals(ACTION_ADD)){
            map.put("type", type);
            map.put("desc", getUTFDesc());
            map.put("account", tixianAcountinfoAcount.getText().toString());
        }
        HttpxUtils.Get(this,HttpPath.TIXIAN_EDIT, map, new Callback.CommonCallback<String>() {
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
                Log.e("修改提现账号信息", "修改提现账号信息 失败= " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public String getUTFDesc(){
        String s = "";
        try {
            s = URLEncoder.encode(tixianAcountinfoDesc.getText().toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String ss = Base64.encodeToString(s.getBytes(), Base64.DEFAULT);
        ss = ss.replaceAll("[\\s*\t\n\r]", "");
        return ss;
    }
}
