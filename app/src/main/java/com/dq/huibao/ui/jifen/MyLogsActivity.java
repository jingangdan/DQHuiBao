package com.dq.huibao.ui.jifen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.SimpleFragmentPagerAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 积分，余额使用记录
 * Created by d on 2018/4/2.
 */

public class MyLogsActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.tb_noScrollViewPage)
    NoScrollViewPager noScrollViewPager;
    private String uid = "", phone = "", token = "";

    private String[] titles = new String[]{"积分", "余额"};
    //score积分 balance现金
    private String[] type = new String[]{"score", "balance"};
    private List<Fragment> fragments = new ArrayList<>();

    private SimpleFragmentPagerAdapter sfpAdapter;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout);
        ButterKnife.bind(this, this);
        isLogin();

        for (String str:type) {
            fragments.add(MyLogsFragment.newInstance(uid, phone, token, str));
        }

        sfpAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, fragments, titles);
        noScrollViewPager.setAdapter(sfpAdapter);

        noScrollViewPager.setCurrentItem(0);


        noScrollViewPager.setOffscreenPageLimit(titles.length);

        noScrollViewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(noScrollViewPager);

        setTitleName("我的记录");
    }

    /**
     * 判断登录状态
     */
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
