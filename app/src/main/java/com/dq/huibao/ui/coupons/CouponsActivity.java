package com.dq.huibao.ui.coupons;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.SimpleFragmentPagerAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.fragment.memcen.FMCouponsNoUse;
import com.dq.huibao.fragment.memcen.FMCouponsPast;
import com.dq.huibao.fragment.memcen.FMCouponsUsed;
import com.dq.huibao.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：优惠券
 * Created by jingang on 2017/11/1.
 */

public class CouponsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    @Bind(R.id.coupons_tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.coupons_tb_noScrollViewPage)
    NoScrollViewPager noScrollViewPager;
    @Bind(R.id.coupons_tv_title)
    TextView couponsTvTitle;

    private String[] titles = new String[]{"未使用", "已使用", "已过期"};
    private List<Fragment> fragments = new ArrayList<>();

    private SimpleFragmentPagerAdapter sfpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
        ButterKnife.bind(this);

        fragments.add(FMCouponsNoUse.newInstance("FMCouponsNoUse"));
        fragments.add(FMCouponsUsed.newInstance("FMCouponsUsed"));
        fragments.add(FMCouponsPast.newInstance("FMCouponsPast"));

        sfpAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, fragments, titles);
        noScrollViewPager.setAdapter(sfpAdapter);


        noScrollViewPager.setOffscreenPageLimit(titles.length);

        noScrollViewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(noScrollViewPager);
        couponsTvTitle.setText("我的优惠券");

    }

    @OnClick({R.id.coupons_iv_back, R.id.coupons_tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.coupons_iv_back://返回
                finish();
                break;
            case R.id.coupons_tv_right://领券中心
                Intent intent = new Intent(CouponsActivity.this,CouponsGetActivity.class);
                startActivity(intent);
                break;
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
