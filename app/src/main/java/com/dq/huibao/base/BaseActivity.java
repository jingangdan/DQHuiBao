
package com.dq.huibao.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dq.huibao.R;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.pingo.PinGoActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.utils.ShowUtils;
import com.dq.huibao.utils.StatusBarUtils;

import java.lang.reflect.Field;

/**/
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout parentLinearLayout;//把父类activity和子类activity的view都add到这里
    private Toolbar baseTitleLayout;
    private TextView titleName;

    /*返回*/
    private ImageView titleBack;

    /*Toast*/
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //状态栏图标，文字改为深色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        initContentView(R.layout.activity_base);

        setActivityState(this);
        getLoginInfo();

        initWidght();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getLoginInfo();
    }

    protected void initWidght() {
    }

    /**
     * 初始化contentview
     */
    @SuppressLint("WrongConstant")
    private void initContentView(int layoutResID) {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(parentLinearLayout);
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);

        titleName = (TextView) findViewById(R.id.tv_base_title);

        titleBack = (ImageView) findViewById(R.id.iv_base_back);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        baseTitleLayout = findViewById(R.id.base_title_layout);
        baseTitleLayout.setVisibility(View.GONE);
    }


    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
    }

    @Override
    public void setContentView(View view) {
        parentLinearLayout.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {

        parentLinearLayout.addView(view, params);

    }
    /**
     * 设置屏幕只能竖屏
     * @param activity
     *                  activity
     */
    @SuppressLint("WrongConstant")
    public void setActivityState(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    /**
     * m
     * 设置title
     *
     * @param title ：title
     */
    protected void setTitleName(String title) {
        baseTitleLayout.setVisibility(View.VISIBLE);
        titleName.setText(title);
    }

    protected  void setTitleRes(){

    }

    /**
     * <strong>AlertDialog with one button.</strong><br>
     * The difference between the method "alert" and "toast" is
     * that "alert" would get focus automatically, and won't
     * disappear until you click the button.
     *
     * @param message The message you wanna show.
     * @return AlertDialog
     */
    protected android.support.v7.app.AlertDialog alert(Object message) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage(message.toString());
        builder.setPositiveButton("确定", null);
        android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return alertDialog;
    }

    /**
     * As "Toast.makeText(context, text, duration).show()"
     *
     * @param text The message you wanna show.
     * @return Toast
     */
    @SuppressLint("WrongConstant")
    protected Toast toast(Object text) {
        if (mToast == null) {
            mToast = Toast.makeText(getApplicationContext(), text.toString(), Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text.toString());
        }
        mToast.show();
        return mToast;
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.iv_base_back:
//                this.finish();
//                break;
//        }
    }

    /**
     * 获取点击事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN ) {
            View view = getCurrentFocus();
            if (isShouldHideKeyBord(view, ev)) {
                hideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判定当前是否需要隐藏
     */
    protected boolean isShouldHideKeyBord(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom);
        }
        return false;
    }

    /**
     * 隐藏软键盘
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected String phoneBase = "",tokenBase = "",uidBase = "";

    /**
     * 获取登陆信息
     */
    public void getLoginInfo() {
        SPUserInfo spUserInfo = new SPUserInfo(this.getApplication());

        if (spUserInfo.getLogin().equals("1")) {
            if (!(spUserInfo.getLoginReturn().equals(""))) {
                Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
                phoneBase = login.getData().getPhone();
                tokenBase = login.getData().getToken();
                uidBase = login.getData().getUid();
            }
        }
    }

    /**
     *  跳转登陆
     */
    protected void toLoginActivity(){
        Intent intent = new Intent(BaseActivity.this,LoginActivity.class);
        startActivity(intent);
//        ShowUtils.showDialog(this, "提示", "未登陆，是否登陆", new ShowUtils.OnDialogListener() {
//            @Override
//            public void confirm() {
//
//            }
//
//            @Override
//            public void cancel() {
//
//            }
//        });
    }
}