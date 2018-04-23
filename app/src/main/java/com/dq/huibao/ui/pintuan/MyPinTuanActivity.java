package com.dq.huibao.ui.pintuan;

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
import com.dq.huibao.ui.xstore.XStoreFragment;
import com.dq.huibao.ui.xstore.XStoreSetFragment;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的拼团记录
 * Created by d on 2018/4/2.
 */

public class MyPinTuanActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.tb_noScrollViewPage)
    NoScrollViewPager noScrollViewPager;

    private String[] titles = new String[]{"进行中", "已完成","已失败"};
    private List<Fragment> fragments = new ArrayList<>();

    private SimpleFragmentPagerAdapter sfpAdapter;

    /*接收页面传值*/
    private Intent intent;
    private int page = 0;
    private String uid = "",phone = "", token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout);
        ButterKnife.bind(this);
        intent = getIntent();
        setTitleName("拼团记录");
        isLogin();

        fragments.add(MyPinTuanChildFragment.newInstance(uid,phone,token,MyPinTuanChildFragment.TYPE_1));
        fragments.add(MyPinTuanChildFragment.newInstance(uid,phone,token,MyPinTuanChildFragment.TYPE_2));
        fragments.add(MyPinTuanChildFragment.newInstance(uid,phone,token,MyPinTuanChildFragment.TYPE_3));


        sfpAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, fragments, titles);
        noScrollViewPager.setAdapter(sfpAdapter);

        noScrollViewPager.setCurrentItem(page);


        noScrollViewPager.setOffscreenPageLimit(titles.length);

        noScrollViewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(noScrollViewPager);
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
    protected void initWidght() {
        super.initWidght();
        setTitleName("小店设置");

    }

    public void setFragments() {

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