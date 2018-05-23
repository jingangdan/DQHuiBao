package com.dq.huibao.ui.memcen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Account;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.utils.VerifyType;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：绑定手机
 * Created by jingang on 2017/11/25.
 */

public class MobileActivity extends BaseActivity {
    @Bind(R.id.et_regist_phone)
    EditText etRegistPhone;
    @Bind(R.id.iv_rg_phone_clear)
    ImageView ivRgPhoneClear;
    @Bind(R.id.et_regist_code)
    EditText etRegistCode;
    @Bind(R.id.iv_rg_code_clear)
    ImageView ivRgCodeClear;
    @Bind(R.id.but_get_code)
    Button butGetCode;
    @Bind(R.id.et_regist_pwd)
    EditText etRegistPwd;
    @Bind(R.id.iv_rg_pwd_clear)
    ImageView ivRgPwdClear;
    @Bind(R.id.iv_rg_invitation_clear)
    ImageView ivRgInvitationClear;
    @Bind(R.id.but_regist)
    Button butRegist;
    @Bind(R.id.iv_rg_pwd_eye)
    ImageView ivRgPwdEye;
    @Bind(R.id.et_rg_invitation_clear)
    EditText etRgInvitationClear;
    @Bind(R.id.lin_rg_main)
    LinearLayout linRgMain;

    private TextWatcher tw_phone, tw_code, tw_pwd, tw_invitation;

    private String phone = "", code = "", pwd = "", invitation = "";

    //控制按钮样式是否改变
    private boolean tag = true;
    //每次验证请求需要间隔60S
    private int i = 60;

    /*借口地址*/
    private String PATH = "";
    private RequestParams params = null;

    LinearLayout xieyiLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);

        butRegist.setText("绑定手机号");
        setTitleName("绑定");
        if(phoneBase.equals("")){
            setTitleName("修改绑定");
        }else {
            findViewById(R.id.regist_pwd_layout).setVisibility(View.GONE);
        }
        findViewById(R.id.regist_pwd2_layout).setVisibility(View.GONE);

        initWatcher();

        etRegistPhone.addTextChangedListener(tw_phone);
        etRegistCode.addTextChangedListener(tw_code);
        etRegistPwd.addTextChangedListener(tw_pwd);
        etRgInvitationClear.addTextChangedListener(tw_invitation);

        linRgMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                @SuppressLint("WrongConstant")
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(etRegistPhone.getWindowToken(), 0);
                return true;
            }
        });

        findViewById(R.id.xieyi).setVisibility(View.GONE);
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        // toast("");
    }

    @OnClick({R.id.iv_rg_phone_clear, R.id.iv_rg_code_clear, R.id.but_get_code,
            R.id.iv_rg_pwd_eye,
            R.id.iv_rg_pwd_clear, R.id.iv_rg_pwd2_clear, R.id.iv_rg_invitation_clear,
            R.id.but_regist, R.id.cb_register})
    public void onClick(View view) {

        phone = etRegistPhone.getText().toString();
        code = etRegistCode.getText().toString();
        pwd = etRegistPwd.getText().toString();

        switch (view.getId()) {
            case R.id.iv_rg_phone_clear:
                etRegistPhone.setText("");
                break;
            case R.id.iv_rg_code_clear:
                etRegistCode.setText("");
                break;
            case R.id.but_get_code:
                if (isMobile(phone)) {
                    //手机号格式验证通过
                    toast("手机号验证通过");
                    getVerify(phone,"1");
                    changeBtnGetCode();

                } else {
                    //手机号格式验证不通过
                    toast("手机号验证未通过");
                }
                break;
            case R.id.iv_rg_pwd_clear:
                etRegistPwd.setText("");
                break;
            case R.id.iv_rg_pwd_eye:
                //密码是否可见
                if (etRegistPwd.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    etRegistPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                } else {
                    etRegistPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etRegistPwd.setSelection(etRegistPwd.getText().toString().length());
                break;
            case R.id.iv_rg_invitation_clear:
                etRgInvitationClear.setText("");
                break;
            case R.id.but_regist:
                //输入手机号
                if (!phone.equals("")) {
                    if (!code.equals("") && code.equals(verifyCode)) {
                        if (!pwd.equals("") && pwd.length() >= 6) {
                            backPwd(phone, MD5Util.getMD5String(pwd));
                        } else {
                            toast("密码必须大于等于6位");
                        }
                    } else {
                        toast("验证码错误");
                    }
                } else {
                    toast("手机号不可为空");
                }
                break;

            case R.id.cb_register:

                break;
        }
    }

    /**
     * 手机号，密码输入控件公用这一个watcher
     */
    private void initWatcher() {
        tw_phone = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("WrongConstant")
            public void afterTextChanged(Editable s) {
                etRegistPwd.setText("");
                if (s.toString().length() > 0) {
                    ivRgPhoneClear.setVisibility(View.VISIBLE);
                } else {
                    ivRgPhoneClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        tw_code = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("WrongConstant")
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    ivRgCodeClear.setVisibility(View.VISIBLE);
                } else {
                    ivRgCodeClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        tw_pwd = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("WrongConstant")
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    ivRgPwdClear.setVisibility(View.VISIBLE);
                } else {
                    ivRgPwdClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        tw_invitation = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("WrongConstant")
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    ivRgInvitationClear.setVisibility(View.VISIBLE);
                } else {
                    ivRgInvitationClear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    String verifyCode = "";
    /**
     * 发送验证码
     *
     * @param phone
     * @param type
     */
    public void getVerify(String phone, String type) {
        PATH = HttpPath.ACCOUNT_VERIFY +
                "phone=" + phone + "&type=" + type;
        HttpxUtils.Get(this,PATH, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Account account = GsonUtil.gsonIntance().gsonToBean(result, Account.class);
                if (account.getStatus() == 1) {
                    verifyCode = account.getData();
                } else {
                    toast("" + account.getData());
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
     * 绑定
     *
     * @param phone
     * @param pwd
     */
    public void backPwd(String phone,String pwd) {
        Map<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("uid",uidBase);
        map.put("code",pwd);
        params = new RequestParams(PATH);
        HttpxUtils.Post(this,HttpPath.ACCOUNT_SET_PHONE, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Account account = GsonUtil.gsonIntance().gsonToBean(result, Account.class);
                if (account.getStatus() == 1) {
                    toast("" + account.getData());
                    MobileActivity.this.finish();
                } else {
                    toast("" + account.getData());
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
     * 注册
     *
     * @param phone
     * @param verify 验证码
     * @param pwd
     */
    public void postReg(String phone, String verify, String pwd) {
        PATH = HttpPath.ACCOUNT_REG +
                "phone=" + phone + "&verify=" + verify + "&pwd=" + pwd;

        params = new RequestParams(PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Account account = GsonUtil.gsonIntance().gsonToBean(result, Account.class);
                        if (account.getStatus() == 1) {
                            toast("" + account.getData());
                            MobileActivity.this.finish();
                        } else {
                            toast("" + account.getData());
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
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 改变按钮样式
     */
    private void changeBtnGetCode() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                if (tag) {
                    while (i > 0) {
                        i--;
                        //如果活动为空
                        if (this == null) {
                            break;
                        }

                        MobileActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                butGetCode.setText("获取验证码(" + i + ")");
                                butGetCode.setClickable(false);
                            }
                        });

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    tag = false;
                }
                i = 60;
                tag = true;

                if (this != null) {
                    MobileActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            butGetCode.setText("获取验证码");
                            butGetCode.setClickable(true);
                        }
                    });
                }
            }
        };
        thread.start();
    }

}
