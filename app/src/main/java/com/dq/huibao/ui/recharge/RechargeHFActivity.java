package com.dq.huibao.ui.recharge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

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

/**
 * 手机话费充值
 * Created by d on 2018/4/26.
 */

public class RechargeHFActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.tb_noScrollViewPage)
    NoScrollViewPager noScrollViewPager;

    private String[] titles = new String[]{"话费充值", "充值记录"};
    private List<Fragment> fragments = new ArrayList<>();

    private SimpleFragmentPagerAdapter sfpAdapter;

    Intent intent;
    private String uid = "",phone = "", token = "";
    private int page = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout);

        isLogin();
        ButterKnife.bind(this);

        fragments.add(RechargeHfFragment.newInstance(uid,phone,token));
        fragments.add(RechargeLogFragment.newInstance(uid,phone,token));


        sfpAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, fragments, titles);
        noScrollViewPager.setAdapter(sfpAdapter);

        noScrollViewPager.setCurrentItem(page);


        noScrollViewPager.setOffscreenPageLimit(titles.length);

        noScrollViewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(noScrollViewPager);

        setTitleName("话费充值");
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
