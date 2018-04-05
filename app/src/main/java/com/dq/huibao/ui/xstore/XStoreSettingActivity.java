package com.dq.huibao.ui.xstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dq.huibao.R;
import com.dq.huibao.adapter.SimpleFragmentPagerAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 小店设置
 * Created by d on 2018/4/2.
 */

public class XStoreSettingActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.tb_noScrollViewPage)
    NoScrollViewPager noScrollViewPager;

    private String[] titles = new String[]{"基本信息", "小店设置"};
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
        uid = intent.getStringExtra("uid");
        phone = intent.getStringExtra("phone");
        token = intent.getStringExtra("token");

        fragments.add(XStoreFragment.newInstance(uid,phone,token));
        fragments.add(XStoreSetFragment.newInstance(uid,phone,token));


        sfpAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, fragments, titles);
        noScrollViewPager.setAdapter(sfpAdapter);

        noScrollViewPager.setCurrentItem(page);


        noScrollViewPager.setOffscreenPageLimit(titles.length);

        noScrollViewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(noScrollViewPager);
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