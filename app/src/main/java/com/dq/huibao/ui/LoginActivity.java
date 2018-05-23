package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.wechat.WeChat;
import com.dq.huibao.ui.addr.AddAddressActivity;
import com.dq.huibao.ui.addr.AddrListActivity;
import com.dq.huibao.ui.jifen.JiFenGoodDetailActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.utils.ShowUtils;
import com.mob.tools.utils.UIHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

import static android.R.attr.action;


/**
 * Description：会员登录
 * Created by jingang on 2017/10/26.
 */
public class LoginActivity extends BaseActivity implements PlatformActionListener, Handler.Callback, View.OnClickListener {

    private static final int MSG_ACTION_CCALLBACK = 0;
    @Bind(R.id.et_login_phone)
    EditText etLoginPhone;
    @Bind(R.id.iv_phone_clear)
    ImageView ivPhoneClear;
    @Bind(R.id.et_login_pwd)
    EditText etLoginPwd;
    @Bind(R.id.iv_pwd_clear)
    ImageView ivPwdClear;
    @Bind(R.id.tv_login_regist)
    TextView tvLoginRegist;
    @Bind(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @Bind(R.id.but_login)
    Button butLogin;
    @Bind(R.id.iv_weixin)
    ImageView ivWeixin;
    @Bind(R.id.iv_qq)
    ImageView ivQq;

    @Bind(R.id.iv_pwd_eye)
    ImageView ivPwdEye;
    //    private ImageView ivWxLogin;
//    private ImageView ivQqLogin;
    //private ImageView ivBlog;

    @Bind(R.id.lin_login_main)
    LinearLayout linLoginMain;

    private ProgressDialog progressDialog;

    /*页面传值*/
    private Intent intent;

    /*接口地址*/
    private String PATH = "";
    private RequestParams params = null;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;

    private TextWatcher username_watcher;
    private TextWatcher password_watcher;

    private String phone = "", pwd = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initWatcher();


        ShareSDK.initSDK(this);
        spUserInfo = new SPUserInfo(getApplication());

        etLoginPhone.addTextChangedListener(username_watcher);
        etLoginPwd.addTextChangedListener(password_watcher);

        linLoginMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return true;
            }
        });

        createDialog();
    }

    @OnClick({R.id.iv_phone_clear, R.id.iv_pwd_clear, R.id.iv_pwd_eye,
            R.id.tv_login_regist, R.id.tv_forget_pwd, R.id.but_login,
            R.id.iv_weixin, R.id.iv_qq})
    public void onClick(View view) {
        phone = etLoginPhone.getText().toString();
        pwd = etLoginPwd.getText().toString();
        switch (view.getId()) {
            case R.id.iv_phone_clear:
                //清除用户输入
                etLoginPhone.setText("");
                etLoginPwd.setText("");
                break;
            case R.id.iv_pwd_clear:
                etLoginPwd.setText("");
                break;

            case R.id.iv_pwd_eye:
                //密码是否可见
                if (etLoginPwd.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    //ivPwdEys.setBackgroundResource(R.mipmap.ic_look001);
                    etLoginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                } else {
                    //ivPwdEys.setBackgroundResource(R.mipmap.ic_look001);
                    etLoginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etLoginPwd.setSelection(etLoginPwd.getText().toString().length());

                break;
            case R.id.tv_login_regist:
                intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forget_pwd:
                intent = new Intent(LoginActivity.this, ForgetPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.but_login:
                if (!phone.equals("")) {
                    if (!pwd.equals("")) {
                        postLogin(phone, pwd);
                    } else {
                        toast("密码不可为空");
                    }
                } else {
                    toast("手机号不可为空");
                }

                break;
            case R.id.iv_weixin:
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                wechat.setPlatformActionListener(this);
                wechat.SSOSetting(false);
                authorize(wechat, 1);
                break;
            case R.id.iv_qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(this);
                qq.SSOSetting(false);
                authorize(qq, 2);
                break;

            default:
                break;
        }
    }

    /**
     * 手机号，密码输入控件公用这一个watcher
     */
    private void initWatcher() {
        username_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("WrongConstant")
            public void afterTextChanged(Editable s) {
                etLoginPwd.setText("");
                if (s.toString().length() > 0) {
                    ivPhoneClear.setVisibility(View.VISIBLE);
                } else {
                    ivPhoneClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        password_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("WrongConstant")
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    ivPwdClear.setVisibility(View.VISIBLE);
                } else {
                    ivPwdClear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    //授权
    private void authorize(Platform plat, int type) {
        switch (type) {
            case 1:
                plat.showUser(null);
                break;
            case 2:
                showProgressDialog("开启QQ");
                break;
//            case 3:
//                showProgressDialog(getString(R.string.opening_blog));
//                break;
        }
        if (plat.isValid()) { //如果授权就删除授权资料
            plat.removeAccount();
        }
//        plat.showUser(null);//授权并获取用户信息
    }

    //登陆授权成功的回调
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> res) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);   //发送消息

    }

    //登陆授权错误的回调
    @Override
    public void onError(Platform platform, int i, Throwable t) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = t;
        UIHandler.sendMessage(msg, this);
    }

    //登陆授权取消的回调
    @Override
    public void onCancel(Platform platform, int i) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    //登陆发送的handle消息在这里处理
    @SuppressLint("WrongConstant")
    @Override
    public boolean handleMessage(Message message) {
        hideProgressDialog();
        switch (message.arg1) {
            case 1: { // 成功
                Toast.makeText(LoginActivity.this, "授权登陆成功", Toast.LENGTH_SHORT).show();

                //获取用户资料
                Platform platform = (Platform) message.obj;
                String userId = platform.getDb().getUserId();//获取用户账号
                String userName = platform.getDb().getUserName();//获取用户名字
                String userIcon = platform.getDb().getUserIcon();//获取用户头像
                String userGender = platform.getDb().getUserGender(); //获取用户性别，m = 男, f = 女，如果微信没有设置性别,默认返回null

                WeChat weChat = GsonUtil.gsonIntance().gsonToBean(platform.getDb().exportData(), WeChat.class);

                //postLoginTest(weChat.getUnionid());

                Toast.makeText(LoginActivity.this, weChat.toString(), Toast.LENGTH_SHORT).show();
                //下面就可以利用获取的用户信息登录自己的服务器或者做自己想做的事啦!
                //。。。
                wechatUnid = platform.getDb().exportData();
                postLoginWx("1","","");
            }
            break;
            case 2: { // 失败
                Toast.makeText(LoginActivity.this, "授权登陆失败", Toast.LENGTH_SHORT).show();
            }
            break;
            case 3: { // 取消
                Toast.makeText(LoginActivity.this, "授权登陆取消", Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return false;
    }

    //显示dialog
    public void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    //隐藏dialog
    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }
    String wechatUnid = "";
    /**
     * 微信登录
     *
     */
    public void postLoginWx(String status,String phone,String pass) {
        Map<String ,String> map = new HashMap<>();
        map.put("status",status);//0-直接注册新的，1-绑定
        map.put("info",wechatUnid);
        map.put("phone",phone);
        map.put("code",MD5Util.getMD5String(pass));
        HttpxUtils.Get(this,HttpPath.ACCOUNT_LOGIN_WX, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                login_result = result;
                try {
                    if(new JSONObject(result).getInt("status") == 1){
                        Login login = GsonUtil.gsonIntance().gsonToBean(result, Login.class);
                        //if (loginBean.getResult().equals("1")) {
                        toast("微信登录成功");
                        spUserInfo.saveLogin("1");//微信登录成功记录 1
                        spUserInfo.saveLoginReturn(result);//登录成功记录返回信息

                        intent = new Intent();
                        intent.putExtra("uid", login.getData().getUid());
                        setResult(CodeUtils.LOGIN, intent);

                        LoginActivity.this.finish();
                    }else {
                        AddrReturn aReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        ShowUtils.showDialogThree(LoginActivity.this, "提示", "当前微信未绑定商城账号,是否绑定", "绑定", "注册", new ShowUtils.OnDialogThreeListener() {
                            @Override
                            public void confirm() {
                                toast("绑定");
                                //
                            }

                            @Override
                            public void center() {
                                postLoginWx("0","","");
//                                alertDialog.show();
                            }

                            @Override
                            public void cancel() {

                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(login_result, AddrReturn.class);
                if (addrReturn.getStatus() == 0) {
                    toast("" + addrReturn.getData());
                } else {
                    toast("微信登录失败，请检查账号或密码");
                }
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
     * 登录
     *
     * @param phone
     * @param pwd
     */
    private String login_result = "";
    public void postLogin(String phone, String pwd) {
        PATH = HttpPath.ACCOUNT_LOGIN +
                "phone=" + phone + "&code=" + MD5Util.getMD5String(pwd);
        HttpxUtils.Post(this,PATH, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                login_result = result;
                try {
                    if(new JSONObject(result).getInt("status") == 1){
                        Login login = GsonUtil.gsonIntance().gsonToBean(result, Login.class);
                        //if (loginBean.getResult().equals("1")) {
                        toast("登录成功");
                        spUserInfo.saveLogin("1");//微信登录成功记录 1
                        spUserInfo.saveLoginReturn(result);//登录成功记录返回信息

                        intent = new Intent();
                        intent.putExtra("uid", login.getData().getUid());
                        setResult(CodeUtils.LOGIN, intent);

                        LoginActivity.this.finish();
                    }else {
                        AddrReturn aReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        toast("" + aReturn.getData());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(login_result, AddrReturn.class);
                if (addrReturn.getStatus() == 0) {
                    toast("" + addrReturn.getData());
                } else {
                    toast("登录失败，请检查账号或密码");
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    AlertDialog alertDialog = null;
    EditText phoneEdit;
    /**
     * 绑定手机号
     */
    public void createDialog() {
        View popview = View.inflate(this, R.layout.alert_login_addphone,
                null);
        phoneEdit = popview.findViewById(R.id.alert_login_addphone);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popview);
        builder.setPositiveButton("注册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                postLoginWx("0",phoneEdit.getText().toString(),"");
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog = builder.create();

    }

    /*强制关闭软键盘*/
    private void hideKeyboard() {
        @SuppressLint("WrongConstant") InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && this.getCurrentFocus() != null) {
            if (this.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}

